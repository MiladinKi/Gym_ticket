package gym_tickets.mappers;

import gym_tickets.entities.EntryLogEntity;
import gym_tickets.entities.dtos.EntryLogDTO;

public class EntryLogMapper {
    public static EntryLogDTO toDTO(EntryLogEntity entity){
        return new EntryLogDTO(
                entity.getId(),
                entity.getTicket().getId(),
                entity.getUser().getId(),
                entity.getEntryTime(),
                entity.getExitTime()
        );
    }
}
