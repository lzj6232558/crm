package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.mapper.SystemDictionaryMapper;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService{
    
    @Autowired
    SystemDictionaryMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemDictionary record) {
        return mapper.insert(record);
    }

    @Override
    public SystemDictionary selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionary> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SystemDictionary record) {
        return mapper.updateByPrimaryKey(record);
    }
}
