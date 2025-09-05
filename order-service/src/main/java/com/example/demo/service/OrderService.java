package com.example.demo.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

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
  
  public OrderDto createOrder1(OrderDto orderDto)
  {
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
  

}
