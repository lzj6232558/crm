package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Orderbill;
import cn.wolfcode.crm.domain.Productstock;
import cn.wolfcode.crm.mapper.ProductstockMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    ProductstockMapper productstockMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return productstockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Productstock record) {
        return productstockMapper.insert(record);
    }

    @Override
    public Productstock selectByPrimaryKey(Long id) {
        return productstockMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Productstock> selectAll() {
        return productstockMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Productstock record) {
        return productstockMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = productstockMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, productstockMapper.queryList(qo));
    }

    /**
     * 采购单审核后商品入库操作
     * @param old
     */
    @Override
    public void stockIncome(Orderbill old) {


        //根据商品id查库存
        Productstock productstock =
                productstockMapper.selectByProductIdAndDepotId(old.getProduct().getId(),old.getDepot().getId());

        //如果还没有该商品的库存

        if(productstock == null) {
            productstock = new Productstock();
            productstock.setProduct(old.getProduct());
            productstock.setPrice(old.getProduct().getSaleprice());
            productstock.setStorenumber(old.getTotalnumber());
            productstock.setAmount(old.getTotalamount());
            productstockMapper.insert(productstock);
        } else {
            //如果存在,更新
            BigDecimal number = productstock.getStorenumber().add(old.getTotalnumber());
            BigDecimal amount = productstock.getAmount().add(old.getTotalamount());
            productstock.setStorenumber(number);
            productstock.setAmount(amount);
            productstockMapper.updateByPrimaryKey(productstock);
        }
    }

    /**
     * 订单退货
     * @param old
     */
    @Override
    public void stockOutcome(Orderbill old) {

        //根据商品id查库存
        Productstock productstock =
                productstockMapper.selectByProductIdAndDepotId(old.getProduct().getId(),old.getDepot().getId());
        //减少库存
        productstock.setStorenumber(productstock.getStorenumber().subtract(old.getTotalnumber()));
        productstock.setAmount(productstock.getAmount().add(old.getTotalamount()));
        productstockMapper.updateByPrimaryKey(productstock);

    }
}

