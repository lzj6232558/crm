package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Checkrecord;

import java.util.List;

public interface ICheckRecordService {
    void deleteByPrimaryKey(Long id);

    void insert(Checkrecord record);

    Checkrecord selectByPrimaryKey(Long id);

    List<Checkrecord> selectAll();

    void updateByPrimaryKey(Checkrecord record);
}
