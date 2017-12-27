package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.DisburseBigClassify;
import cn.wolfcode.crm.mapper.DisburseBigClassifyMapper;
import cn.wolfcode.crm.mapper.DisburseLittleClassifyMapper;
import cn.wolfcode.crm.service.IDisburseBigClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisburseBigClassifyImpl implements IDisburseBigClassifyService {

    @Autowired
    private DisburseBigClassifyMapper bigMapper;

    @Autowired
    private DisburseLittleClassifyMapper littleMapper;

    @Override
    public void deleteByPrimaryKey(Long bigId) {
        //删除父子关系
        bigMapper.deleteRelationByBigId(bigId);
        //删除子分类
        littleMapper.deleteByBigId(bigId);
        bigMapper.deleteByPrimaryKey(bigId);
    }

    @Override
    public void insert(DisburseBigClassify record) {
        bigMapper.insert(record);
    }

    @Override
    public List<DisburseBigClassify> selectAll() {
        return bigMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(DisburseBigClassify record) {
        bigMapper.updateByPrimaryKey(record);
    }
}
