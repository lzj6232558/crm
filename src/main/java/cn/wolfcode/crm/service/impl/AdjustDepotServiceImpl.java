package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.AdjustdepotMapper;
import cn.wolfcode.crm.mapper.DepotMapper;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.mapper.ProductstockMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IAdjustDepotService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class AdjustDepotServiceImpl implements IAdjustDepotService {
    @Autowired
    private AdjustdepotMapper adjustdepotMapper;

    @Autowired
    private ProductstockMapper productstockMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private DepotMapper depotMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        adjustdepotMapper.deleteByPrimaryKey(id);
    }

    /**
     * 仓库库存调拨
     * @param adjustdepot
     */
    @Override
    public void insert(Adjustdepot adjustdepot) {
        adjustdepot.setInputuser((Employee) SecurityUtils.getSubject().getPrincipal());

        Product Outproduct = productMapper.selectByPrimaryKey(adjustdepot.getOutproduct().getId());
        adjustdepot.setOutproduct(Outproduct);
        adjustdepot.setInproduct(Outproduct);


        //查调出仓库库存
        Productstock newproductstock1 = productstockMapper
                .selectByProductIdAndDepotId(adjustdepot
                        .getOutproduct().getId(),adjustdepot.getOutdepot().getId());


        //调出仓库原数量
        BigDecimal oldoutnumber = newproductstock1.getStorenumber();

        //更新库存1
        newproductstock1.setStorenumber(oldoutnumber.subtract(adjustdepot.getDiffer()));
        productstockMapper.updateByPrimaryKey(newproductstock1);




        adjustdepot.setOldoutnumber(oldoutnumber);


        //调出仓库现数量
        adjustdepot.setNewoutnumber(oldoutnumber.subtract(adjustdepot.getDiffer()));



        //查调入仓库库存
        Productstock newProductstock2 = productstockMapper
                .selectByProductIdAndDepotId(adjustdepot
                        .getInproduct().getId(),adjustdepot.getIndepot().getId());

        //如果调入仓库没有该库存信息
        if(newProductstock2 == null) {
            newProductstock2 = new Productstock();
            Depot indepot = depotMapper.selectByPrimaryKey(adjustdepot.getIndepot().getId());
            newProductstock2 = new Productstock();
            newProductstock2.setDepot(indepot);
            newProductstock2.setStorenumber(new BigDecimal("0"));
            newProductstock2.setProduct(Outproduct);
            newProductstock2.setPrice(Outproduct.getSaleprice());
            newProductstock2.setAmount(adjustdepot.getDiffer().multiply(Outproduct.getSaleprice()));
            productstockMapper.insert(newProductstock2);

            //调入仓库原数量
            BigDecimal oldinnumber = newProductstock2.getStorenumber();


            //更新库存2
            newProductstock2.setStorenumber(oldinnumber.add(adjustdepot.getDiffer()));
            productstockMapper.updateByPrimaryKey(newProductstock2);
        }



        //调入仓库原数量
        BigDecimal oldinnumber = newProductstock2.getStorenumber();


        //更新库存2
        newProductstock2.setStorenumber(oldinnumber.add(adjustdepot.getDiffer()));
        productstockMapper.updateByPrimaryKey(newProductstock2);

        adjustdepot.setOldinnumber(oldinnumber);



        //调入仓库现数量
        adjustdepot.setNewinnumber(oldinnumber.add(adjustdepot.getDiffer()));


        adjustdepotMapper.insert(adjustdepot);
    }

    @Override
    public Adjustdepot selectByPrimaryKey(Long id) {
        return adjustdepotMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Adjustdepot> selectAll() {
        return adjustdepotMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Adjustdepot record) {
        adjustdepotMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = adjustdepotMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, adjustdepotMapper.queryList(qo));
    }
}
