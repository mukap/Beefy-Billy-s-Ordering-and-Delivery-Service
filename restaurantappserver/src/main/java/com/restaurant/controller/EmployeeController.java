package com.restaurant.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import com.restaurant.data.CustomerDAO;
import com.restaurant.data.EmployeeDAO;
import com.restaurant.models.Customer;
import com.restaurant.models.Employee;
import com.restaurant.models.Login;

import java.net.URISyntaxException;
import java.util.List;






/**
 * The Employee Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    
    private static final Logger logger = Logger.getLogger(CustomerController.class);    
    
    /**
     * Returns a list of all Employees
     *
     * @author Justin Kroh
     * 
     * @return List of employees in JSON format

     * */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
    	System.out.println("Get all employee");
    	
    	logger.info("Listing All Employees");
    	
        return employeeDAO.getAll();
        
        
    }
    
    
    
	/**
	 *	Gets an individual employee from the database by their ID
	 * @param employeeId
	 * @author Justin Kroh
	 * 
	 * @return Json representation of an individual customer
	 * */
    @GetMapping(path="e/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE) // /api/greetings/x
    // where x is some int
	public Employee getEmployeeById(@PathVariable(name="employeeId", required = true) Integer id) {
    	
    	System.out.println(id);
    	
    	logger.info("Listing Employee with id:" + id);
    	
		return employeeDAO.getById(id);
	}
    
    
	/**
	 * Adds an employee to the database
	 * @param Employee Object, see model
	 * @author Justin Kroh
	 * 
	 * @return Returns the ID of the employee that was added  to the db
	 * */
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestBody Employee e) throws URISyntaxException {
    
    	System.out.println(e);
    	
    	logger.info("Adding Employee" + e.getFirstName());
    	
    	
    	return employeeDAO.save(e);
    	
    }
    
    
    /**
	 * Takes in a user name and password and checks again the database, 
	 * returns an Employee if credentials are accurate
	 * @param Login Object, see model- username and password JSON
	 * @author Justin Kroh
	 * 
	 * @return Returns employee object in JSON
	 * */
    @PostMapping(path="login", consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee checkCredentials(@RequestBody Login login) throws URISyntaxException {

    	
    	System.out.println(login);
    	System.out.println(login.getEmail());

    	logger.info("Checking for credentials" + login.getEmail());
    	
    	return employeeDAO.checkCredentials(login.getEmail(), login.getPassword());
    }

    
    /**
     * Deletes an Employee from the database
     * @param Employee_Id path
     * @author Ronald Martz
     *
     * @return Returns Http Status Okay
     * */
    @DeleteMapping(path="r/{employee_id}")
    @ResponseBody
    public ResponseEntity<String> deleteFromEmployees(@PathVariable(name="employee_id", required = true) Integer id) throws URISyntaxException{
        System.out.println("Create httpHeaders");
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("Reaching the controller");
        
        logger.info("Deleting employee with id" + id);
        
        System.out.println(id);
        employeeDAO.remove(id);
        System.out.println("Return ResponseEntity");
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }
    

}