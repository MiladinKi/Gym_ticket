package gym_tickets.controllers;

import com.google.zxing.WriterException;
import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.TicketEntity;
import gym_tickets.entities.UserEntity;
import gym_tickets.entities.dtos.BarCodeDTO;
import gym_tickets.entities.dtos.TicketDTO;
import gym_tickets.entities.dtos.UserTicketDTO;
import gym_tickets.entities.dtos.WorkerEarningsDTO;
import gym_tickets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

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
    public ResponseEntity<?> assignTicketToUser(@PathVariable Integer userId, @RequestBody TicketDTO ticketDTO) {
        try {
            UserTicketDTO userTicketDTO = ticketService.assignTicketToUser(userId, ticketDTO);
            return new ResponseEntity<>(userTicketDTO, HttpStatus.CREATED);
        } catch (IOException | WriterException e) {
            return new ResponseEntity<>(new RESTError(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(2, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/workerEarnings")
    public ResponseEntity<?> getWorkerEarnings(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDate from, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate to){
        try {
            WorkerEarningsDTO earningsDTO = ticketService.calculateEarningsByWorkers(from, to);
            return new ResponseEntity<>(earningsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/barCode/{barCode}")
    public ResponseEntity<?> findTicketByBarCode(@PathVariable String barCode){
        try{
            BarCodeDTO ticket = ticketService.findByBarCode(barCode);
            if (ticket == null){
                return new ResponseEntity<>(new RESTError(1, "Ticket not found"), HttpStatus.NOT_FOUND);
            }
            return  new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1, e.getMessage()), HttpStatus.OK);
        }

    }
}
