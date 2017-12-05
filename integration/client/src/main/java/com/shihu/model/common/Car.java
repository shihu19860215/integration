package com.shihu.model.common;

import com.shihu.model.common.VO.CarVO;

import java.util.Set;

public class Car {
	private Long id;
	private String name;
	private CarType carType;
	private Set<Product> products;

	public Car() {
	}

	public Car(CarVO carVO) {
		this.id = carVO.getId();
		this.name =carVO.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
