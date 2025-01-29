package gym_tickets.services;

import com.google.zxing.WriterException;
import gym_tickets.configurations.BarCodeGenerator;
import gym_tickets.configurations.CodeGenerator;
import gym_tickets.entities.ETicket;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.BarCodeDTO;
import gym_tickets.entities.dtos.TicketDTO;
import gym_tickets.entities.dtos.UserTicketDTO;
import gym_tickets.entities.dtos.WorkerEarningsDTO;
import gym_tickets.mappers.TicketMapper;
import gym_tickets.repositories.TicketRepository;
import gym_tickets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public TicketDTO createTicket(TicketDTO ticketDTO) throws IOException, WriterException {
        TicketEntity ticketEntity = TicketMapper.toEntity(ticketDTO);

        if(ticketDTO.getPrice() == null || ticketDTO.getPrice() <= 0){
            throw new IllegalArgumentException("Base price must be higher then 0.");
        }
        double basePrice = ticketDTO.getPrice();
        double calculatedPrice = calculatePriceByTicketPrice(ticketEntity.getTicketType(), basePrice);

        if(ticketDTO.getDiscount() != null && ticketDTO.getDiscount() > 0){
            if(ticketDTO.getDiscount() < 0 || ticketDTO.getDiscount() > 100){
                throw new IllegalArgumentException("Discount must be between 0% i 100%.");
            }
        }

        ticketEntity.setPrice(calculatedPrice);

        LocalDate dateOfIssue = LocalDate.now();
        ticketEntity.setDateOfIssue(dateOfIssue);

        switch (ticketEntity.getTicketType()){
            case ONE_ENTER -> ticketEntity.setValidityPeriod(dateOfIssue);

            case WEEK -> ticketEntity.setValidityPeriod(dateOfIssue.plusWeeks(1));

            case MONTHS -> ticketEntity.setValidityPeriod(dateOfIssue.plusMonths(1));

            case THREE_MONTHS -> ticketEntity.setValidityPeriod(dateOfIssue.plusMonths(3));

            case SIX_MONTHS -> ticketEntity.setValidityPeriod(dateOfIssue.plusMonths(6));

            case YEAR -> ticketEntity.setValidityPeriod(dateOfIssue.plusYears(1));

            case FREE -> {
                if (ticketDTO.getValidityPeriod() == null) {
                    throw new IllegalArgumentException("For FREE tickets, validityPeriod must be provided.");
                }
                ticketEntity.setValidityPeriod(ticketDTO.getValidityPeriod());
            }
            default -> throw new IllegalArgumentException("Unknown ticket type: " + ticketEntity.getTicketType());

        }
        String uniqueCode = CodeGenerator.generateUniqueCode();
        ticketEntity.setBarCode(uniqueCode);
        try {
            BarCodeGenerator.generateBarCode(uniqueCode);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating barcode: " + e.getMessage(), e);
        }
        ticketRepository.save(ticketEntity);
        return TicketMapper.toDTO(ticketEntity);
    }

    public double calculatePriceByTicketPrice(ETicket ticketType, double basePrice){
    switch (ticketType) {
        case ONE_ENTER -> {
            return basePrice;
        }
        case WEEK -> {
            return (basePrice * 5);
        }
        case MONTHS -> {
            return (basePrice * 20);
        }
        case THREE_MONTHS -> {
            return (basePrice * 55);
        }
        case SIX_MONTHS -> {
            return (basePrice * 100);
        }
        case YEAR -> {
            return (basePrice * 180);
        }
        case FREE -> {
            return (basePrice * 0);
        }
        default -> {
            throw new IllegalArgumentException("Unknown ticket type: " + ticketType);
        }
    }

    }

    public UserTicketDTO assignTicketToUser(Integer userId, TicketDTO ticketDTO) throws IOException, WriterException {
        UserEntity user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("User with that id: " + userId + " not found!"));

        TicketDTO createdTicket = createTicket(ticketDTO);
        TicketEntity ticketEntity = TicketMapper.toEntity(createdTicket);

        ticketEntity.setUser(user);
        ticketRepository.save(ticketEntity);

        UserTicketDTO userTicketDTO = new UserTicketDTO();
        userTicketDTO.setUsername(user.getUsername());
        userTicketDTO.setPrice(ticketEntity.getPrice());
        userTicketDTO.setBarCode(ticketEntity.getBarCode());
        userTicketDTO.setValidityPeriod(ticketEntity.getValidityPeriod());

        String barcodeBase64 = BarCodeGenerator.getBarCodeBase64(ticketEntity.getBarCode());

        String subject = "Your ticket details: ";
        String body = String.format(
                "<html>" +
                        "<body>" +
                        "<h2>Dear %s,</h2>" +
                        "<p>Thank you for purchasing a ticket! Here are your ticket details:</p>" +
                        "<img src='data:image/png;base64,%s' alt='Barcode'>" +
                        "<p><strong>Barcode:</strong> %s</p>" +
                        "<p><strong>Price:</strong> %.2f</p>" +
                        "<p><strong>Date of Issue:</strong> %s</p>" +
                        "<p><strong>Valid Until:</strong> %s</p>" +
                        "<p>Enjoy your training!</p>" +
                        "<p>Best regards, <br>Your team</p>" +
                        "</body>" +
                        "</html>",
                user.getUsername(),
                barcodeBase64, // Barcode kao Base64
                ticketEntity.getBarCode(),
                ticketEntity.getPrice(),
                ticketEntity.getDateOfIssue(),
                ticketEntity.getValidityPeriod()
        );

        emailService.sendEmailWithAttachment(user.getEmail(), subject, body);

        return userTicketDTO;

    };

    public WorkerEarningsDTO calculateEarningsByWorkers (LocalDate from, LocalDate to){
        List<TicketEntity> tickets = ticketRepository.findAllByDateOfIssueBetween(from, to);

        Map<String, Double> earningsByWorker =  tickets.stream().collect(Collectors.groupingBy(
                ticket -> ticket.getUser().getUsername(),
                Collectors.summingDouble(TicketEntity::getPrice)
        ));

        Map<String, Long> ticketsSoldByWorker = tickets.stream().collect(Collectors.groupingBy(
                ticket -> ticket.getUser().getUsername(),
                Collectors.counting()
                )
        );

        double totalEarnings = tickets.stream().
                mapToDouble(TicketEntity::getPrice).
                sum();

        long totalTicketsSold = tickets.size();

        WorkerEarningsDTO workerEarningsDTO = new WorkerEarningsDTO();
        workerEarningsDTO.setEarningsByWorker(earningsByWorker);
        workerEarningsDTO.setTotalEarnings(totalEarnings);
        workerEarningsDTO.setTicketSoldByWorker(ticketsSoldByWorker);
        workerEarningsDTO.setTotalTicketsSold(totalTicketsSold);

        return workerEarningsDTO;
    }

    public BarCodeDTO findByBarCode(String barCode){
            TicketEntity ticket = ticketRepository.findByBarCode(barCode);
            if(ticket == null){
                throw new RuntimeException("Ticket with that bar code " + ticket + " not found!");
            }

        BarCodeDTO barCodeTicket = new BarCodeDTO();
            barCodeTicket.setBarCode(ticket.getBarCode());
            barCodeTicket.setUsername(ticket.getUser().getUsername());
            barCodeTicket.setTicketType(ticket.getTicketType().toString());
            barCodeTicket.setValidityPeriod(ticket.getValidityPeriod());

            return barCodeTicket;
            
    }
}
