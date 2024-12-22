package gym_tickets.controllers;

import com.google.zxing.WriterException;
import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.dtos.TicketDTO;
import gym_tickets.entities.dtos.UserTicketDTO;
import gym_tickets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/gym_tickets/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTicket(@RequestBody TicketDTO ticketDTO){
        try {
            TicketDTO createTicket = ticketService.createTicket(ticketDTO);
            return  new ResponseEntity<>(createTicket, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new RESTError(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(new RESTError(2, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping (method = RequestMethod.POST, path = "/assignTicketToUser/{userId}")
    public  ResponseEntity<?> assignTicketToUser(@PathVariable Integer userId, @RequestBody TicketDTO ticketDTO) throws WriterException, IOException {
       try {
           UserTicketDTO userTicketDTO = ticketService.assignTicketToUser(userId, ticketDTO);
           return new ResponseEntity<>(userTicketDTO, HttpStatus.CREATED);
       } catch (IOException | WriterException e){
           return new ResponseEntity<>(new RESTError(1, e.getMessage()), HttpStatus.BAD_REQUEST);
       }
    }

}
