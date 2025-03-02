package gym_tickets.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class EntryLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    @ManyToOne
    @JoinColumn (name = "ticket_id", nullable = false)
    private TicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public EntryLogEntity() {
    }

    public EntryLogEntity(Integer id, LocalDateTime entryTime, LocalDateTime exitTime, TicketEntity ticket, UserEntity user) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.ticket = ticket;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
