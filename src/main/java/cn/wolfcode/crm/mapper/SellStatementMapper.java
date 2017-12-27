package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SellStatement;
import java.util.List;

public interface SellStatementMapper {
    void insert(SellStatement record);

    SellStatement selectByPrimaryKey(Long id);

    List<SellStatement> selectAll();
}