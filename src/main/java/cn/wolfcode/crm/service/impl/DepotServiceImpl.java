package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.mapper.DepotMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepotServiceImpl implements IDepotService{

    @Autowired
    private DepotMapper depotMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        depotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Depot record) {
        depotMapper.insert(record);
    }

    @Override
    public Depot selectByPrimaryKey(Long id) {
        return depotMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Depot> selectAll() {
        return depotMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Depot record) {
        depotMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = depotMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, depotMapper.queryList(qo));
    }

    //改变仓库状态
    @Override
    public void changeState(Long id) {
        depotMapper.changeState(id);
    }

}
