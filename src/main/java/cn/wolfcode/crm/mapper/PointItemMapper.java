package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PointItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface PointItemMapper {
    int insert(PointItem record);

    PointItem selectByPrimaryKey(Long id);

    List<PointItem> selectAll();

    int queryForCount(QueryObject qo);

    List<PointItem> queryForList(QueryObject qo);

}