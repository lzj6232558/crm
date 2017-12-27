package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by Charles on 2017/12/21.
 */
public interface IGiftService {
    int deleteByPrimaryKey(Long id);

    int insert(Gift record);

    Gift selectByPrimaryKey(Long id);

    List<Gift> selectAll();

    int updateByPrimaryKey(Gift record);

    PageResult query(QueryObject qo);

}
