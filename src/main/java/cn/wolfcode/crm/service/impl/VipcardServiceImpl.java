package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Vipcard;
import cn.wolfcode.crm.mapper.VipcardMapper;
import cn.wolfcode.crm.service.IVipcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员卡service实现类
 */
@Service
public class VipcardServiceImpl implements IVipcardService{
    @Autowired
    VipcardMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Vipcard record) {
        //新增时设置会员卡状态为1(正常)
        record.setState(Vipcard.STATE_NORMAL);
        return mapper.insert(record);
    }

    @Override
    public Vipcard selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Vipcard> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Vipcard record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public void changeState(Long id) {
        mapper.changeState(id);
    }
}
