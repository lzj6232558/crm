
package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ConsumptionDetail;
import cn.wolfcode.crm.domain.ConsumptionRecord;
import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.domain.SellStatement;
import cn.wolfcode.crm.mapper.ConsumptionDetailMapper;
import cn.wolfcode.crm.mapper.SellStatementMapper;
import cn.wolfcode.crm.service.ISellStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SellStatementServiceImpl implements ISellStatementService {

    @Autowired
    SellStatementMapper sellStatementMapper;


    @Override
    public void insert(ConsumptionRecord recor) {
        //消费报表
        List<ConsumptionDetail> items = recor.getItems();
        for (ConsumptionDetail item : items) {

        }
    }

    @Override
    public SellStatement selectByPrimaryKey(Long id) {
        return sellStatementMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SellStatement> selectAll() {
        return sellStatementMapper.selectAll();
    }
}

