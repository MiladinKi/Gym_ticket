package gym_tickets.entities.dtos;

public class SupplementSoldDTO {
    public Integer id;
    public Integer quantitySold;
    public Integer userId;

    public SupplementSoldDTO() {
    }

    public SupplementSoldDTO(Integer id, Integer quantitySold, Integer userId) {
        this.id = id;
        this.quantitySold = quantitySold;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(Integer quantitySold) {
        this.quantitySold = quantitySold;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
