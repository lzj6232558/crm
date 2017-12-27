package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Checkrecord;
import java.util.List;

public interface CheckrecordMapper {
    void deleteByPrimaryKey(Long id);

    void insert(Checkrecord record);

    Checkrecord selectByPrimaryKey(Long id);

    List<Checkrecord> selectAll();

    void updateByPrimaryKey(Checkrecord record);
}