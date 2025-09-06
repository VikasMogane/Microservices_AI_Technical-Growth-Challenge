package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class OrderDTO {
    private Long id;         // orderId from Order-Service
    private Long userId;
    private Double amount;
    private String orderStatus;
    
    public OrderDTO()
    {
    	
    }
    
	public OrderDTO(Long id, Long userId, Double amount, String orderStatus) {
		super();
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.orderStatus = orderStatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
    
    
}
