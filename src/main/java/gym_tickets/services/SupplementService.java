package gym_tickets.services;

import gym_tickets.entities.SupplementEntity;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.SupplementDTO;
import gym_tickets.entities.dtos.SupplementQuantityDTO;
import gym_tickets.entities.dtos.SupplementSoldDTO;
import gym_tickets.entities.dtos.SupplementViewDTO;
import gym_tickets.mappers.SupplementMapper;
import gym_tickets.repositories.SupplementRepository;
import gym_tickets.repositories.UserRepository;
import org.hibernate.engine.transaction.jta.platform.internal.SunOneJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplementService {

    @Autowired
    private SupplementRepository supplementRepository;

    @Autowired
    private UserRepository userRepository;

    public SupplementDTO createSupplement(SupplementDTO supplementDTO) {
        SupplementEntity supplementEntity = SupplementMapper.toEntity(supplementDTO);
        supplementRepository.save(supplementEntity);
        return SupplementMapper.toDTO(supplementEntity);
    }

    public void deleteSupplement(Integer supplementId){
        SupplementEntity supplement = supplementRepository.findById(supplementId).
                orElseThrow(()->new RuntimeException("Supplement not found"));
        supplementRepository.delete(supplement);
    }

    public SupplementDTO getSupplementById(Integer supplementId){
        SupplementEntity supplement = supplementRepository.findById(supplementId).
                orElseThrow(()->new RuntimeException("Supplement not found"));
        return SupplementMapper.toDTO(supplement);
    }

    public List<SupplementViewDTO> getAllSupplements(){
        List<SupplementEntity> supplements = (List<SupplementEntity>) supplementRepository.findAll();

        return  supplements.stream().map(SupplementMapper::toSupplementDTO).
                collect(Collectors.toList());
    }

    public SupplementDTO updateQuantityForSale(SupplementQuantityDTO supplementQuantityDTO){
        SupplementEntity supplement = supplementRepository.findByName(supplementQuantityDTO.getName()).
                orElseThrow(()-> new RuntimeException("Supplement not found"));
        int newQuantity = supplement.getQuantityForSale() + supplementQuantityDTO.getInputQuantity();

        if(newQuantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative!");
        }

        supplement.setQuantityForSale(newQuantity);
        supplementRepository.save(supplement);

        return SupplementMapper.toDTO(supplement);
    }

    public SupplementDTO supplementSold(SupplementSoldDTO supplementSoldDTO){
        SupplementEntity supplementEntity = supplementRepository.findById(supplementSoldDTO.getId()).
                orElseThrow(()->new RuntimeException("Supplement not found"));

        UserEntity userEntity = userRepository.findById(supplementSoldDTO.getUserId()).
                orElseThrow(()-> new RuntimeException("User not found"));

        if(supplementEntity.getQuantityForSale()<supplementSoldDTO.getQuantitySold()){
            throw new IllegalArgumentException("Not enough stock available for sale!");
        }

       int x = supplementSoldDTO.getQuantitySold();
        supplementEntity.setQuantityForSale(supplementEntity.getQuantityForSale() - x);

        supplementEntity.setUser(userEntity);

        supplementRepository.save(supplementEntity);
        return SupplementMapper.toDTO(supplementEntity);
    }
}
