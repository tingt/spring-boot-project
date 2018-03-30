package com.ttt.dynamic.datasource.service;

import com.ttt.dynamic.datasource.dao.entity.City;
import com.ttt.dynamic.datasource.dao.entity.Product;
import com.ttt.dynamic.datasource.dao.mapper.CityMapper;
import com.ttt.dynamic.datasource.dao.mapper.ProductMapper;
import com.ttt.dynamic.datasource.db.annotation.TargetDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tutingting
 * @date 2018/3/24 下午6:45
 * 由于事务不会传递，对于某一方法中单独的写，可以加事务控制。
 * 如果某一方法中有读又有写，不建议在整个方法中加事务，如果非要加事物，必须 设置该方法中读的事务传播策略为NOT_SUPPORTED
 */
@Service
public class CityService extends  CoreService{
    @Resource
    private CityMapper cityMapper;
    @Resource
    private ProductService productService;
    @Resource
    private ProductMapper productMapper;

    @TargetDataSource("studySlaveDataSource")
    public List<City> getCityList() {
        return cityMapper.listSlave();
    }

    @TargetDataSource("studyMasterDataSource")
    public List<City> getCityList2() {
        return cityMapper.listMaster();
    }

    /**
     * 主库写，异常会回滚,事务生效
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertCity() throws  Exception{
        City city=new City();
        city.setId(6L);
        city.setName("test");
        city.setState("test");
        cityMapper.insert(city);
  //     throw new Exception();
    }

    /**
     * 从不同数据库读。非事务
     * 不能直接通过this.**()调用同一service里的方法，如果采用此调用方式 ，数据源不会切换.
     * 如果需要切换数据源，且需要调用同一service里的方法，须通过getService().**()调用。
     * @return
     */
    @TargetDataSource("studyMasterDataSource")
    public List<Product> readFromDifferDs(){
        getCityList();
        return ((CityService)getService()).getProductMaster();
     //   return productService.getProductMaster();
    }

    /**
     * 主库写，从库（不同数据库）读,如果需要在整个方法上配置事务，读的方法必须配置传播策略为NOT_SUPPORTED，否则还是会走原来的连接。
     * @return
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    @Transactional
    public List<Product> writeAndRead() throws Exception{
        City city=new City();
        city.setId(7L);
        city.setName("test");
        city.setState("test");
        cityMapper.insert(city);
        return ((CityService)getService()).getProductMaster();
      //  throw new Exception();
    }

    /**
     * 主库写，从库（同一数据库）读，如果不用在整个方法上配事务，只在该方法中写的部分配置事务，由于事务不会向上传递，
     * 因此，该方法中的读不用配置事务传播策略为NOT_SUPPORTED
     * @return
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    public List<City> writeAndReadCity() throws Exception{
        ((CityService)getService()).insertCity();
        return ((CityService)getService()).getCityList();
        //  throw new Exception();
    }

    /**
     * 主库写，通过 service读
     * @return
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    public List<Product> writeAndReadByService() throws Exception{
        this.insertCity();
        return productService.getProductMaster();
    }

    /**
     * 如果写不同库，不支持分布式事物，不要在整个方法上加事务。
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    public void writeDifDs() throws Exception{
        ((CityService)getService()).insertCity();
        productService.insertProduct();
    }

    /**
     * 如果写不同库，如果非要在整个方法上加事务，所有写方法的事务传播策略须为REQUIRES_NEW
     * @throws Exception
     */
    @TargetDataSource("studyMasterDataSource")
    @Transactional
    public void writeDifDsNew() throws Exception{
        ((CityService)getService()).insertCity();
        productService.insertProductNew();
    }

    @TargetDataSource("productMasterDataSource")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Product> getProductMaster() {
        return productMapper.listMaster();
    }
}
