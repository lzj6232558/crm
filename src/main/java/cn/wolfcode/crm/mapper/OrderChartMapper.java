package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface OrderChartMapper {
    /**
     * 订单报表,将每条数据封装到一个Map集合中
     * @param qo 高级查询相关的参数对象
     * @return 返回多个map集合
     */
    List<Map<String,Object>> orderChart(QueryObject qo);
}
