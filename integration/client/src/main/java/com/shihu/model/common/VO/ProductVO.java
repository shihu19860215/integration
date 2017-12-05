package com.shihu.model.common.VO;

import com.shihu.base.Base;

import java.util.List;

public class ProductVO {
	private Long id;
	private String name;
	private int num;
	private String version;
	private Integer ownerprice;
	private Integer otherprice;
	private String remark;
	private String carStr;

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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getOwnerprice() {
		return ownerprice;
	}

	public void setOwnerprice(Integer ownerprice) {
		this.ownerprice = ownerprice;
	}

	public Integer getOtherprice() {
		return otherprice;
	}

	public void setOtherprice(Integer otherprice) {
		this.otherprice = otherprice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCarStr() {
		return carStr;
	}

	public void setCarStr(String carStr) {
		this.carStr = carStr;
	}

	public void setCarStr(List<CarVO> cars) {
		this.carStr = Base.gson.toJson(cars);
	}
}
