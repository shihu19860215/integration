package com.shihu.service;

import com.shihu.model.common.Car;
import com.shihu.model.common.Product;
import com.shihu.model.common.User;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    public List<Product> getProductListByCarId(Long carId);
    @Transactional
    public void addProduct(ProductVO productVO,List<CarVO> cars);
    public Product getProductById(Long id);
    public Product getProductByIdWithCarTypeName(Long id);
    public int addProductNum(Long id);
    public int subProductNum(Long id);
    @Transactional
    public void updateProduct(ProductVO productVO,Long[] carIds,Long carId);
    public void delProduct(Long id);
    public List<Product> searchProduct(String carName,String productName,String productVersion,String sort);




    /*修改数据库增加carstr字段*/
    public void changesql();
    /*修改数据库增加carstr字段*/
}
