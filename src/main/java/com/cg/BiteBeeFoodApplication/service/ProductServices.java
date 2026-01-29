package com.cg.BiteBeeFoodApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.BiteBeeFoodApplication.entity.Product;
import com.cg.BiteBeeFoodApplication.repositry.ProductRepository;

@Service
public class ProductServices {
	@Autowired
	private ProductRepository productRepository;

	public void addProduct(Product p) {
		this.productRepository.save(p);
	}

	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>) this.productRepository.findAll();
		return products;
	}

	public Product getProduct(int id) {
		Product product = this.productRepository.findById(id).get();
		return product;
	}

	public void deleteProduct(int id) {
		this.productRepository.deleteById(id);
	}

	public Product getProductByName(String name) {
		Product product = this.productRepository.findByPname(name);
		if (product != null) {
			return product;
		}
		return null;
	}

	public void updateProduct(Product p, int id) {
		p.setPid(id);
		Product prod = this.productRepository.findById(id).get();
        if(prod.getPid()==id)
        {
        	this.productRepository.save(p);
        }
	}

}
