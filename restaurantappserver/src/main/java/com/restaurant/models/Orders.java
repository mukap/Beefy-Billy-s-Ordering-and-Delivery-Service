package com.restaurant.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id",  columnDefinition="serial primary key")	
	private int order_id;
	
	/*
	@Column(name = "date")
	private String date;
	
	*/
	@Column(name = "order_type")
	private String orderType;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "customer_id")
	private int customer_id;
	
	@Column(name = "delivery_address")
	private String deliveryAddress;
	
	@Column(name = "billing_address")
	private String billingAddress;
	
	
	//Changing this to merged solve the issue of multiple same menu items in the same session
    @ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
    // @JoinTable informs hibernate of the junction table that hosts the many-to-many relationship
    //
    @JoinTable(name="order_items",
        joinColumns=@JoinColumn(name="order_id", referencedColumnName="order_id"),
        inverseJoinColumns = @JoinColumn(name="menu_id", referencedColumnName="menu_id")
    )
    private List<MenuItems> itemsOrdered;
	
	
	
	public Orders() {
		
	}



	public int getOrder_id() {
		return order_id;
	}



	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}






	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getCustomer_id() {
		return customer_id;
	}



	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}



	public String getDeliveryAddress() {
		return deliveryAddress;
	}



	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}



	public String getBillingAddress() {
		return billingAddress;
	}



	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}



	public List<MenuItems> getItemsOrdered() {
		return itemsOrdered;
	}



	public void setItemsOrdered(List<MenuItems> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}








	
	
	
	
}
