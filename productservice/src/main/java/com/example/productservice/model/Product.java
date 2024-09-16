package com.example.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long id;
	private String name;
	private Double price;

//	public Product() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Product(Long id, String name, Double price) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.price = price;
//	}
//
////	public Object getId() {
////		// TODO Auto-generated method stub
////		return null;
////	}
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}
}
