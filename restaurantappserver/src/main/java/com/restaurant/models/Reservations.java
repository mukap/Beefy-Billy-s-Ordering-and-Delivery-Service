package com.restaurant.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "reservations")
public class Reservations {

	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reservation_id",  columnDefinition="serial primary key")	
	private int reservation_id;
	
	@Column(name = "customer_id")
	private int customer_id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "time")
	private int time;

	@Column(name = "status")
	private String status;
	
	
	
	public Reservations() {
	
	}



	public int getReservation_id() {
		return reservation_id;
	}



	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}



	public int getCustomer_id() {
		return customer_id;
	}



	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public int getTime() {
		return time;
	}



	public void setTime(int time) {
		this.time = time;
	}



	public String getStatus() { return status; }



	public void setStatus(String status) { this.status = status; }
}
