package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PointItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by Charles on 2017/12/21.
 */
public interface IPointItemService {
    int insert(PointItem record);

    PointItem selectByPrimaryKey(Long id);

    List<PointItem> selectAll();

    PageResult query(QueryObject qo);

}
