package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.query.QueryObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ChartOrderMapper {
    /**
     * 销售报表的高级查询
     * @param queryObject
     * @return
     */
    List<Map<String,Object>> saleChart(QueryObject queryObject);

    /**
     * 支出分析
     * @param qo
     * @return
     */
    List<Map<String,Object>>  selectGroupBy(QueryObject qo);

    /**
     * 销售总利润
     * @return
     */
    BigDecimal selectSumProfit();
}
