package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDto;
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
 public CompletableFuture<ResponseEntity<OrderDto>> saveOrder(@RequestBody OrderDto order) {
     return orderService.createOrder1(order)
                        .thenApply(ResponseEntity::ok);
 }
 
// @PostMapping
// public CompletableFuture<OrderDto>  saveOrder(@RequestBody  OrderDto order)
// {
//	 return CompletableFuture.supplyAsync(() -> { ResponseEntity.ok( orderService.createOrder1(order)); });
// }
 
 @GetMapping
 public ResponseEntity<List<OrderDto>> listOfOrders()
 {
	 return ResponseEntity.ok( orderService.getAllOrders1());
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id)
 {
	 return  ResponseEntity.ok( orderService.getOrderById(id));
 }
 
 
 @PutMapping("{id}")
 public  OrderDto updateOrder(@PathVariable("id") Long id ,@RequestBody OrderDto orderDto)
 {
	 
	 return orderService.updateOrder(id,orderDto);
	 
 }
 
 @DeleteMapping("{id}")
 public String deleteOrder(@PathVariable("id") Long id)
 {
	  orderService.deleteOrder(id);
	 
	 return "Order Deleted Successfully";
	 
 }
 

/* @PostMapping
 public ResponseEntity<Order>  saveOrder(@RequestBody  Order order)
 {
	 return ResponseEntity.ok( orderService.createOrder(order));
 }
 
 @GetMapping
 public ResponseEntity<List<OrderDTO>> listOfOrders()
 {
	 return ResponseEntity.ok( orderService.getAllOrders());
 }
 */
}

