package com.restaurant.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.restaurant.models.Orders;
import com.restaurant.models.MenuItems;


/**
 * The OrdersDAO manages all employee interactions with the Database utilizing hibernate
 *
 * @author Justin Kroh
 * */
@Repository
@Transactional
public class OrdersDAO {

	
    //private final PilotRepo pilotRepo;
    private SessionFactory sessionFactory;
    private final MenuItemsDAO menuItemsDAO;

    /*
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {
    	
    	/*
        Orders o = new Orders();

        MenuItems burger = new MenuItems();
        MenuItems fries = new MenuItems();
        MenuItems coke = new MenuItems();

        burger.setItemName("Burger");
        burger.setDescription("Greazy");
        burger.setPrice(100);

        fries.setItemName("f");
        fries.setDescription("des");
        fries.setPrice(100);

        coke.setItemName("coke");
        coke.setDescription("desc");
        coke.setPrice(100);
        
        //Set<Orders> orders = new HashSet<Orders>();
        
        //orders.add(getById(1));
        
        //burger.setOrders(orders);
        
        Set<MenuItems> itemsOrdered = new HashSet<>();
        
        itemsOrdered.add(burger);
        itemsOrdered.add(fries);
        itemsOrdered.add(coke);

        System.out.println(itemsOrdered);

        o.setOrderType("DELIVERY");
        o.setStatus("PENDING");
        o.setDeliveryAddress("11 LALA LANE");
        o.setBillingAddress("11 LALA LANE");
        o.setCustomer_id(1);
        o.setItemsOrdered(itemsOrdered);
		*/
    
    	/*
    	
        Orders o2 = new Orders();



        Set<MenuItems> itemsOrdered2 = new HashSet<>();

        itemsOrdered2.add(menuItemsDAO.getById(1));
        itemsOrdered2.add(menuItemsDAO.getById(2));
        itemsOrdered2.add(menuItemsDAO.getById(3));

        System.out.println(itemsOrdered2);

        o2.setOrderType("DELIVERY");
        o2.setStatus("PENDING");
        o2.setDeliveryAddress("11 LALA LANE");
        o2.setBillingAddress("11 LALA LANE");
        o2.setCustomer_id(2);
        o2.setItemsOrdered(itemsOrdered2);

        Session session = sessionFactory.openSession();
        
       
        //session.save(burger);
     

       session.save(o2);
        //session.save(o2);
         * 
         * 
         * 

    }

	*/

    
    /**
     * Constructor Dependency Injection gets session factory from Spring IOC Container
     *
     * @author Justin Kroh
     * */
    @Autowired
    public OrdersDAO(SessionFactory sessionFactory, MenuItemsDAO menuItemsDAO) {
        System.out.println("Creating Orders DAO");
        this.sessionFactory = sessionFactory;
        this.menuItemsDAO = menuItemsDAO;
    }

    
	/**
	 * Adds a Order in the Database
	 * @param Orders see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Orders Object, see Model
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(Orders o) {
        Session session = sessionFactory.getCurrentSession();
        session.save(o);
    }

    
	/**
	 * Gets an order by Id
	 * @param ID of order to get
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Orders Object, see Model
	 * */
    @Transactional(readOnly = true)
    public Orders getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Orders) session.get(Orders.class, id);
    }

    
	/**
	 * Gets a list of Orders
	 * @param ID of order to get
	 * 
	 * @author Justin Kroh
	 * 
	 * @return List of Orders Object, see Model
	 * */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Orders> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Orders");
        return hql.list();
    }
    
    
	/**
	 * Updates the status field of an Order, CANCELED, PENDING, DELIVERED
	 * @param ID of order to get
	 * @status the status to change to, CANCELED, PENDING, DELIVERED
	 * 
	 * @author Ronald Martz, Justin Kroh
	 * 
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void updateOrderStatus(Integer id, String status){
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("Update Orders set status = :status where order_id = :id");
        hql.setString("status",status);
        hql.setInteger("id",id);
        hql.executeUpdate();
        
    }
	
	
}
