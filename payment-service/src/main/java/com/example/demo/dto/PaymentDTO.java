package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private Long orderId;
    private Double amount;
    private String paymentStatus;
    private String transactionId;
    
    public PaymentDTO() {
		
		// TODO Auto-generated constructor stub
	}
    
	public PaymentDTO(Long paymentId, Long orderId, Double amount, String paymentStatus, String transactionId) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.transactionId = transactionId;
	}
	
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
    
    
}
