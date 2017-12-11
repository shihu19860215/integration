package com.shihu.model.common.VO;

public class CarProductVO {
    private Long id;
    private Long carId;
    private Long productId;

    public CarProductVO(Long carId, Long productId) {
        this.carId = carId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public CarProductVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
