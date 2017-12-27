package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SystemLog;
import java.util.List;

public interface SystemLogMapper {
    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();
}