package gym_tickets.entities.dtos;

public class SupplementDTO {
    private Integer id;
    private String name;
    private Double price;
    private String barCode;
    private Integer quantityForSale;
    private Integer quantitySold;


    public SupplementDTO() {
    }

    public SupplementDTO(Integer id, String barCode, Double price, String name, Integer quantityForSale, Integer quantitySold) {
        this.id = id;
        this.barCode = barCode;
        this.price = price;
        this.name = name;
        this.quantityForSale = quantityForSale;
        this.quantitySold = quantitySold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getQuantityForSale() {
        return quantityForSale;
    }

    public void setQuantityForSale(Integer quantityForSale) {
        this.quantityForSale = quantityForSale;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }
}
