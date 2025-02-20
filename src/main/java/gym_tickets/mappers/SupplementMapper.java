package gym_tickets.mappers;

import gym_tickets.entities.SupplementEntity;
import gym_tickets.entities.dtos.SupplementDTO;
import gym_tickets.entities.dtos.SupplementQuantityDTO;
import gym_tickets.entities.dtos.SupplementViewDTO;

public class SupplementMapper {

    public static SupplementEntity toEntity(SupplementDTO supplementDTO){
        if (supplementDTO == null){
            return null;
        }

        SupplementEntity supplementEntity = new SupplementEntity();
        supplementEntity.setId(supplementDTO.getId());
        supplementEntity.setName(supplementDTO.getName());
        supplementEntity.setPrice(supplementDTO.getPrice());
        supplementEntity.setBarCode(supplementDTO.getBarCode());
        supplementEntity.setQuantityForSale(supplementDTO.getQuantityForSale());
        supplementEntity.setQuantitySold(supplementDTO.getQuantitySold());
        return supplementEntity;

    }

    public static SupplementDTO toDTO(SupplementEntity supplementEntity){
        if(supplementEntity == null){
            return  null;
        }

        return new SupplementDTO(
                supplementEntity.getId(),
                supplementEntity.getBarCode(),
                supplementEntity.getPrice(),
                supplementEntity.getName(),
                supplementEntity.getQuantityForSale(),
                supplementEntity.getQuantitySold()
        );
    }

    public static SupplementEntity toEntity(SupplementViewDTO supplementViewDTO){
        if(supplementViewDTO == null){
            return null;
        }
        SupplementEntity supplementEntity = new SupplementEntity();
        supplementEntity.setId(supplementViewDTO.getId());
        supplementEntity.setPrice(supplementViewDTO.getPrice());
        supplementEntity.setBarCode(supplementViewDTO.getBarCode());
        supplementEntity.setQuantityForSale(supplementViewDTO.getQuantityForSale());
        return supplementEntity;
    }

    public static SupplementViewDTO toSupplementDTO(SupplementEntity supplementEntity){
        if(supplementEntity == null){
            return  null;
        }

        return new SupplementViewDTO(
                supplementEntity.getId(),
                supplementEntity.getName(),
                supplementEntity.getPrice(),
                supplementEntity.getBarCode(),
                supplementEntity.getQuantityForSale()
        );
        }

    }

