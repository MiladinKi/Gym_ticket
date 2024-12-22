package gym_tickets.entities.dtos;

import java.time.LocalDate;

public class UserTicketDTO {
    private String username;
    private Double price;
    private String barCode;
    private LocalDate validityPeriod;

    public UserTicketDTO() {
    }

    public UserTicketDTO(String username, Double price, String barCode, LocalDate validityPeriod) {
        this.username = username;
        this.price = price;
        this.barCode = barCode;
        this.validityPeriod = validityPeriod;
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
