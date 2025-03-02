package gym_tickets.repositories;

import gym_tickets.entities.EntryLogEntity;
import gym_tickets.entities.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntryLogRepository extends CrudRepository<EntryLogEntity, Integer> {
    List<EntryLogEntity> findByTicket(TicketEntity ticket);

    Optional<EntryLogEntity> findTopByTicketOrderByEntryTimeDesc(TicketEntity ticket);
}
