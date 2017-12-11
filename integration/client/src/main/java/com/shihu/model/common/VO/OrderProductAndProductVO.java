package com.shihu.model.common.VO;

/**
 * 订单商品 和商品详情联合查询
 */
public class OrderProductAndProductVO {
    private int num;//订单商品数量
    private Integer price;//单价
    private boolean sell;//是卖还是退货
    private String name;//商品名
    private String version;
    private String carStr;

    public int getNum() {
        return num;
    }

    public OrderProductAndProductVO setNum(int num) {
        this.num = num;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderProductAndProductVO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public boolean isSell() {
        return sell;
    }

    public OrderProductAndProductVO setSell(boolean sell) {
        this.sell = sell;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderProductAndProductVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public OrderProductAndProductVO setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getCarStr() {
        return carStr;
    }

    public OrderProductAndProductVO setCarStr(String carStr) {
        this.carStr = carStr;
        return this;
    }
}
