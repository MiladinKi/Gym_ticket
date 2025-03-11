package gym_tickets.repositories;

import gym_tickets.entities.EntryLogEntity;
import gym_tickets.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntryLogRepository extends JpaRepository<EntryLogEntity, Integer> {
    List<EntryLogEntity> findByTicket(TicketEntity ticket);
    Optional<EntryLogEntity> findTopByTicketOrderByEntryTimeDesc(TicketEntity ticket);

    long countByEntryTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    long countByExitTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT FUNCTION('DATE', e.entryTime), COUNT(e) FROM EntryLogEntity e " +
            "GROUP BY FUNCTION('DATE', e.entryTime) " +
            "ORDER BY COUNT(e) DESC")
    List<Object[]> findDateWithMostEntries();

    @Query("SELECT DATE(e.entryTime), COUNT(e) FROM EntryLogEntity e " +
            "GROUP BY DATE(e.entryTime) " +
            "ORDER BY COUNT(e) ASC LIMIT 1")
    List<Object[]> findDateWithLeastEntries();

    List<EntryLogEntity> findAllByOrderByEntryTimeAsc();
//    List<EntryLogEntity> findAllByOrderByEntryTimeAsc();
}
