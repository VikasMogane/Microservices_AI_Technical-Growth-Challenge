package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

	private Long orderId;
	
	private Long userId;
	private Double amount;
	private String orderStatus;
	
	public OrderDto()
	{
		
	}
	
	public OrderDto(Long orderId, Long userId, Double amount, String orderStatus) {
	
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.orderStatus = orderStatus;
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
