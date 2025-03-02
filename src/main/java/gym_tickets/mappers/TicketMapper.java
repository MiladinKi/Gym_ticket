package gym_tickets.mappers;

import gym_tickets.entities.ETicket;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.dtos.TicketDTO;

public class TicketMapper {

    public static TicketEntity toEntity(TicketDTO ticketDTO){
        if(ticketDTO == null){
            return null;
        }
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticketDTO.getId());
        ticketEntity.setTicketType(ETicket.valueOf(ticketDTO.getTicketType().toUpperCase()));
        ticketEntity.setPrice(ticketDTO.getPrice());
        ticketEntity.setDiscount(ticketDTO.getDiscount());
        ticketEntity.setDateOfIssue(ticketDTO.getDateOfIssue());
        ticketEntity.setValidityPeriod(ticketDTO.getValidityPeriod());
        ticketEntity.setBarCode(ticketDTO.getBarCode());
        ticketEntity.setBarCodeImageBase64(ticketDTO.getBarCodeImageBase64());
        return ticketEntity;
    }

    public static TicketDTO toDTO(TicketEntity ticketEntity){
        if(ticketEntity == null){
            return null;
        }

        return new TicketDTO(
                ticketEntity.getId(),
                ticketEntity.getTicketType().name(),
                ticketEntity.getPrice(),
                ticketEntity.getDiscount(),
                ticketEntity.getDateOfIssue(),
                ticketEntity.getValidityPeriod(),
                ticketEntity.getBarCode(),
                ticketEntity.getBarCodeImageBase64()
        );
    }
}
