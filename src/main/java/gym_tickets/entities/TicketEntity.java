package gym_tickets.entities;

import org.springframework.cglib.core.EmitUtils;

import java.time.LocalDate;

public class TicketEntity {
    private Integer id;
    private ETicket ticketType;
    private Double price;
    private Double discount;
    private LocalDate dateOfIssue;
    private LocalDate validityPeriod;
    private String barCode;

}
