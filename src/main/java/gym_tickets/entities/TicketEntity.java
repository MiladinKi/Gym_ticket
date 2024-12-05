package gym_tickets.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.cglib.core.EmitUtils;

import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private ETicket ticketType;
    private Double price;
    private Double discount;
    private LocalDate dateOfIssue;
    private LocalDate validityPeriod;
    private String barCode;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @JsonBackReference
    private UserEntity user;

    public TicketEntity() {
    }

    public TicketEntity(Integer id, ETicket ticketType, Double price, Double discount, LocalDate dateOfIssue, LocalDate validityPeriod, String barCode, UserEntity user) {
        this.id = id;
        this.ticketType = ticketType;
        this.price = price;
        this.discount = discount;
        this.dateOfIssue = dateOfIssue;
        this.validityPeriod = validityPeriod;
        this.barCode = barCode;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ETicket getTicketType() {
        return ticketType;
    }

    public void setTicketType(ETicket ticketType) {
        this.ticketType = ticketType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
