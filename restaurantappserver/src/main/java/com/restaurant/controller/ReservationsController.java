package com.restaurant.controller;

import com.restaurant.data.ReservationsDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Reservations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


/**
 * The Reservations Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
@CrossOrigin
@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsDAO reservationsDAO;
    
    private static final Logger logger = Logger.getLogger(CustomerController.class); 

    
    /**
     * Returns a list of all Reservations
     *
     * @author Justin Kroh
     * 
     * @return List of Reservations in JSON format

     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservations> getAllReservations() {
    	System.out.println("Returning all reservations");
    	
    	logger.info("Returning all reservations");
    	
    	return reservationsDAO.getAll();}
    
    
	/**
	 * Gets a Reservation from the database by their ID
	 * @param reservationId
	 * @author Justin Kroh
	 * 
	 * @return Json representation of an individual Reservation
	 * */
    @GetMapping(path="r/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Reservations getReservationById(@PathVariable(name="reservationId", required = true) Integer id) {

    	logger.info("Getting reservation with id of" + id);
    	
    	System.out.println(id);
    	return reservationsDAO.getById(id); 
		
	}
    
    /**
	 * Adds a Reservation to the database
	 * @param Reservation Object, see model
	 * @author Justin Kroh
	 * 
	 * 
	 * */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addReservation(@RequestBody Reservations r) throws URISyntaxException {
    	
    	logger.info("Adding reservation" + r.getDate());
    	
    	
    	System.out.println(r);
    	reservationsDAO.save(r);
    }

    
    /**
	 * Deletes a Reservation from the database, uses query params ID and Status
	 * @author Ronald Martz
	 * 
	 * 
	 * */
    @RequestMapping(method=RequestMethod.PUT,path="/u/")
    @ResponseBody
    public void changeReservationStatus(@RequestParam(name="id") Integer id, @RequestParam(name="status") String status){
        System.out.println("Updated Reservation " + id + " Status to " + status);
        
        logger.info("Updating Reservations with id of " + id);
        
        reservationsDAO.changeReservation(id,status);
    }

    
    
}


