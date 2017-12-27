package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PointExchange;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by Charles on 2017/12/25.
 */
public interface PointExchangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PointExchange record);

    PointExchange selectByPrimaryKey(Long id);

    List<PointExchange> selectAll();

    int updateByPrimaryKey(PointExchange record);

    int queryForCount(QueryObject qo);

    List<PointExchange> queryForList(QueryObject qo);
}
