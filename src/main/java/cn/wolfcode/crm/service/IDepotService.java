package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IDepotService {
    void deleteByPrimaryKey(Long id);

    void insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    void updateByPrimaryKey(Depot record);
    
    PageResult query(QueryObject qo);

    //改变仓库状态
    void changeState(Long id);
}
