package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface GiftMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gift record);

    Gift selectByPrimaryKey(Long id);

    List<Gift> selectAll();

    int updateByPrimaryKey(Gift record);

    int queryForCount(QueryObject qo);

    List<Gift> queryForList(QueryObject qo);
}