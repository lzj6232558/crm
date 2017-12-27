package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {
    
    @Autowired
    SystemDictionaryItemMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SystemDictionaryItem record) {
        return mapper.insert(record);
    }

    @Override
    public SystemDictionaryItem selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SystemDictionaryItem record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SystemDictionaryItem> queryById(SystemDictionaryItem item) {
        return mapper.queryById(item);
    }

    @Override
    public List<SystemDictionaryItem> selectItemByParentId(Long id) {
        return mapper.selectItemByParentId(id);
    }
}
