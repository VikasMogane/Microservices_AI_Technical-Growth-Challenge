package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
  private final OrderRepository orderRepository;
  
  public OrderService(OrderRepository orderRepository)
  {
	  this.orderRepository = orderRepository;
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
 

}
