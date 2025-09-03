package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
 private final OrderService orderService;
 
 public OrderController(OrderService orderService)
 {
	 this.orderService = orderService;
 }


 @PostMapping
 public ResponseEntity<Order>  saveOrder(@RequestBody  Order order)
 {
	 return ResponseEntity.ok( orderService.createOrder(order));
 }
 
 @GetMapping
 public ResponseEntity<List<Order>> listOfOrders()
 {
	 return ResponseEntity.ok( orderService.getAllOrders());
 }
 
}

