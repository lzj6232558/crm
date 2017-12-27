package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.OrderChartMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IOrderChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderChartServiceImpl implements IOrderChartService {
    @Autowired
    private OrderChartMapper orderChartMapper;
    @Override
    public List<Map<String, Object>> orderChart(QueryObject qo) {
        return orderChartMapper.orderChart(qo);
    }
}
