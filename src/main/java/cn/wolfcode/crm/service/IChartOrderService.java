package cn.wolfcode.crm.service;

import cn.wolfcode.crm.query.OrderSaleQueryObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IChartOrderService {
    /**
     * 这是销售报表的高级查询
     * @param queryObject
     * @return
     */
    List<Map<String, Object>> saleChart(OrderSaleQueryObject queryObject);

    /**
     * 销售分析
     * @param qo
     * @return
     */
    List<Map<String,Object>> selectGroupBy(OrderSaleQueryObject qo);

    /**
     * 销售总利润
     * @return
     */
    BigDecimal selectSumProfit();
}
