package com.restaurant;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.restaurant.data.CustomerDAO;
import com.restaurant.models.Customer;
import com.restaurant.utils.DatabaseConnection;


/**
 * This class locates the application context
 * 
 * */
@Component
@EnableWebMvc
public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
        ApplicationContext ac = new ClassPathXmlApplicationContext("WEB-INF/application-context.xml");
        Main main = ac.getBean(Main.class);


        ((AbstractApplicationContext)ac).close();
	}
	
}
