package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Orderbillitem;
import java.util.List;

public interface OrderbillitemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderbillitem record);

    Orderbillitem selectByPrimaryKey(Long id);

    List<Orderbillitem> selectAll();

    int updateByPrimaryKey(Orderbillitem record);

    List<Orderbillitem> selectByBillId(Long billId);
}