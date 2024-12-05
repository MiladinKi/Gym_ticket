package gym_tickets.repositories;

import gym_tickets.entities.TicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<TicketEntity, Integer> {
}
