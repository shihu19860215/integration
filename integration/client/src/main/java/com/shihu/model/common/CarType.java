package com.shihu.model.common;

import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CarType {
	private Long id;
	private String name;
	private List<CarVO> carVOs;


	public CarType() {
	}

	public CarType(CarTypeVO carTypeVO, List<CarVO> carVOs) {
		this.id = carTypeVO.getId();
		this.name=carTypeVO.getName();
		this.carVOs=carVOs;
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

	public List<CarVO> getCarVOs() {
		return carVOs;
	}

	public CarType setCarVOs(List<CarVO> carVOs) {
		this.carVOs = carVOs;
		return this;
	}
}
