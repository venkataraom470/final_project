package com.cg.BiteBeeFoodApplication.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.BiteBeeFoodApplication.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	public Product findByPname(String name);

}
