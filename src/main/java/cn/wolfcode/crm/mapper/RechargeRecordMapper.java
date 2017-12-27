package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.RechargeRecord;
import java.util.List;

public interface RechargeRecordMapper {
    void insert(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Long id);

    List<RechargeRecord> selectAll();

    List<RechargeRecord> selectRechargeRecordByVipNumber(Long number);
}