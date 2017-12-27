package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Adjustdepot;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface AdjustdepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Adjustdepot record);

    Adjustdepot selectByPrimaryKey(Long id);

    List<Adjustdepot> selectAll();

    int updateByPrimaryKey(Adjustdepot record);

    int queryCount(QueryObject qo);

    List<Adjustdepot> queryList(QueryObject qo);
}