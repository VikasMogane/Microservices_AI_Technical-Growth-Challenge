package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PaymentDTO;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}
	
	/* @PostMapping
	public Payment doPayment(@RequestBody Payment payment)
	{
		return paymentService.doPayment(payment);
	}
	
	@GetMapping
	public List<Payment> getAllPaymentList()
	{
		return paymentService.getAllPaymentList();
		
	} */
	

  @PostMapping
    public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.makePayment(paymentDTO));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
	

}
