package gym_tickets.entities.dtos;

import java.time.LocalDate;

public class TicketDTO {
    private Integer id;
    private String ticketType;
    private Double price;
    private Double discount;
    private LocalDate dateOfIssue;
    private LocalDate validityPeriod;
    private String barCode;
    private String barCodeImageBase64;

    public TicketDTO(Integer id, String ticketType, Double price, Double discount, LocalDate dateOfIssue, LocalDate validityPeriod, String barCode, String barCodeImageBase64) {
        this.id = id;
        this.ticketType = ticketType;
        this.price = price;
        this.discount = discount;
        this.dateOfIssue = dateOfIssue;
        this.validityPeriod = validityPeriod;
        this.barCode = barCode;
        this.barCodeImageBase64 = barCodeImageBase64;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getBarCodeImageBase64() {
        return barCodeImageBase64;
    }

    public void setBarCodeImageBase64(String barCodeImageBase64) {
        this.barCodeImageBase64 = barCodeImageBase64;
    }
}
