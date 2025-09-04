package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentService {
	
	
 private final PaymentRepository paymentRepository;
  
 public PaymentService(PaymentRepository paymentRepository)
 {
	 this.paymentRepository = paymentRepository;
 }
 
 public Payment doPayment(Payment payment)
 {
	 payment.setTransactionId(UUID.randomUUID().toString());
	 payment.setPaymentStatus("Payment Done");
	 
	 return paymentRepository.save(payment);
 }
 
 public List<Payment> getAllPaymentList()
 {
	 return paymentRepository.findAll();
 }
 
}
