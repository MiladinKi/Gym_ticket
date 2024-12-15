package gym_tickets.services;

import gym_tickets.entities.ERole;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.UserDTO;
import gym_tickets.mappers.UserMapper;
import gym_tickets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   public UserDTO createUser (UserDTO userDTO){
       UserEntity userEntity = UserMapper.toEntity(userDTO);
       userRepository.save(userEntity);
       return UserMapper.toDTO(userEntity);
   }

   public UserDTO getUserById(Integer userId){
       UserEntity userEntity = userRepository.findById(userId).
               orElseThrow(()-> new RuntimeException("User not found"));
       return  UserMapper.toDTO(userEntity);
   }

   public UserDTO modifyUserById(UserDTO userDTO, Integer userId){
       UserEntity userEntity = userRepository.findById(userId).
               orElseThrow(()-> new RuntimeException("User not found!"));
       userEntity.setName(userDTO.getName());
       userEntity.setLastname(userDTO.getLastname());
       userEntity.setEmail(userDTO.getEmail());
       userEntity.setUsername(userDTO.getUsername());
       userEntity.setPassword(userDTO.getPassword());
       userEntity.setUserRole(ERole.valueOf(userDTO.getUserRole().toUpperCase()));

       userRepository.save(userEntity);
       return UserMapper.toDTO(userEntity);
   }

   public void deleteUserById(Integer userId){
       UserEntity userEntity = userRepository.findById(userId).
               orElseThrow(()->new RuntimeException("User not found!"));
       userRepository.delete(userEntity);

   }

   public List<String> getAllUsernames(){
       return  userRepository.findAllByUsername();
   }

}
