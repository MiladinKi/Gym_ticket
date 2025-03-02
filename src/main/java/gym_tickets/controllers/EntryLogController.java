package gym_tickets.controllers;

import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.dtos.EntryLogDTO;
import gym_tickets.services.EntryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/entryLog")
public class EntryLogController {

    @Autowired
    private EntryLogService entryLogService;

    @RequestMapping(method = RequestMethod.GET, path = "/entry/{ticketId}/{userId}")
    private ResponseEntity<?> registerEntry(@PathVariable Integer ticketId, @PathVariable Integer userId){
        try{

        EntryLogDTO entryLog = entryLogService.registerEntry(ticketId, userId);
        return new ResponseEntity<>(entryLog, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new RESTError(1, "Exception occur: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
}
