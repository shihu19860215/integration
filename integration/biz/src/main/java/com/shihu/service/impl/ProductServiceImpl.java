package com.shihu.service.impl;

import com.google.gson.Gson;
import com.shihu.model.common.Car;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.CarProductVO;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.mybatis.dao.CarProductDao;
import com.shihu.mybatis.dao.ProductDao;
import com.shihu.service.CarService;
import com.shihu.service.CarTypeService;
import com.shihu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CarProductDao carProductDao;
    @Autowired
    private CarService carService;

    public List<Product> getProductListByCarId(Long carId){
        List<ProductVO> productVOs=productDao.getProductVOByCarId(carId);
        List<Product> products;

        if(null!=productVOs&&productVOs.size()>0){
            products=new ArrayList<Product>(productVOs.size());
            for(ProductVO p:productVOs){
                products.add(new Product(p));
            }
        }else {
            products=new ArrayList<Product>(0);
        }
        return products;
    }
    public void addProduct(ProductVO productVO,List<CarVO> cars){
        productDao.addProduct(productVO);
        for(CarVO c:cars){
            carProductDao.addCarProductVO(new CarProductVO(c.getId(),productVO.getId()));
        }
    }
    public Product getProductById(Long id){
        ProductVO productVO=productDao.getProductVOById(id);
        Product product=new Product(productVO);
        return product;
    }
    public Product getProductByIdWithCarTypeName(Long id){
        ProductVO productVO=productDao.getProductVOById(id);
        Product product=new Product(productVO);
        product.setCarVOs(carService.setCarNameWithCarType(product.getCarVOs()));
        return product;
    }

    public void updateProduct(ProductVO productVO, Long[] carIds, Long carId) {
        List<CarVO> carVOs=new ArrayList<CarVO>();
        List<Long> removeLongList=new ArrayList<Long>();
        List<Long> longList=new ArrayList<Long>();
        longList.add(carId);
        if(null!=carIds){
            for(Long l:carIds){
                longList.add(l);
            }
        }
        ProductVO productVOOld=productDao.getProductVOById(productVO.getId());
        Product productOld=new Product(productVOOld);
        List<CarVO> carVOList=productOld.getCarVOs();
        for (CarVO c:carVOList){
            for(Long l:longList){
                if(c.getId().equals(l)){
                    carVOs.add(c);
                    removeLongList.add(l);
                }
            }
        }
        for (CarVO c:carVOs){
            carVOList.remove(c);
        }
        for (Long l:removeLongList){
            longList.remove(l);
        }
        if(carVOList.size()>0||longList.size()>0){
            if(longList.size()>0){
                for(Long l:longList){
                    CarVO carVO=carService.getCarVOByIdCache(l);
                    carVOs.add(carVO);
                    carProductDao.addCarProductVO(new CarProductVO(carVO.getId(),productVO.getId()));
                }
            }
            if(carVOList.size()>0){
                for (CarVO c:carVOList){
                    carProductDao.delCarProductVO(new CarProductVO(c.getId(),productVO.getId()));
                }
            }
            productVO.setCarStr(carVOs);
        }else{
            productVO.setCarStr(productVOOld.getCarStr());
        }
        productDao.updateProdect(productVO);
    }

    public int addProductNum(Long id) {
        int count;
        ProductVO productVO=productDao.getProductVOById(id);
        count=productVO.getNum()+1;
        productVO.setNum(count);
        productDao.updateProdectNumById(productVO);
        return count;
    }

    public int subProductNum(Long id) {
        int count;
        ProductVO productVO=productDao.getProductVOById(id);
        count=productVO.getNum();
        if(productVO.getNum()>0){
            count--;
        }
        productVO.setNum(count);
        productDao.updateProdectNumById(productVO);
        return count;
    }

    public void delProduct(Long id){
        ProductVO productVO=productDao.getProductVOById(id);
        Product product=new Product(productVO);
        for(CarVO carVO:product.getCarVOs()){
            carProductDao.delCarProductVO(new CarProductVO(carVO.getId(),id));
        }
        productDao.delProduct(id);
    }

    public List<Product> searchProduct(String carName,String productName,String productVersion,String sort){
        Map<String,String> map=new HashMap<String,String>();
        if(null!=carName&&carName.length()>0){
            map.put("carName","%"+carName+"%");
        }
        if(null!=productName&&productName.length()>0){
            map.put("productName","%"+productName+"%");
        }
        if(null!=productVersion&&productVersion.length()>0){
            map.put("version","%"+productVersion+"%");
        }
        if(null!=sort&&sort.length()>0){
            map.put("sort",sort);
        }
        List<ProductVO> productVOs= productDao.searchProduct(map);
        return productListToProductVOList(productVOs);
    }

    private List<Product> productListToProductVOList(List<ProductVO> productVOs){
        List<Product> list=null;
        if(null!=productVOs&&productVOs.size()>0){
            list=new ArrayList<Product>(productVOs.size());
            for(ProductVO p:productVOs){
                list.add(new Product(p));
            }
        }
        return  list;
    }


    /*修改数据库增加carstr字段*/
    public void changesql() {
        Gson gson=new Gson();
        List<ProductVO> productList=productDao.getPruduct();
        for (ProductVO p:productList){
            List<CarVO> carVOs=productDao.getCarsByProductId(p.getId());
            p.setCarStr(gson.toJson(carVOs));
            productDao.updateP(p);
            System.out.println(p.getId());
        }
    }

    /*修改数据库增加carstr字段*/
}
