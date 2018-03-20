package com.almundo.callcenter.model;

/**
 * @author Wilson Salamanca
 *
 */
public class Call {
	private int id;
	private String customerName;
	private Double duration;
	
	
		
	public Call() {}

	public Call(int id, String customerName) {
		super();
		this.id = id;
		this.customerName = customerName;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
}
