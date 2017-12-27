package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.mapper.GiftMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Charles on 2017/12/21.
 */
@Service
public class GiftServiceImpl implements IGiftService {
    @Autowired
    private GiftMapper giftMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return giftMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Gift record) {
        return giftMapper.insert(record);
    }

    @Override
    public Gift selectByPrimaryKey(Long id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Gift> selectAll() {
        return giftMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Gift record) {
        return giftMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = giftMapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, giftMapper.queryForList(qo));
    }
}
