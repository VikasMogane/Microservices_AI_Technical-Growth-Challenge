package com.example.demo.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@Service
public class OrderService {
	
	  private final OrderRepository orderRepository;
	  private final RestTemplate restTemplate;
	  
	  
	  public OrderService(OrderRepository orderRepository)
	  {
		 this.orderRepository = orderRepository;
		 this.restTemplate = new RestTemplate();	  
	  }
	  
	  public Order createOrder(Order order)
	  {
		  order.setOrderStatus("Order Created");
		 return  orderRepository.save(order);
	  }
	  
	  public List<Order> getAllOrders()
	  {
		  return orderRepository.findAll();
	  }
	  
	  
	  @CircuitBreaker(name = "userservice", fallbackMethod = "userServiceFallback")
	  @Retry(name = "userservice", fallbackMethod = "userServiceFallback")
	  //@TimeLimiter(name = "userservice")
	  public CompletableFuture<OrderDto> createOrder1(OrderDto orderDto) {
			    return CompletableFuture.supplyAsync(() -> {
	      // ✅ Call User-Service to check if user exists
	
		  String userServiceUrl = "http://localhost:8080/users/"+orderDto.getUserId();
		  UserDto user          = restTemplate.getForObject(userServiceUrl, UserDto.class);
		  
		  if(user == null)
		  {
			  throw new RuntimeException("User Not Found");
		  }
		  
		  Order order  = new Order();
		 // order.setOrderId(orderDto.getOrderId());
		  order.setUserId(orderDto.getUserId());
		  order.setAmount(orderDto.getAmount());
		  order.setOrderStatus("Order Created");
		  
		  Order saveOrder = orderRepository.save(order);  
		  
		  return new OrderDto(saveOrder.getOrderId(), saveOrder.getUserId(), saveOrder.getAmount(), saveOrder.getOrderStatus());
			    });
		  }
	  
	  
	  public CompletableFuture<OrderDto> userServiceFallback(OrderDto orderDto,  Throwable t)
	  {
		  OrderDto fallbackOrder = new OrderDto(null, orderDto.getUserId(), orderDto.getAmount(), "⚠️ User-service Unavailable, order kept in pending state");
		  
		  return CompletableFuture.completedFuture(fallbackOrder);
	  }
	  
	  public List<OrderDto> getAllOrders1()
	  {
		  return orderRepository.findAll().stream().map(o -> new OrderDto( o.getOrderId(),o.getUserId(),o.getAmount(),o.getOrderStatus() ))
				  .collect(Collectors.toList());
	  }
	  
	  public OrderDto getOrderById(Long id)
	  {
		  Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
		  
		  return new OrderDto(order.getOrderId(),order.getUserId(), order.getAmount(), order.getOrderStatus());
	  }

	 public OrderDto updateOrder(Long id, OrderDto orderDto) 
	 {
		Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order Not Found"));
		
		order.setAmount(orderDto.getAmount());
		order.setOrderStatus("Order Updated");
		
		Order updatedOrder = orderRepository.save(order);
		 
		return new OrderDto(updatedOrder.getOrderId(),updatedOrder.getUserId(), updatedOrder.getAmount(),updatedOrder.getOrderStatus());
	 }

	 public void deleteOrder(Long id) 
	 {
		 orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order Not Found"));
		 
		 orderRepository.deleteById(id);
		
	 }
	  

}
