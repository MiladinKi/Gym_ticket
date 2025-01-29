package gym_tickets.entities.dtos;

import java.time.LocalDate;

public class BarCodeDTO {
    private String barCode;
    private String username;
    private String TicketType;
    private LocalDate validityPeriod;

    public BarCodeDTO() {
    }

    public BarCodeDTO(String barCode, String username, String ticketType, LocalDate validityPeriod) {
        this.barCode = barCode;
        this.username = username;
        TicketType = ticketType;
        this.validityPeriod = validityPeriod;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTicketType() {
        return TicketType;
    }

    public void setTicketType(String ticketType) {
        TicketType = ticketType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(LocalDate validityPeriod) {
        this.validityPeriod = validityPeriod;
    }
}
