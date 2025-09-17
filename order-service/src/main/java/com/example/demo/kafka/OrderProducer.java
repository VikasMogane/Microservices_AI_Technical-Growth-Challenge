package com.example.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.events.OrderEvent;

@Service
public class OrderProducer {
	
	private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate )
	{
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendOrderEvent(OrderEvent orderEvent)
	{
		kafkaTemplate.send("order-events",orderEvent);
		System.out.println("order-event sent from orderproducer");
	}

}
