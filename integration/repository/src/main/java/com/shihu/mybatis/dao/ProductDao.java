package com.shihu.mybatis.dao;

import com.shihu.model.common.Product;
import com.shihu.model.common.User;
import com.shihu.model.common.VO.CarProductVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.model.common.VO.UserVO;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    public List<ProductVO> getProductVOByCarId(Long carId);
    public void updateProdectNumById(ProductVO productVO);
    public ProductVO getProductVOById(Long id);
    public void addProduct(ProductVO productVO);
    public void updateProdect(ProductVO productVO);
    public void delProduct(Long id);
    public List<ProductVO> searchProduct(Map<String,String> map);

    /*修改数据库增加carstr字段*/
    List<CarVO> getCarsByProductId(Long productId);
    List<ProductVO> getPruduct();
    void updateP(ProductVO productVO);
    /*修改数据库增加carstr字段*/
}
