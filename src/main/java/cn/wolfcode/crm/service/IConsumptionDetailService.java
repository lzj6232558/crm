package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ConsumptionDetail;

import java.util.List;

//消费记录
public interface IConsumptionDetailService {
    void insert(List<ConsumptionDetail> items,Long id);

    ConsumptionDetail selectByPrimaryKey(Long id);

    List<ConsumptionDetail> selectAll();
}
