package com.restaurant.models;

import java.util.HashSet;
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
@Table(name = "menu_items")
public class MenuItems {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "menu_id",  columnDefinition="serial primary key")	
	private int menu_id;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private float price;
	
	
	/*
	
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    // @JoinTable informs hibernate of the junction table that hosts the many-to-many relationship
    //
    @JoinTable(name="order_items",
        joinColumns=@JoinColumn(name="menu_id", referencedColumnName="menu_id"),
        inverseJoinColumns = @JoinColumn(name="order_id", referencedColumnName="order_id")
    )
    private Set<Orders> orderedItems;
	
	*/
	

//	@ManyToMany(mappedBy = "itemsOrdered")
//	private Set<Orders> orders = new HashSet<>();
//	

	
	public MenuItems() {

	}
	
	
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}


	/*
	public Set<Orders> getOrders() {
		return orders;
	}


	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
	
	
	*/
	
	
	
}
