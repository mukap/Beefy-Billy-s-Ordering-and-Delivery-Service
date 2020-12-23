package com.restaurant.data;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.models.Customer;

//import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;


/**
 * The CustomerDAO manages all customer interactions with the Database utilizing hibernate
 *
 * @author Justin Kroh
 * */
@Repository
@Transactional
public class CustomerDAO {
    //private final PilotRepo pilotRepo;
    private SessionFactory sessionFactory;

    /*
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {

    }
    
    */

    
    /**
     * Constructor Dependency Injection gets session factory from Spring IOC Container
     *
     * @author Justin Kroh
     * */
    @Autowired
    public CustomerDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Customer DAO");
        this.sessionFactory = sessionFactory;
    }

	/**
	 * Adds a customer in the Database
	 * @param Customer Object see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Customer Object, see Model
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public Customer save(Customer c) {
        Session session = sessionFactory.getCurrentSession();
        session.save(c);
        return c;
    }
    
	/**
	 * Deletes a customer in the Database
	 * @param Customer Object see model
	 * 
	 * @author Justin Kroh
	 * 
	 * 
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void delete(Customer c) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(c);
    }
    
	/**
	 * Deletes a customer in the Database byID
	 * @param id Id of Customer
	 * 
	 * @author Justin Kroh
	 * 
	 * 
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        
        Customer deletedCustomer = getById(id);
        
        session.delete(deletedCustomer);
    }
    

	/**
	 * Returns a customer in the Database by ID
	 * @param id of customer
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Customer Object, see Model
	 * */
    @Transactional(readOnly = true)
    public Customer getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }

    
	/**
	 * Returns all Customers in the Database
	 *
	 * 
	 * @author Justin Kroh
	 * 
	 * @return List of Customer Objects, see Model
	 * */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Customer> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Customer");
        return hql.list();
    }
}