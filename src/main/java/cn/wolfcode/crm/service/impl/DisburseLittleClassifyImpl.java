package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.DisburseLittleClassify;
import cn.wolfcode.crm.mapper.DisburseLittleClassifyMapper;
import cn.wolfcode.crm.service.IDisburseLittleClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Relation;
import java.util.List;

@Service
public class DisburseLittleClassifyImpl implements IDisburseLittleClassifyService {

    @Autowired
    private DisburseLittleClassifyMapper littleMapper;
    @Override
    public void deleteByPrimaryKey(Long littleId) {
        //删除父子关系
        littleMapper.deleteRelationLittleBigId(littleId);
        littleMapper.deleteByPrimaryKey(littleId);
    }

    @Override
    public void insert(DisburseLittleClassify record) {
        littleMapper.insert(record);
        //保存关系
        littleMapper.insertBigAndLittleRelation(record.getBigClassify().getId(), record.getId());
    }

    @Override
    public DisburseLittleClassify selectByPrimaryKey(Long id) {
        return littleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DisburseLittleClassify> selectAll() {
        return littleMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(DisburseLittleClassify record) {
        littleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<DisburseLittleClassify> getLittleByBigName(String bigName) {
        return littleMapper.getLittleByBigName(bigName);
    }
}
