package gym_tickets.controllers;

import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.UserDTO;
import gym_tickets.services.UserService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/gym_tickets/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser (@RequestBody UserDTO userDTO){
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>(new RESTError(1, "Exception occur:" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public ResponseEntity<?> getById(@PathVariable Integer userId){
        try{
            UserDTO userDTO = userService.getUserById(userId);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(2,  e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO){
        try{
                UserDTO updatedUser = userService.modifyUserById(userDTO, userId);
                return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(3, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId){
        try{
            userService.deleteUserById(userId);
            return ResponseEntity.ok("User successfully deleted");

        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1, e.getMessage()), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/usernames")
    public ResponseEntity<?> findAllUsernames(){
        List<String> usernames = userService.getAllUsernames();
        return ResponseEntity.ok(usernames);
    }

}
