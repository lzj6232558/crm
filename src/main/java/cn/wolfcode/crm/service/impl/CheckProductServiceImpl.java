package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.CheckProduct;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CheckProductMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IcheckproductService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

@Service
public class CheckProductServiceImpl implements IcheckproductService{

    @Autowired
    private CheckProductMapper checkProductMapper;

    @Override
    public PageResult query(QueryObject qo) {
        int count = checkProductMapper.queryCount(qo);
        if(count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count,checkProductMapper.checkproduct(qo));
    }

    @Override
    public void check(Long id) {
        CheckProduct checkProduct = checkProductMapper.selectByPrimaryKey(id);
        checkProduct.setCheckTime(new Date());
        checkProduct.setInputuser((Employee) SecurityUtils.getSubject().getPrincipal());

       /*//对应商品在所有仓库的总数量  ...没必要
        BigDecimal totalNumber =
                checkProductMapper
                        .selectDepotTotalNumber(checkProduct.getId()).getStoreNumber();

        checkProduct.setStoreNumber(totalNumber);*/
        checkProductMapper.update(checkProduct);
    }

    @Override
    public void adjust(BigDecimal newNumber,Long id) {
        checkProductMapper.adjust(newNumber,id);
    }
}
