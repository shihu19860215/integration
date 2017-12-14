package com.shihu.service.impl;

import com.google.gson.Gson;
import com.shihu.model.common.Car;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.*;
import com.shihu.mybatis.dao.CarProductDao;
import com.shihu.mybatis.dao.LogDao;
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
    @Autowired
    private LogDao logDao;

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
    @Transactional
    public void addProduct(ProductVO productVO,List<CarVO> cars){
        productDao.addProduct(productVO);
        for(CarVO c:cars){
            carProductDao.addCarProductVO(new CarProductVO(c.getId(),productVO.getId()));
        }
        Product product=new Product(productVO);
        logDao.addLog(new LogVO("添加商品:"+product.toLog()));
    }
    public Product getProductById(Long id){
        ProductVO productVO=productDao.getProductVOById(id);
        Product product=new Product(productVO);
        return product;
    }
    public Product getDisplayProductById(Long id){
        ProductVO productVO=productDao.getDisplayProductVOById(id);
        if(null==productVO){
            return null;
        }else {
            Product product=new Product(productVO);
            return product;
        }
    }
    public Product getProductByIdWithCarTypeName(Long id){
        ProductVO productVO=productDao.getProductVOById(id);
        Product product=new Product(productVO);
        product.setCarVOs(carService.setCarNameWithCarType(product.getCarVOs()));
        return product;
    }

    @Transactional
    public void updateProduct(ProductVO productVO, Long[] carIds, Long carId) {
        List<Long> longList=new ArrayList<Long>();
        longList.add(carId);
        if(null!=carIds){
            for(Long l:carIds){
                longList.add(l);
            }
        }
        this.update(productVO,longList);
    }

    @Transactional
    public void updateProduct(ProductVO productVO,Long[] carIds){
        List<Long> longList=null;
        if(null!=carIds){
            longList=new ArrayList<Long>();
            for(Long l:carIds){
                longList.add(l);
            }
        }
        this.update(productVO,longList);
    }
    @Transactional
    public void updateProduct(ProductVO productVO,String[] carIds){
        List<Long> longList=null;
        if(null!=carIds){
            longList=new ArrayList<Long>();
            for(String s:carIds){
                longList.add(Long.valueOf(s));
            }
        }
        this.update(productVO,longList);
    }

    /**
     * 更新商品信息并更新商品车型关系
     * @param productVO
     * @param longList
     */
    private void update(ProductVO productVO,List<Long> longList){
        if(null==longList||longList.size()<=0)return;
        ProductVO productVOOld=productDao.getProductVOById(productVO.getId());
        Product productOld=new Product(productVOOld);
        List<CarVO> carVOListOld=productOld.getCarVOs();
        boolean carsIsChange;
        if(longList.size()==carVOListOld.size()){
            int sameCount=0;
            for (CarVO c:carVOListOld){
                for(Long l:longList){
                    if(c.getId().equals(l)){
                        sameCount++;
                        break;
                    }
                }
            }
            if (sameCount==longList.size()){
                carsIsChange=false;
            }else {
                carsIsChange=true;
            }
        }else {
            carsIsChange=true;
        }

        if(carsIsChange
                ||!productVO.getName().equals(productOld.getName())
                ||(null!=productVO.getVersion()&&!productVO.getVersion().equals(productVOOld.getVersion())
                ||(null!=productVOOld.getVersion()&&!productVOOld.getVersion().equals(productVO.getVersion()))
                ||(null!=productVO.getRemark()&&!productVO.getRemark().equals(productVOOld.getRemark())))
                ||(null!=productVOOld.getRemark()&&!productOld.getRemark().equals(productVO.getRemark()))
                ){
            List<CarVO> carVOList=new ArrayList();
            for(Long l:longList){
                carVOList.add(carService.getCarVOByIdCache(l));
            }
            productVO.setCarStr(carVOList);

            productDao.noDisplayProduct(productVO.getId());
            productDao.addProduct(productVO);
            for(CarVO c:carVOList){
                carProductDao.addCarProductVO(new CarProductVO(c.getId(),productVO.getId()));
            }
            for(CarVO carVO:productOld.getCarVOs()){
                carProductDao.delCarProductVO(new CarProductVO(carVO.getId(),productOld.getId()));
            }

            logDao.addLog(new LogVO("更新商品:更新前"+productOld.toLog()+"更新后"+new Product(productVO).toLog()));
        }else if(
                productVO.getNum()!=productVOOld.getNum()
                        ||(null!=productVO.getOtherprice()&&!productVO.getOtherprice().equals(productVOOld.getOtherprice()))
                        ||(null!=productVOOld.getOtherprice()&&!productVOOld.getOtherprice().equals(productVO.getOtherprice()))
                        ||(null!=productVO.getOwnerprice()&&!productVO.getOwnerprice().equals(productVOOld.getOwnerprice()))
                        ||(null!=productVOOld.getOwnerprice()&&!productVOOld.getOwnerprice().equals(productVO.getOwnerprice()))
                ){
            productVO.setCarStr(productVOOld.getCarStr());
            productDao.updateProdect(productVO);
            logDao.addLog(new LogVO("更新商品:更新前"+productOld.toLog()+"更新后"+new Product(productVO).toLog()));
        }
    }

    @Transactional
    public int addProductNum(Long id) {
        int count;
        ProductVO productVO=productDao.getProductVOById(id);
        count=productVO.getNum()+1;
        productVO.setNum(count);
        productDao.updateProdectNumById(productVO);
        logDao.addLog(new LogVO("商品数量加一:"+new Product(productVO).toLog()));
        return count;
    }

    @Transactional
    public int subProductNum(Long id) {
        int count;
        ProductVO productVO=productDao.getProductVOById(id);
        count=productVO.getNum();
        if(productVO.getNum()>0){
            count--;
        }
        productVO.setNum(count);
        productDao.updateProdectNumById(productVO);
        logDao.addLog(new LogVO("商品数量减一:"+new Product(productVO).toLog()));
        return count;
    }

    @Transactional
    public void delProduct(Long id){
        ProductVO productVO=productDao.getProductVOById(id);
        carProductDao.delCarProductVOByProductId(id);
        productDao.noDisplayProduct(id);
        logDao.addLog(new LogVO("删除商品:"+new Product(productVO).toLog()));
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
