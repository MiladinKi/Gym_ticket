package gym_tickets.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SupplementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private Double discount;
    private String barCode;
    private Integer quantityForSale;
    private Integer quantitySold;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @JsonBackReference
    private UserEntity user;

    public SupplementEntity() {
    }

    public SupplementEntity(Integer id, UserEntity user, Integer quantitySold, Integer quantityForSale, String barCode, Double discount, Double price, String name) {
        this.id = id;
        this.user = user;
        this.quantitySold = quantitySold;
        this.quantityForSale = quantityForSale;
        this.barCode = barCode;
        this.discount = discount;
        this.price = price;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Integer getQuantityForSale() {
        return quantityForSale;
    }

    public void setQuantityForSale(Integer quantityForSale) {
        this.quantityForSale = quantityForSale;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
