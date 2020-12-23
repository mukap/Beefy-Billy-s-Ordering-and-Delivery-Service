package com.restaurant.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurant.data.CustomerDAO;
import com.restaurant.data.EmployeeDAO;
import com.restaurant.data.MenuItemsDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.MenuItems;

import java.net.URISyntaxException;
import java.util.List;




/**
 * The MenuItems Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
@CrossOrigin
@RestController
@RequestMapping("/menuitems")
public class MenuItemsController {
    @Autowired
    private MenuItemsDAO menuItemsDAO;

    
    
    private static final Logger logger = Logger.getLogger(CustomerController.class);  
    
    
    /**
     * Returns a list of all menu items
     *
     * @author Justin Kroh
     * 
     * @return List of menuItems in JSON format

     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItems> getAllMenuItems() {
    	System.out.println("Det all menu items");
    	
    	
    	logger.info("Getting all Menu items");
    	
        return menuItemsDAO.getAll();
    }
    
    
    
	/**
	 * Gets an individual menu item from the database by their ID
	 * @param employeeId
	 * @author Justin Kroh
	 * 
	 * @return Json representation of an individual menuItems
	 * */
    @GetMapping(path="m/{menuId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public MenuItems getMenuItemById(@PathVariable(name="menuId", required = true) Integer id) {
    	
    	logger.info("Getting MenuItem with id of " + id);
    	
    	
    	System.out.println(id);
		return menuItemsDAO.getById(id);
	}
    
    
    /**
	 * Adds a menuItem to the database
	 * @param MenuItem Object, see model
	 * @author Justin Kroh
	 * 
	 * @return Returns the ID of the menuITem that was added  to the db
	 * */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addMenuItem(@RequestBody MenuItems m) throws URISyntaxException {
    	
    	logger.info("Adding menu Item " + m.getItemName());
    	
    	
    	System.out.println(m);
    	menuItemsDAO.save(m);
    }

    
    /**
	 * Deletes a MenuItem from the database
	 * @param Menu_Id path
	 * @author Ronald Martz
	 * 
	 * @return Returns Http Status Okay
	 * */
    @DeleteMapping(path="r/{menu_id}")
    @ResponseBody
    public ResponseEntity<String> deleteFromMenu(@PathVariable(name="menu_id", required = true) Integer id) throws URISyntaxException{
    	
    	logger.info("Deleting MenuItem with id of " + id);
    	
    	System.out.println("Create httpHeaders");
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("Reaching the controller");
        System.out.println(id);
        menuItemsDAO.remove(id);
        System.out.println("Return ResponseEntity");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }

	/**
	 * Updates a MenuItem from the database, uses query params ID, Name, Desc, and Price
	 * @author Ronald Martz
	 *
	 *
	 * */
    
    
	@RequestMapping(method=RequestMethod.PUT,path="/u/")
	@ResponseBody
	public void changeMenuItem(@RequestParam(name="id") Integer id, @RequestParam(name="name") String name,
							   @RequestParam(name="desc") String desc, @RequestParam(name="price") Integer price){
		
		logger.info("Updating MenuItem with id of " + id);
		System.out.println("Updated Menu Item " + id + " Price to " + price);
		menuItemsDAO.changeItem(id,name,desc,price);
	}
    

}