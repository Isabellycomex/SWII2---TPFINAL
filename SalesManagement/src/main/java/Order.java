/*
	Isabelly Barbosa Gonçalves CB3021467
	Lucas Aragão Almeida CB3013146
 */

import java.time.LocalDateTime;

public class Order {
	private int id;
	private float purchaseAmount;
	private LocalDateTime createdAt;
	private int salesmanId;
	private int customerId;
	
	public Order() {
		
	}
	
	public Order(int id) {
		this.id = id;
	}
	
	public Order(float purchaseAmount, int salesman, int customer) {
		this.purchaseAmount = purchaseAmount;
		this.salesmanId = salesman;
		this.customerId = customer;
	}
	
	public Order(int id, float purchaseAmount, int salesman, int customer) {
		this.id = id;
		this.purchaseAmount = purchaseAmount;
		this.salesmanId = salesman;
		this.customerId = customer;
	}
	
	public Order(int id, float purchaseAmount, LocalDateTime createdAt, int salesman, int customer) {
		this.id = id;
		this.purchaseAmount = purchaseAmount;
		this.createdAt = createdAt;
		this.salesmanId = salesman;
		this.customerId = customer;
	}
	
	public int getId() {
		return id;
	}

	public float getPurchaseAmount() {
		return purchaseAmount;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public int getSalesmanId() {
		return salesmanId;
	}

	public int getCustomerId() {
		return customerId;
	}
}
