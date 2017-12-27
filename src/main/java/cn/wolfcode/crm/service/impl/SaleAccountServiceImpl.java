
package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.ProductstockMapper;
import cn.wolfcode.crm.mapper.SaleAccountMapper;
import cn.wolfcode.crm.mapper.VipMapper;
import cn.wolfcode.crm.service.ISaleAccountService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleAccountServiceImpl implements ISaleAccountService {

    @Autowired
    SaleAccountMapper saleAccountMapper;


    @Override
    public SaleAccount selectByPrimaryKey(Long id) {
        return saleAccountMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SaleAccount> selectAll() {
        return saleAccountMapper.selectAll();
    }
}

