package com.restaurant.data;

import java.awt.*;
import java.util.List;

//import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.models.MenuItems;


/**
 * The MenuItemsDAO manages all employee interactions with the Database utilizing hibernate
 *
 * @author Justin Kroh
 * */
@Repository
@Transactional
public class MenuItemsDAO {

	
    //private final PilotRepo pilotRepo;
    private SessionFactory sessionFactory;

    
    /*
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {
        MenuItems m = new MenuItems();
        m.setItemName("Burger");
        m.setDescription("Greazy");
        m.setPrice(100);


        Session session = sessionFactory.openSession();
        session.save(m);
        

    }

	*/
    
    
    /**
     * Constructor Dependency Injection gets session factory from Spring IOC Container
     *
     * @author Justin Kroh
     * */
    
    
    @Autowired
    public MenuItemsDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Menu Items DAO");
        this.sessionFactory = sessionFactory;
    }

    
	/**
	 * Adds a MenuItem in the Database
	 * @param Employee Object see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Employee Object, see Model
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(MenuItems m) {
        Session session = sessionFactory.getCurrentSession();
        session.save(m);
    }

    
	/**
	 * Removes a MenuItem in the Database
	 * @param MenuItem m see model
	 * 
	 * @author Ronald Martz
	 * */
    @Transactional
    public void remove(Integer m){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("delete from MenuItems where menu_id=:id");
        hql.setInteger("id",m);
        hql.executeUpdate();
    }

    /**
     * Updates a Menu Item, name, description, and price
     * @param ID of menuItem to get
     *
     * @param NAME of new Menu Item Name
     * @param DESC of new Menu Item Description
     * @param PRICE of new Menu Item Price
     *
     * @author Ronald Martz
     *
     * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void changeItem(Integer id, String name, String desc, Integer price){
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("Update MenuItems set item_name = :itemName, description = :desc, price = :price where menu_id = :id");
        hql.setString("itemName",name);
        hql.setInteger("id",id);
        hql.setString("desc", desc);
        hql.setInteger("price", price);

        hql.executeUpdate();
    }

    
	/**
	 * Removes a MenuItem in the Database
	 * @param MenuItem m see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return MenuItem, see Model
	 * */
    @Transactional(readOnly = true)
    public MenuItems getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (MenuItems) session.get(MenuItems.class, id);
    }

    
	/**
	 * Gets all MenuItems in the Database
	 * @param MenuItem m see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return MenuItem, see Model
	 * */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<MenuItems> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From MenuItems");
        return hql.list();
    }
	
	
}
