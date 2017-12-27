package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Unit;
import cn.wolfcode.crm.mapper.UnitMapper;
import cn.wolfcode.crm.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements IUnitService{
    @Autowired
    UnitMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Unit record) {
        return mapper.insert(record);
    }

    @Override
    public Unit selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Unit> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Unit record) {
        return mapper.updateByPrimaryKey(record);
    }
}
