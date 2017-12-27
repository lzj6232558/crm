package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ConsumptionRecord;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.JsonResult;

import java.util.List;

//消费记录
public interface IConsumptionRecordService {
    JsonResult insert(Long record);

    ConsumptionRecord selectByPrimaryKey(Long id);

    List<ConsumptionRecord> selectAll();

    PageResult query(QueryObject qo);
}
