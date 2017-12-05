package com.shihu.model.common.VO;

public class CarProductVO {
    private Long carId;
    private Long productId;

    public CarProductVO(Long carId, Long productId) {
        this.carId = carId;
        this.productId = productId;
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
