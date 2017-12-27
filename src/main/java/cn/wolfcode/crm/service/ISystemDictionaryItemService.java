package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionaryItem;

import java.util.List;

public interface ISystemDictionaryItemService {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    List<SystemDictionaryItem> queryById(SystemDictionaryItem item);

    List<SystemDictionaryItem>selectItemByParentId(Long id);
}