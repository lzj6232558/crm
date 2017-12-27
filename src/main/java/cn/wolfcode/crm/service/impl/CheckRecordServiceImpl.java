package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Checkrecord;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CheckrecordMapper;
import cn.wolfcode.crm.service.ICheckRecordService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CheckRecordServiceImpl implements ICheckRecordService {

    @Autowired
    private CheckrecordMapper checkrecordMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        checkrecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Checkrecord record) {
        record.setInputter((Employee) SecurityUtils.getSubject().getPrincipal());
        checkrecordMapper.insert(record);
    }

    @Override
    public Checkrecord selectByPrimaryKey(Long id) {
        return checkrecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Checkrecord> selectAll() {
        List<Checkrecord> checkrecords = checkrecordMapper.selectAll();
        Collections.reverse(checkrecords);
        return checkrecords;
    }

    @Override
    public void updateByPrimaryKey(Checkrecord record) {
        checkrecordMapper.updateByPrimaryKey(record);
    }
}
