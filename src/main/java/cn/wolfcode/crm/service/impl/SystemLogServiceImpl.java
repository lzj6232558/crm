package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.mapper.SystemLogMapper;
import cn.wolfcode.crm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    SystemLogMapper mapper;

    @Override
    public int insert(SystemLog record) {
        return mapper.insert(record);
    }

    @Override
    public SystemLog selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemLog> selectAll() {
        return mapper.selectAll();
    }
}
