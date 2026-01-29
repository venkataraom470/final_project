package com.cg.BiteBeeFoodApplication.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.BiteBeeFoodApplication.entity.Orders;
import com.cg.BiteBeeFoodApplication.entity.User;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
	
	public List<Orders> findOrdersByUser(User user);

}
