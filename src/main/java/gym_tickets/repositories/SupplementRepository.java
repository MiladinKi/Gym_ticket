package gym_tickets.repositories;

import gym_tickets.entities.SupplementEntity;
import gym_tickets.entities.dtos.SupplementQuantityDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplementRepository extends CrudRepository<SupplementEntity, Integer> {
    Optional <SupplementEntity> findByName(String name);
}
