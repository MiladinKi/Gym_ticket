package gym_tickets.controllers;

import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.dtos.TicketDTO;
import gym_tickets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
