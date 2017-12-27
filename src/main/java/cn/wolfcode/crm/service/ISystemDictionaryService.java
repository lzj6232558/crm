package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionary;

import java.util.List;

public interface ISystemDictionaryService {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);
}