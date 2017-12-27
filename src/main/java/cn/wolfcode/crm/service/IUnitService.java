package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Unit;

import java.util.List;

public interface IUnitService {
    int deleteByPrimaryKey(Long id);

    int insert(Unit record);

    Unit selectByPrimaryKey(Long id);

    List<Unit> selectAll();

    int updateByPrimaryKey(Unit record);
}
