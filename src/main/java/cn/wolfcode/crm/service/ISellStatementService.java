package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ConsumptionRecord;
import cn.wolfcode.crm.domain.SellStatement;

import java.util.List;

//消费报表
public interface ISellStatementService {
    void insert(ConsumptionRecord record);

    SellStatement selectByPrimaryKey(Long id);

    List<SellStatement> selectAll();
}
