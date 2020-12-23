package com.restaurant.data;

import java.util.List;

//import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.models.Employee;

/**
 * The EmployeeDAO manages all employee interactions with the Database utilizing hibernate
 *
 * @author Justin Kroh
 * */
@Repository
@Transactional
public class EmployeeDAO {

	private SessionFactory sessionFactory;

	/*
    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {
//        Employee e = new Employee();
//        e.setEmail("test5@email.com");
//        e.setPassword("testpassword");
//        e.setFirstName("test");
//        e.setLastName("test");
//        e.setAddress("test");
//        e.setUser_type("EMPLOYEE");
//
//        
//       Session session = sessionFactory.openSession();
//       session.save(e);
    }

*/
    /**
     * Constructor Dependency Injection gets session factory from Spring IOC Container
     *
     * @author Justin Kroh
     * */
    @Autowired
    public EmployeeDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Employee DAO");
        this.sessionFactory = sessionFactory;
    }

    
	/**
	 * Adds a employee in the Database
	 * @param Employee Object see model
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Employee Object, see Model
	 * */
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public Employee save(Employee e) {
        Session session = sessionFactory.getCurrentSession();
        session.save(e);
        return e;
    }

    
	/**
	 * Returns a employee object in the Database by ID
	 * @param id of Employee
	 * 
	 * @author Justin Kroh
	 * 
	 * @return Customer Object, see Model
	 * */
    @Transactional(readOnly = true)
    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Employee) session.get(Employee.class, id);
    }

	/**
	 * Removes a EmployeeItem in the Database
	 * @param EmployeeItem m see model
	 *
	 * @author Ronald Martz
	 * */
	@Transactional
	public void remove(Integer m){
		Session session = sessionFactory.getCurrentSession();
		Query hql = session.createQuery("delete from Employee where employee_id=:id");
		hql.setInteger("id",m);
		hql.executeUpdate();
	}
    

    
	/**
	 * Returns all Employees in the Database
	 *

	 * @author Justin Kroh
	 * 
	 * @return List of Employee Objects, see Model
	 * */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Employee> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Employee");
        return hql.list();
    }
    
    
	/**
	 * Returns an Employee object after checking the credentials of employee from filling out a login form
	 * 
	 *
	 * @author Justin Kroh
	 * 
	 * @return an Employee if credentials exist and are accurate
	 * */
    public Employee checkCredentials(String email, String password) {
    	
    	boolean correctCredentials = false;
    	List<Employee> employeeList = null;
    	Employee employee = null;
    	
    	Session s = sessionFactory.getCurrentSession();
    	
    	employeeList = s.createQuery("FROM Employee").list(); 
        for (Employee e: employeeList){ 
       	 
       	 if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
       		 
       		 correctCredentials = true;
       		 System.out.println("GOT CORRECT CREDENTIALS");
       		 employee = e;
       		 break;
       		 
       	 }
       	 else System.out.println("FAILURE TO GET CORRECT CREDENTIALS");
        }
    	
        System.out.println("did it break");
        System.out.println(correctCredentials);
    	
    	return employee;
    }
    

    
	
}
