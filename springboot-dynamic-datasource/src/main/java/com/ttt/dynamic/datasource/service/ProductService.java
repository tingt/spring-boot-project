package com.ttt.dynamic.datasource.service;

import com.ttt.dynamic.datasource.dao.entity.Product;
import com.ttt.dynamic.datasource.dao.mapper.ProductMapper;
import com.ttt.dynamic.datasource.db.annotation.TargetDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:45
 */
@Service
public class ProductService extends  CoreService{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductMapper productMapper;

    @TargetDataSource("productMasterDataSource")
    public List<Product> getProductMaster() {
        return productMapper.listMaster();
    }

    @TargetDataSource("productSlaveDataSource")
    public List<Product> getProductSlave() {
        return productMapper.listSlave();
    }

    @TargetDataSource("productMasterDataSource")
    //@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public void insertProduct() throws  Exception{
        Product product=new Product();
        product.setId(3);
        product.setName("test");
        product.setPrice(new BigDecimal(19.9));
        productMapper.insert();
     //   throw new Exception();
    }

    @TargetDataSource("productMasterDataSource")
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
   // @Transactional(rollbackFor = Exception.class)
    public void insertProductNew() throws  Exception{
        Product product=new Product();
        product.setId(3);
        product.setName("test");
        product.setPrice(new BigDecimal(19.9));
        productMapper.insert();
        //   throw new Exception();
    }
}
