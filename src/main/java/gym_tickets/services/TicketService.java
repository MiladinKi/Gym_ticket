package gym_tickets.services;

import com.google.zxing.WriterException;
import gym_tickets.configurations.BarCodeGenerator;
import gym_tickets.configurations.CodeGenerator;
import gym_tickets.entities.ETicket;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.dtos.TicketDTO;
import gym_tickets.mappers.TicketMapper;
import gym_tickets.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

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
}
