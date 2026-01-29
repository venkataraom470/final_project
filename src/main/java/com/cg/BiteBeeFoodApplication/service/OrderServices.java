package com.cg.BiteBeeFoodApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.BiteBeeFoodApplication.entity.Orders;
import com.cg.BiteBeeFoodApplication.entity.User;
import com.cg.BiteBeeFoodApplication.repositry.OrderRepository;

@Service
public class OrderServices {
	@Autowired
	private OrderRepository orderRepository;
	
	public void saveOrder(Orders order)
	{
		this.orderRepository.save(order);
	}
	
	public void updateOrder(int id,Orders order)
	{
		order.setoId(id);
		this.orderRepository.save(order);
	}
	public void deleteOrder(int id)
	{
		this.orderRepository.deleteById(id);
	}
	public List<Orders>getOrders()
	{
		List<Orders>list=this.orderRepository.findAll();
		return list;
	}
	public List<Orders>getOrdersForUser(User user)
	{
		return this.orderRepository.findOrdersByUser(user);
	}

}
