package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Vipcard;
import java.util.List;

public interface VipcardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vipcard record);

    Vipcard selectByPrimaryKey(Long id);

    List<Vipcard> selectAll();

    int updateByPrimaryKey(Vipcard record);

    void changeState(Long id);

    /**
     * 根据会员积分查询对应的会员卡等级
     * @param totalpoint
     * @return
     */
    Vipcard selectCardByPoint(Long totalpoint);
}