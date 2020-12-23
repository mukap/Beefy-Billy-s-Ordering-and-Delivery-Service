package com.restaurant.data;


import com.restaurant.models.MenuItems;
import com.restaurant.models.Orders;
import com.restaurant.models.Reservations;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import javax.annotation.PostConstruct;

import java.util.List;

/**
 * The ReservationsDAO manages all employee interactions with the Database utilizing hibernate
 *
 * @author Justin Kroh
 * */
@Repository
@Transactional
public class ReservationsDAO {

    private SessionFactory sessionFactory;

    
    /*
    @PostConstruct
    @Transactional
    public void initDB(){
        Reservations r = new Reservations();

        r.setCustomer_id(1);
        r.setDate("12/15/2020");
        r.setTime(12);

        Session session = sessionFactory.openSession();
        session.save(r);
    }
    
    */

    
    /**
     * Constructor Dependency Injection gets session factory from Spring IOC Container
     *
     * @author Justin Kroh
     * */
    @Autowired
    public ReservationsDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Reservation DAO");
        this.sessionFactory = sessionFactory;
    }

    
	/**
	 * Adds a Reservation in the Database
	 * @param Reservations Object see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Reservations Object, see Model
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(Reservations r) {
        Session session = sessionFactory.getCurrentSession();
        session.save(r);
    }

    
	/**
	 * Gets a Reservations Object by ID in the Database
	 * @param Orders see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Reservations Object, see Model
	 * */
    @Transactional(readOnly = true)
    public Reservations getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Reservations) session.get(Reservations.class, id);
    }
    
    
	/**
	 * Returns a list of reservations objects in the database
	 * @param Orders see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return List of Reservations Object, see Model
	 * */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Reservations> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Reservations");
        return hql.list();
    }

    
    /**
	 * Updates the status field of an Reservations, CANCELED, BOOKED
	 * @param ID of order to get
	 * @status the status to change to
	 * 
	 * @author Ronald Martz
	 * 
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void changeReservation(Integer id, String status){
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("Update Reservations set status = :status where reservation_id = :id");
        hql.setString("status",status);
        hql.setInteger("id",id);
        hql.executeUpdate();
    }
}
