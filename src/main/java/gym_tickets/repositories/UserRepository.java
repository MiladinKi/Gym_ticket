package gym_tickets.repositories;

import gym_tickets.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query("SELECT u.username FROM UserEntity u")
    public List<String> findAllByUsername();
}
