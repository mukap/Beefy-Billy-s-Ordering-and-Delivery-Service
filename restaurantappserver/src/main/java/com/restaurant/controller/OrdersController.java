package com.restaurant.controller;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.data.CustomerDAO;
import com.restaurant.data.EmployeeDAO;
import com.restaurant.data.MenuItemsDAO;
import com.restaurant.data.OrdersDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.MenuItems;
import com.restaurant.models.Orders;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * The Orders Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {
	
    @Autowired
    private OrdersDAO ordersDAO;
    
    @Autowired
    private MenuItemsDAO menuItemsDAO;
    
    private static final Logger logger = Logger.getLogger(CustomerController.class); 

    
    /**
     * Returns a list of all orders
     *
     * @author Justin Kroh
     * 
     * @return List of orders in JSON format

     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> getAllOrders() {

    	
    	
    	logger.info("Getting all Orders");

    	System.out.println("Get all orders");
    	
    	
    	

        return ordersDAO.getAll();
        
        
    }
    
    
	/**
	 * Gets an individual order from the database by their ID
	 * @param orderId
	 * @author Justin Kroh
	 * 
	 * @return Json representation of an individual order
	 * */
    @GetMapping(path="o/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Orders getOrdersById(@PathVariable(name="orderId", required = true) Integer id) {
    	
    	logger.info("Getting All Orders with Id of" + id);
    	
    	System.out.println(id);
		return ordersDAO.getById(id);
	}
    
    
    /**
	 * Adds a Order to the database
	 * @param Order Object, see model
	 * @author Justin Kroh
	 * 
	 * 
	 * */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addOrders(@RequestBody Orders o) throws URISyntaxException {
    
    	
    	if (o.getItemsOrdered() != null) {
    	
    	logger.info("Adding Order" + o.getOrderType());
    	
    	
    	// Duplicate Items cannot exist in the same session
    	
    	List<MenuItems> actualItemsOrdered = new ArrayList<MenuItems>();
    	
    	
	    	for (MenuItems m: o.getItemsOrdered() ) {
	    		
	    		actualItemsOrdered.add(menuItemsDAO.getById(m.getMenu_id()));
	    		
	    		
	    	}
    	
    	System.out.println(o);
    	
    	System.out.println(o.getOrder_id());
    	
    	o.getItemsOrdered().clear();
    	
    	System.out.println(o.getItemsOrdered());
    	
    	o.setItemsOrdered(actualItemsOrdered);
    	}
    	System.out.println(o.getItemsOrdered());
    	
    	System.out.println(o);
    	ordersDAO.save(o);
    }
    
    /**
	 * Deletes a Order from the database, uses query params ID and Status
	 * @author Justin Kroh
	 * 
	 * 
	 * */
    @RequestMapping(method=RequestMethod.PUT,path="/u/")
    @ResponseBody
    public void updateOrderStatus(@RequestParam(name="id") Integer id, @RequestParam(name="status") String status){
        System.out.println("Updated Order " + id + " Status to " + status);
        
        logger.info("Updating Order with ID of" + id);
        
        ordersDAO.updateOrderStatus(id,status);
    }
    
    
    

}