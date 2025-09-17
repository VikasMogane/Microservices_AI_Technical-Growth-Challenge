package com.example.demo.events;

import lombok.Data;

@Data
public class OrderEvent {
	
	private Long orderId;
	private Long userId;
	private Double amount;
	private String status;
	
	public OrderEvent() {
		super();
	}

	public OrderEvent(Long orderId, Long userId, Double amount, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
