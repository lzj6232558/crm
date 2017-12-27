package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.ChartOrderMapper;
import cn.wolfcode.crm.query.OrderSaleQueryObject;
import cn.wolfcode.crm.service.IChartOrderService;
import cn.wolfcode.crm.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ChartOrderServiceImpl implements IChartOrderService {

    @Autowired
    private ChartOrderMapper chartOrderMapper;

    @Override
    public List<Map<String, Object>> saleChart(OrderSaleQueryObject qo) {
        int state = qo.getDateState();
        //等于 1为今天 2 为昨天
        if (state == 1) {
            qo.setBeginDate(DateUtil.getDate(new Date(), -1));
        } else if (state == 2) {
            qo.setBeginDate(DateUtil.getDate(new Date(), -2));
            qo.setEndDate(DateUtil.getDate(new Date(), -2));
        }
        List<Map<String, Object>> maps = chartOrderMapper.saleChart(qo);
        for (Map<String, Object> map : maps) {
            //处理时间
            map.put("sellVdate", map.get("sellVdate").toString());
        }
        return maps;
    }

    @Override
    public List<Map<String, Object>> selectGroupBy(OrderSaleQueryObject qo) {
        return chartOrderMapper.selectGroupBy(qo);
    }

    @Override
    public BigDecimal selectSumProfit() {
        return chartOrderMapper.selectSumProfit();
    }
}
