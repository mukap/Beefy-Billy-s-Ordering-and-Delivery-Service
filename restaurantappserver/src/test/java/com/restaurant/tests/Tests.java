package com.restaurant.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.restaurant.controller.CustomerController;
import com.restaurant.controller.EmployeeController;
import com.restaurant.controller.MenuItemsController;
import com.restaurant.controller.OrdersController;
import com.restaurant.controller.ReservationsController;
import com.restaurant.models.Customer;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;

import java.io.StringWriter;

////import static MockMvcRequestBuilders.*;
//import static MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-application-context.xml"})
@WebAppConfiguration
public class Tests {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    CustomerController controller;
    
    
    Customer c;
    
    MockMvc mockMvc;
    
//*****************Testing for CustomerController********************************    

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        
//        c = new Customer();
//        c.setEmail("test");
//        c.setFirstName("test");
//        c.setLastName("test");
//        c.setAddress("test");
        
    }

//*****************Testing for CustomerControlle's getAllCustomers method******************************** 
    @Test
    public void getCustomerController_ThenReturnCustomer() throws Exception {
        MvcResult result = mockMvc.perform(get("/customers")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        

        int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       

        
    }
//*****************Testing for CustomerController's getCustomerById method******************************** 
    @Test
    public void getCustomerController_ThenReturnSpecificCustomer() throws Exception {
        MvcResult result = mockMvc.perform(get("/customers/c/1")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		

		String databaseResponse = result.getResponse().getContentAsString().toString(); 
		System.out.println("*************************************");

	    
		String json = Json.createObjectBuilder()
	            .add("customer_id", 1)
	            .add("password", "12345")
	            .add("email", "test")
	            .add("firstName", "test")
	            .add("lastName", "test")
	            .add("address", "test")
	            .build()
	            .toString();
		System.out.println(json);

		
		String actual = databaseResponse;
		
		String expected = json;
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    

//*****************Testing for CustomerController's addCustomer method********************************    

    
	String json = Json.createObjectBuilder()
            .add("password", "12345")
            .add("email", "test")
            .add("firstName", "test")
            .add("lastName", "test")
            .add("address", "test")
            .build()
            .toString();

	
    @Test 
    public void getCustomerController_ThenAddCustomer() throws Exception {
    	
    	
        MvcResult result = mockMvc.perform(post("/customers") 
        		.contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();

		
		System.out.println(result.getResponse().getStatus()); 
		
		int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
        
		Assert.assertEquals(actual, expected);
		
        
       
    }
    
    
//*****************Testing for EmployeeController********************************
 
    @Autowired
    EmployeeController controllerEmp;

  
    @Before
    public void setupEmp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
//*****************Testing for EmployeeController's getAllEmployees method********************************    


    @Test
    public void getEmployeeController_ThenReturnEmployee() throws Exception {
        MvcResult result = mockMvc.perform(get("/employee")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();
        
        int actual = result.getResponse().getStatus();  
		
		int expected = 200;
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    
//*****************Testing for EmployeeController's getEmployeeById method********************************    

    
    @Test
    public void getEmployeeController_ThenReturnSpecificEmployee() throws Exception {
        MvcResult result = mockMvc.perform(get("/employee/e/1")) // testing is done without the /api context of the DispatcherServlet f
        

        		// from web.xml. The tests startup their own context not from the
        		.andDo(print())
        		.andExpect(status().isOk()) 
                .andReturn();
        
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String databaseResponse = result.getResponse().getContentAsString().toString(); 
		System.out.println("*************************************");
		
		String json = Json.createObjectBuilder()
	            .add("employee_id", 1)
	            .add("password", "12345")
	            .add("email", "test12345")
	            .add("firstName", "test")
	            .add("lastName", "test")
	            .add("address", "test")
	            .add("user_type", "MANAGER")
	            .build()
	            .toString();
		System.out.println(json);
		
		String actual = databaseResponse;
		
		String expected = json;
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
        Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
       
    }
    
    
    
    
//*****************Testing for EmployeeController's addEmployee method********************************   
    
	String jsonEmp = Json.createObjectBuilder()
			.add("employee_id", 1000)
            .add("password", "12345")
            .add("email", "test12345")
            .add("firstName", "test")
            .add("lastName", "test")
            .add("address", "test")
            .add("user_type", "MANAGER")
            .build()
            .toString();
	
	@Test 
    public void getEmployeeController_ThenAddEmployee() throws Exception {
    	
    	
        MvcResult result = mockMvc.perform(post("/employee") 
        		.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonEmp))
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();

		
		System.out.println(result.getResponse().getStatus());
		
		int actual = result.getResponse().getStatus();  
		
		int expected = 200;
        
		Assert.assertEquals(actual, expected);
		
		
}
	

	
	//*****************Testing for EmployeeController's addEmployee method********************************   
    
	
	@Test 
    public void deleteEmployeeById() throws Exception {
    	
    	
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/employee/r/1000") 
        		.contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andDo(print())
        		.andExpect(status().isOk())
                .andReturn();

		
		System.out.println(result.getResponse().getStatus());
		
		int actual = result.getResponse().getStatus();  
		
		int expected = 200;
        
		Assert.assertEquals(actual, expected);
		
		
}
	//*****************Testing for EmployeeController's addEmployee method********************************   
    
	String login = Json.createObjectBuilder()
            .add("password", "12345")
            .add("email", "test12345")
            .build()
            .toString();
		
		@Test 
	    public void getEmployee2Controller_ThenAddEmployee() throws Exception {
	    	
	    	
	        MvcResult result = mockMvc.perform(post("/employee/login") 
	        		.contentType(MediaType.APPLICATION_JSON_VALUE).content(login))
	        		.andDo(print())
	        		.andExpect(status().isOk())
	                .andReturn();

			
			System.out.println(result.getResponse().getStatus());
			
			int actual = result.getResponse().getStatus();  
			
			int expected = 200;
	        
			Assert.assertEquals(actual, expected);
			
			
	}
	
	
	
	
	
    
//*****************Testing for MenuItemsController********************************
    
  @Autowired
  MenuItemsController controllerMenuItem;

  @Before
  public void setupMenuItem() {
      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }
//*****************Testing for MenuItemsController's getAllMenuItems method********************************    

  @Test
  public void getMenuItemsController_ThenReturnMenuItems() throws Exception {
      MvcResult result = mockMvc.perform(get("/menuitems")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
      	int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
      
		Assert.assertEquals(actual, expected);
     
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
		Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
//*****************Testing for MenuItemsController's getMenuItemById method********************************    

  @Test
  public void getMenuItemsController_ThenReturnSpecificMenuItems() throws Exception {
      MvcResult result = mockMvc.perform(get("/menuitems/m/1")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String databaseResponse = result.getResponse().getContentAsString().toString(); 
		System.out.println("*************************************");
		
		String json = Json.createObjectBuilder()
	            .add("menu_id", 1)
	            .add("itemName", "cheeseburger")
	            .add("description", "Saucy slice of beef with cheese")
	            .add("price", 10.00)
	            .build()
	            .toString();
		System.out.println(json);
		
		String actual = databaseResponse;
		
		String expected = json;
        
		Assert.assertEquals(actual, expected);

		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
		Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
  
//*****************Testing for MenuItemsController's addMenuItem method********************************    

	String jsonMenuItem = Json.createObjectBuilder()
			.add("menu_id", 1000)
            .add("itemName", "cheeseburger")
            .add("description", "Saucy slice of beef with cheese")
            .add("price", 100.0)
            .build()
            .toString();
	
	 	@Test 
	    public void AddMenuItem() throws Exception {
	    		    	
	        MvcResult result = mockMvc.perform(post("/menuitems") 
	        		.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonMenuItem))
	        		.andDo(print())
	        		.andExpect(status().isOk())
	                .andReturn();

			
			System.out.println(result.getResponse().getStatus());
			
			int actual = result.getResponse().getStatus(); 
			
			int expected = 200;
	        
			Assert.assertEquals(actual, expected);
			
	
	}
	 	
	 	
	 	
	 	
	 	//*****************Testing for MenuItemsController's addMenuItem method********************************    


		
		 	@Test 
		    public void DeleteMenuItemsById() throws Exception {
		    		    	
		        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/menuitems/r/1000") 
		        		.contentType(MediaType.APPLICATION_JSON_VALUE))
		        		.andDo(print())
		        		.andExpect(status().isOk())
		                .andReturn();

				
				System.out.println(result.getResponse().getStatus());
				
				int actual = result.getResponse().getStatus(); 
				
				int expected = 200;
		        
				Assert.assertEquals(actual, expected);
				
		
		}
		 	
//*****************Testing for OrdersController********************************
  
  @Autowired
  OrdersController controllerOrder;

//*****************Testing for OrdersController's getAllOrders method********************************

  @Test
  public void getOrdersController_ThenReturnOrder() throws Exception {
      MvcResult result = mockMvc.perform(get("/orders")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
      	int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
      
		Assert.assertEquals(actual, expected);
      
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
		Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
  
//*****************Testing for OrdersController's getOrdersById method********************************

  
  @Test
  public void getOrdersController_ThenReturnSpecificOrder() throws Exception {
      MvcResult result = mockMvc.perform(get("/orders/o/1")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		System.out.println("*************************************");
		
		int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
		
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
	    Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
	     	
//	    String databaseResponse = result.getResponse().getContentAsString().toString(); 
	    
//		JsonObjectBuilder jsonOrder = Json.createObjectBuilder();
//        jsonOrder.add("order_id", 1);
//        jsonOrder.add("orderType", "DELIVERY");
//        jsonOrder.add("status", "PENDING");
//        jsonOrder.add("customer_id", 1);
//        jsonOrder.add("deliveryAddress", "11 Delivery Lane");  
//        jsonOrder.add("billingAddress", "11 Billing lane");
//	
//		JsonArray itemsOrdered = Json.createArrayBuilder()
//		.add(Json.createObjectBuilder()
//		.add("menu_id", 2).add("itemName","burger").add("description","Saucy slice of beef with onions").add("price",100.0))
//		.add(Json.createObjectBuilder()
//		.add("menu_id", 3).add("itemName","hamburger").add("description","Saucy slice of beef with no cheese").add("price",100.0))
//		.add(Json.createObjectBuilder()
//		.add("menu_id", 1).add("itemName","cheeseburger").add("description","Saucy slice of beef with cheese").add("price",100.0))
//		.build();
//		jsonOrder.add("itemsOrdered", itemsOrdered);
//		JsonObject data = jsonOrder.build();
//		StringWriter sw = new StringWriter();
//		JsonWriter jw = Json.createWriter(sw);
//		jw.writeObject(data);
//		jw.close();
//		
//		System.out.println(sw.toString());
//		
//		
//		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&THIS IS EXPECTED VALUE &&&&&&&&&&&&&&&&&&&&");
//		String json = sw.toString();
//		
//		String actual = databaseResponse;
//		
//		String expected = json;
        	
  }
  
//*****************Testing for OrdersController's addOrders method********************************
  
  
  
	String jsonOrder = Json.createObjectBuilder()
	.add("orderType", "DELIVERY")
	.add("status", "PENDING")
	.add("customer_id", 1)
	.add("deliveryAddress", "11 Delivery Lane")
	.add("billingAddress", "11 Billing lane")
	.build()
	.toString();
  
  
  @Test
  public void getOrdersController_ThenAddOrder() throws Exception {
	  MvcResult result = mockMvc.perform(post("/orders") 
      		.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonOrder))
      		.andDo(print())
      		//.andExpect(status().isOk())
              .andReturn();

	  	System.out.println("******************THE STATUS*****************************");
		System.out.println(result.getResponse().getStatus()); 
		
	
		int actual = result.getResponse().getStatus();   
		
		int expected = 200;
        
		Assert.assertEquals(actual, expected);
		
	
      
}
 	

  
  
//*****************Testing for ReservationsController********************************
  
  @Autowired
  ReservationsController controllerReservation;

//*****************Testing for ReservationsController's getAllReservations method********************************
  @Test
  public void getReservationsController_ThenReturnReservation() throws Exception {
      MvcResult result = mockMvc.perform(get("/reservations")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
      	int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
      
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
		Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
//*****************Testing for ReservationsController's getCustomerById method ********************************
  
  @Test
  public void getReservationsController_ThenReturnSpecificReservation() throws Exception {
      MvcResult result = mockMvc.perform(get("/reservations/r/1")) // testing is done without the /api context of the DispatcherServlet f
      

      		// from web.xml. The tests startup their own context not from the
      		.andDo(print())
      		.andExpect(status().isOk())
              .andReturn();
      
		System.out.println(result.getResponse().getHeader("Content-Type"));
		System.out.println(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
		System.out.println(result.getResponse().getContentAsString());
		
		String databaseResponse = result.getResponse().getContentAsString().toString(); 
		System.out.println("*************************************");
		
		String json = Json.createObjectBuilder()
	            .add("reservation_id", 1)
	            .add("customer_id", 1)
	            .add("date", "10-10-2020")
	            .add("time", 12)
	            .add("status", "CANCELED")
	            .build()
	            .toString();
		System.out.println(json);
		
		String actual = databaseResponse;
		
		String expected = json;
        
		Assert.assertEquals(actual, expected);
		
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
		Assert.assertNotNull(result.getResponse().getHeader("Content-Type").equals("application/json;charset=UTF-8"));
     
  }
  
//*****************Testing for ReservationsController's addCustomer method ********************************
  
  String jsonReser = Json.createObjectBuilder()
          .add("customer_id", 1)
          .add("date", "10-10-2020")
          .add("time", 12)
          .add("status", "Cancelled")
          .build()
          .toString();
  
  @Test 
  public void getReservationsController_ThenAddReservations() throws Exception {
  	 	
      MvcResult result = mockMvc.perform(post("/customers") 
      		.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonReser))
      		.andDo(print())
      		.andExpect(status().isOk())
            .andReturn();

		
		System.out.println(result.getResponse().getStatus()); 
		
		int actual = result.getResponse().getStatus(); 
		
		int expected = 200;
      
		Assert.assertEquals(actual, expected);
		

  }
  
}