package com.shihu.model.common;

import com.shihu.model.common.VO.OrderProductAndProductVO;

public class OrderProduct {
    private Long id;
    private Product product;
    private int num;//订单商品数量
    private Integer price;
    private boolean sell;//是卖还是退货

    public Long getId() {
        return id;
    }

    public OrderProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public OrderProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getNum() {
        return num;
    }

    public OrderProduct setNum(int num) {
        this.num = num;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderProduct setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public boolean isSell() {
        return sell;
    }

    public OrderProduct setSell(boolean sell) {
        this.sell = sell;
        return this;
    }

    public OrderProduct(OrderProductAndProductVO orderProductAndProductVO) {
        Product product=new Product();
        product.setName(orderProductAndProductVO.getName());
        product.setVersion(orderProductAndProductVO.getVersion());
        product.setCarVOs(orderProductAndProductVO.getCarStr());
        this.price=orderProductAndProductVO.getPrice();
        this.num=orderProductAndProductVO.getNum();
        this.sell=orderProductAndProductVO.isSell();
        this.setProduct(product);
    }
}
