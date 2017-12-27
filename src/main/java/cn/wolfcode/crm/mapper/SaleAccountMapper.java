package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SaleAccount;
import java.util.List;

public interface SaleAccountMapper {
    void insert(SaleAccount record);

    SaleAccount selectByPrimaryKey(Long id);

    List<SaleAccount> selectAll();
}