package com.example.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.PaymentDTO;
import com.example.demo.entity.Payment;
import com.example.demo.events.OrderEvent;
import com.example.demo.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class PaymentService {
	
 private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
	
 @Autowired
 private  PaymentRepository paymentRepository;
 private  RestTemplate restTemplate;
 
 @Autowired
 private  KafkaTemplate<String, OrderEvent> kafkaEvent;

//  
// public PaymentService(PaymentRepository paymentRepository)
// {
//	 this.paymentRepository = paymentRepository;
//	 this.restTemplate = new RestTemplate();
// }

 public PaymentService(PaymentRepository paymentRepository,KafkaTemplate<String, OrderEvent> kafkaEvent)
 {
	 this.paymentRepository = paymentRepository;
	 this.restTemplate      = new RestTemplate();
	 this.kafkaEvent        = kafkaEvent;
 }
 public PaymentDTO makePayment(PaymentDTO paymentDTO) {
     // ✅ Call Order-Service to validate order
     String orderServiceUrl = "http://localhost:8081/orders/" + paymentDTO.getOrderId();
     OrderDTO order = restTemplate.getForObject(orderServiceUrl, OrderDTO.class);

     if (order == null) {
         throw new RuntimeException("Order not found. Cannot make payment.");
     }

     Payment payment = new Payment();
     payment.setOrderId(paymentDTO.getOrderId());
     payment.setAmount(paymentDTO.getAmount());
     payment.setPaymentStatus("SUCCESS");
     payment.setTransactionId(UUID.randomUUID().toString()); // ✅ unique transaction id

     Payment saved = paymentRepository.save(payment);

     // ✅ Update Order status after successful payment
  //   order.setOrderStatus("PAID");
   //  restTemplate.put(orderServiceUrl, order);

     return new PaymentDTO(
             saved.getPaymentId(),
             saved.getOrderId(),
             saved.getAmount(),
             saved.getPaymentStatus(),
             saved.getTransactionId()
     );
 }
 
 @KafkaListener(topics = "order-events", groupId = "payment-group")
 public void consumeOrderEvent(OrderEvent orderEvent)
 {
	    log.info(">>> Received OrderEvent in payment-service: {}", orderEvent);
        log.debug("OrderEvent details - orderId: {}, userId: {}, amount: {}, status: {}",
                orderEvent.getOrderId(),
                orderEvent.getUserId(),
                orderEvent.getAmount(),
                orderEvent.getStatus());
	 System.out.println("Receive Order Event "+ orderEvent);
	 
	 Payment paymentDto = new Payment();
	 paymentDto.setOrderId(orderEvent.getOrderId());
	 paymentDto.setAmount(orderEvent.getAmount());
	 paymentDto.setPaymentStatus(orderEvent.getStatus());
	 paymentDto.setTransactionId(UUID.randomUUID().toString());
	 
	 Payment savePayment = paymentRepository.save(paymentDto);
	 
	 // payment Done for Order Id
     log.info("Payment saved for OrderId -> {} (paymentId: {})", savePayment.getOrderId(), savePayment.getPaymentId());

	 
	 
 }

 public List<PaymentDTO> getAllPayments() {
     return paymentRepository.findAll()
             .stream()
             .map(p -> new PaymentDTO(
                     p.getPaymentId(),
                     p.getOrderId(),
                     p.getAmount(),
                     p.getPaymentStatus(),
                     p.getTransactionId()
             ))
             .collect(Collectors.toList());
 }
	public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
		
		Payment payment = paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("Payment Not Found For This Id"));
		
		payment.setAmount(paymentDTO.getAmount());
		payment.setTransactionId(UUID.randomUUID().toString());
		
		Payment savedPayment = paymentRepository.save(payment);
		
		return new PaymentDTO(savedPayment.getOrderId(), savedPayment.getPaymentId(), savedPayment.getAmount(), savedPayment.getPaymentStatus(), savedPayment.getTransactionId());
	}
	public String deletePayment(Long id)
	{
			
		paymentRepository.findById(id).orElseThrow(()-> new RuntimeException("Payment Not Found For This Id"));
		
		paymentRepository.deleteById(id);
		
		return  "Payment Deleted Successfully";
    }
 
 /* public Payment doPayment(Payment payment)
 {
	 payment.setTransactionId(UUID.randomUUID().toString());
	 payment.setPaymentStatus("Payment Done");
	 
	 return paymentRepository.save(payment);
 }
 
 public List<Payment> getAllPaymentList()
 {
	 return paymentRepository.findAll();
 } */
 
}
