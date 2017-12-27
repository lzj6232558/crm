package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ConsumptionDetail;
import java.util.List;

public interface ConsumptionDetailMapper {
    int insert(ConsumptionDetail record);

    ConsumptionDetail selectByPrimaryKey(Long id);

    List<ConsumptionDetail> selectAll();
}