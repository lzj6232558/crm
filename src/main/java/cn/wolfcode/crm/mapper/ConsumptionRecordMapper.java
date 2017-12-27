package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ConsumptionRecord;
import cn.wolfcode.crm.query.QueryObject;
import java.util.List;

public interface ConsumptionRecordMapper {
    int insert(ConsumptionRecord record);

    ConsumptionRecord selectByPrimaryKey(Long id);

    List<ConsumptionRecord> selectAll();

    int queryForCount(QueryObject qo);

    List<ConsumptionRecordMapper> queryForList(QueryObject qo);
}