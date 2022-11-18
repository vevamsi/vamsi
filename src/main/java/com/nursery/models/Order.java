package com.nursery.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	private LocalDate orderDate;
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="paymentId")
	private Payment payment;
	private String paymethod;
	private String status;
	
	@OneToMany(mappedBy = "order" , fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
    private List<OrderDetails> details;
	
	
	public List<OrderDetails> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetails> details) {
		this.details = details;
	}
	
	public Order() {
		this.orderDate=LocalDate.now();
		this.status="Pending";
		this.paymethod="By Card";
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", orderDate=" + orderDate + ", customer=" + customer + ", address="
				 + ", payment=" + payment + ", paymethod=" + paymethod + ", status=" + status + "]";
	}

	
	
}
