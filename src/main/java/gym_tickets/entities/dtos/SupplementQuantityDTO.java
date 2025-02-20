package gym_tickets.entities.dtos;

public class SupplementQuantityDTO {
    private String name;
    private Integer quantityForSale;
    private Integer inputQuantity;

    public SupplementQuantityDTO() {
    }

    public SupplementQuantityDTO(String name, Integer quantityForSale, Integer inputQuantity) {
        this.name = name;
        this.quantityForSale = quantityForSale;
        this.inputQuantity = inputQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityForSale() {
        return quantityForSale;
    }

    public void setQuantityForSale(Integer quantityForSale) {
        this.quantityForSale = quantityForSale;
    }

    public Integer getInputQuantity() {
        return inputQuantity;
    }

    public void setInputQuantity(Integer inputQuantity) {
        this.inputQuantity = inputQuantity;
    }
}
