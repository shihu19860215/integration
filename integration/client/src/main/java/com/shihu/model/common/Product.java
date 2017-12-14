package com.shihu.model.common;

import com.google.gson.reflect.TypeToken;
import com.shihu.base.Base;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private Long id;
	private String name;
	private int num;
	private String version;
	private Integer ownerprice;
	private Integer otherprice;
	private String remark;
	private List<CarVO> carVOs;

	public Product() {
	}
	public Product(ProductVO productVO) {
		this.id=productVO.getId();
		this.name=productVO.getName();
		this.num=productVO.getNum();
		this.version=productVO.getVersion();
		this.ownerprice=productVO.getOwnerprice();
		this.otherprice=productVO.getOtherprice();
		this.remark=productVO.getRemark();
		if(null!=productVO.getCarStr()&&productVO.getCarStr().length()>0){
			Type t=new TypeToken<ArrayList<CarVO>>(){}.getType();
			this.carVOs= Base.gson.fromJson(productVO.getCarStr(),t);
		}
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

	public List<CarVO> getCarVOs() {
		return carVOs;
	}

	public void setCarVOs(List<CarVO> carVOs) {
		this.carVOs = carVOs;
	}
	public void setCarVOs(String carStr) {
		if(null!=carStr&&carStr.length()>0){
			Type t=new TypeToken<ArrayList<CarVO>>(){}.getType();
			this.carVOs= Base.gson.fromJson(carStr,t);
		}
	}

	public String toLog(){
		StringBuffer sb=new StringBuffer();
		if(null!=version&&!"".equals(version)){
			sb.append(version).append(" ");
		}
		sb.append(name)
				.append(" ")
				.append(num)
				.append("个.")
				.append("车型:");
		int i=0;
		for(;i<carVOs.size()-1;i++){
			sb.append(carVOs.get(i).getName()).append(" ");
		}
		sb.append(carVOs.get(i).getName()).append(" ");
		sb.append(".");
		if(null!=remark&&!"".equals(remark)){
			sb.append("(注解:")
				.append(remark)
				.append(").");
		}
		return sb.toString();
	}
}
