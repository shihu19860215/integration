package com.shihu.model.common.VO;

import com.shihu.model.common.Car;

import java.util.Set;

public class ProductVO {
	private Long id;
	private String name;
	private int num;
	private String version;
	private int ownerprice;
	private int otherprice;
	private String remark;

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

	public int getOwnerprice() {
		return ownerprice;
	}

	public void setOwnerprice(int ownerprice) {
		this.ownerprice = ownerprice;
	}

	public int getOtherprice() {
		return otherprice;
	}

	public void setOtherprice(int otherprice) {
		this.otherprice = otherprice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
