package gym_tickets.mappers;

import gym_tickets.entities.ERole;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.TicketDTO;
import gym_tickets.entities.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toDTO(UserEntity userEntity){
        if(userEntity == null){
            return null;
        }

        List<TicketDTO> ticketDTOs = userEntity.getTickets().
                stream().
                map(UserMapper::toTicketDTO).
                collect(Collectors.toList());

    return new UserDTO(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getLastname(),
            userEntity.getEmail(),
            userEntity.getUsername(),
            userEntity.getPassword(),
            userEntity.getUserRole().name(),
            ticketDTOs
    );
    }

    public static UserEntity toEntity (UserDTO userDTO){
        if(userDTO == null){
            return  null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setLastname(userDTO.getLastname());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setUserRole(ERole.valueOf(userDTO.getUserRole().toUpperCase()));
        return userEntity;
    }

    public static TicketDTO toTicketDTO(TicketEntity ticketEntity){
        return new TicketDTO(
        ticketEntity.getId(),
        ticketEntity.getTicketType().name(),
        ticketEntity.getPrice(),
        ticketEntity.getDiscount(),
        ticketEntity.getDateOfIssue(),
        ticketEntity.getValidityPeriod(),
        ticketEntity.getBarCode()
        );
    }
}
