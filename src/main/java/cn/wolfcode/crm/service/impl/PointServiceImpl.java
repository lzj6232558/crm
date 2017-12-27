package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.PointItem;
import cn.wolfcode.crm.mapper.PointItemMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPointItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Charles on 2017/12/21.
 */
@Service
public class PointServiceImpl implements IPointItemService {
    @Autowired
    private PointItemMapper pointItemMapper;

    @Override
    public int insert(PointItem record) {
        return pointItemMapper.insert(record);
    }

    @Override
    public PointItem selectByPrimaryKey(Long id) {
        return pointItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PointItem> selectAll() {
        return pointItemMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = pointItemMapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, pointItemMapper.queryForList(qo));
    }
}
