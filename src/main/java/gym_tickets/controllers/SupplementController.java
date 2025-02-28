package gym_tickets.controllers;

import gym_tickets.controllers.utils.RESTError;
import gym_tickets.entities.SupplementEntity;
import gym_tickets.entities.dtos.SupplementDTO;
import gym_tickets.entities.dtos.SupplementQuantityDTO;
import gym_tickets.entities.dtos.SupplementSoldDTO;
import gym_tickets.entities.dtos.SupplementViewDTO;
import gym_tickets.services.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/gym_tickets/supplements")
public class SupplementController {

    @Autowired
    private SupplementService supplementService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createSupplement(@RequestBody SupplementDTO supplementDTO){
        try{
            SupplementDTO newSupplement = supplementService.createSupplement(supplementDTO);
            return  new ResponseEntity<>(newSupplement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1,"Exception occur:" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{supplementId}")
    public ResponseEntity<?> deleteSupplementById(@PathVariable Integer supplementId){
        try{
            supplementService.deleteSupplement(supplementId);
            return new ResponseEntity<>(supplementId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1, "Exception occur:" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{supplementId}")
    public ResponseEntity<?> getSupplementByID(@PathVariable Integer supplementId){
        try{
            SupplementDTO supplement = supplementService.getSupplementById(supplementId);
            return new ResponseEntity<>(supplement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1,"Exception occur:" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allSupplements")
    public ResponseEntity<?> getAllSupplements(){
        try{
            List<SupplementViewDTO> supplements = supplementService.getAllSupplements();
            return new ResponseEntity<>(supplements, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new RESTError(1, "Exception occur:" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/updateQuantity")
    public ResponseEntity<?> updateQuantity(@RequestBody SupplementQuantityDTO supplementQuantityDTO){
        try{
            SupplementDTO updateSupplement = supplementService.updateQuantityForSale(supplementQuantityDTO);
            return new ResponseEntity<>(updateSupplement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RESTError(1, "Exception occur:" + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/sold")
    public ResponseEntity<?> soldSupplement(@RequestBody SupplementSoldDTO supplementSoldDTO){
        try {
            SupplementDTO updateSupplement = supplementService.supplementSold(supplementSoldDTO);
            return new ResponseEntity<>(updateSupplement, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new RESTError(1, "Exception occur: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
