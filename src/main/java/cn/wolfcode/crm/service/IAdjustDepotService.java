package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Adjustdepot;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IAdjustDepotService {
    void deleteByPrimaryKey(Long id);

    void insert(Adjustdepot record);

    Adjustdepot selectByPrimaryKey(Long id);

    List<Adjustdepot> selectAll();

    void updateByPrimaryKey(Adjustdepot record);

    PageResult query(QueryObject qo);
}
