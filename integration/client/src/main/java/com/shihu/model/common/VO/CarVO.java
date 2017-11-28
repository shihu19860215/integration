package com.shihu.model.common.VO;

import com.shihu.model.common.CarType;
import com.shihu.model.common.Product;

import java.util.Set;

public class CarVO {
	private Long id;
	private String name;
	private Long carTypeId;

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

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}
}
