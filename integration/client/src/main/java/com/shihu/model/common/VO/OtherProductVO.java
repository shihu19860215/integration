package com.shihu.model.common.VO;

public class OtherProductVO {
    private Long id;
    private Long orderId;
    private String name;
    private Integer num;
    private String unit;
    private Integer price;
    private boolean sell;

    public Long getId() {
        return id;
    }

    public OtherProductVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OtherProductVO setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getName() {
        return name;
    }

    public OtherProductVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public OtherProductVO setNum(Integer num) {
        this.num = num;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public OtherProductVO setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OtherProductVO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public boolean isSell() {
        return sell;
    }

    public OtherProductVO setSell(boolean sell) {
        this.sell = sell;
        return this;
    }
}
