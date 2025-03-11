package gym_tickets.entities.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EntryCountDTO {
    private LocalDate date;
    private Long count;

    public EntryCountDTO() {
    }

    public EntryCountDTO(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
