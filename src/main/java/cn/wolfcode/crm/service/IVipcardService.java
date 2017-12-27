package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Vipcard;

import java.util.List;

/**
 * 会员卡service
 */
public interface IVipcardService {
    int deleteByPrimaryKey(Long id);

    int insert(Vipcard record);

    Vipcard selectByPrimaryKey(Long id);

    List<Vipcard> selectAll();

    int updateByPrimaryKey(Vipcard record);

    void changeState(Long id);
}
