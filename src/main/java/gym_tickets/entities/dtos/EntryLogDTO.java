package gym_tickets.entities.dtos;

import java.time.LocalDateTime;

public class EntryLogDTO {
    private Integer id;
    private Integer ticketId;
    private Integer userId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public EntryLogDTO() {
    }

    public EntryLogDTO(Integer id, Integer ticketId, Integer userId, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.id = id;
        this.ticketId = ticketId;
        this.userId = userId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
