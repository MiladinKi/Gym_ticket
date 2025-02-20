package gym_tickets.entities.dtos;

public class SupplementViewDTO {
    private Integer id;
    private String name;
    private Double price;
    private String barCode;
    private Integer quantityForSale;
//    private Double totalValueSupplement;

    public SupplementViewDTO() {
    }

    public SupplementViewDTO(Integer id, String name, Double price, String barCode, Integer quantityForSale/*, Double totalValueSupplement*/) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.barCode = barCode;
        this.quantityForSale = quantityForSale;
//        this.totalValueSupplement = totalValueSupplement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

//    public Double getTotalValueSupplement() {
//        return totalValueSupplement;
//    }
//
//    public void setTotalValueSupplement(Double totalValueSupplement) {
//        this.totalValueSupplement = totalValueSupplement;
//    }
}
