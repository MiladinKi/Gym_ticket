package gym_tickets.repositories;

import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.dtos.TicketDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends CrudRepository<TicketEntity, Integer> {


    public List<TicketEntity> findAllByDateOfIssueBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);
    public TicketEntity findByBarCode(String barCode);
}
