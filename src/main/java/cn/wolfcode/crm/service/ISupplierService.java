package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Supplier;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ISupplierService {
    void deleteByPrimaryKey(Long id);

    void insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    void updateByPrimaryKey(Supplier record);
    
    PageResult query(QueryObject qo);

}
