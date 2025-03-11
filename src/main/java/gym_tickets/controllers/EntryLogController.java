package gym_tickets.controllers;

import com.zaxxer.hikari.metrics.IMetricsTracker;
import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.dtos.EntryCountDTO;
import gym_tickets.entities.dtos.EntryLogDTO;
import gym_tickets.services.EntryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/gym_tickets/entryLog")
public class EntryLogController {

    @Autowired
    private EntryLogService entryLogService;

    @RequestMapping(method = RequestMethod.POST, path = "/entry/{ticketId}/{userId}")
    private ResponseEntity<?> registerEntry(@PathVariable Integer ticketId, @PathVariable Integer userId){
        try{

        EntryLogDTO entryLog = entryLogService.registerEntry(ticketId, userId);
        return new ResponseEntity<>(entryLog, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new RESTError(1, "Exception occur: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "/exit/{ticketId}")
    private ResponseEntity<?> registerExit(@PathVariable Integer ticketId){
        try{

            EntryLogDTO exitLog = entryLogService.registerExit(ticketId);
            return new ResponseEntity<>(exitLog, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new RESTError(1, "Exception occur: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/entriesNumber")
    private ResponseEntity<?> getEntriesBetween(@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startTime,
                                                @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime){
        try {
            long x = entryLogService.getNumberOfEntriesBetween(startTime, endTime);
            return new ResponseEntity<>(x, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1, "Exception occured: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/most")
    public ResponseEntity<?> getDateWithMostEntries() {
        EntryCountDTO result = entryLogService.getDateWithMostEntries();
        if (result == null) {
            return new ResponseEntity<>("No entries found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/least")
    public ResponseEntity<?> leastEntries(){
        EntryCountDTO result = entryLogService.getDateWithTheLeastEntries();
        if (result == null) {
            return new ResponseEntity<>("No entries found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
