package com.shihu.model.common.VO;

public class CarProductUpdateVO {
    private Long carId;
    private Long productId;
    private Long carIdOld;
    private Long productIdOld;

    public CarProductUpdateVO(Long carId, Long productId, Long carIdOld, Long productIdOld) {
        this.carId = carId;
        this.productId = productId;
        this.carIdOld = carIdOld;
        this.productIdOld = productIdOld;
    }

    public Long getCarId() {
        return carId;
    }

    public CarProductUpdateVO setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public CarProductUpdateVO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Long getCarIdOld() {
        return carIdOld;
    }

    public CarProductUpdateVO setCarIdOld(Long carIdOld) {
        this.carIdOld = carIdOld;
        return this;
    }

    public Long getProductIdOld() {
        return productIdOld;
    }

    public CarProductUpdateVO setProductIdOld(Long productIdOld) {
        this.productIdOld = productIdOld;
        return this;
    }
}
