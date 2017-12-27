package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.RechargeRecord;

import java.util.List;
//充值记录
public interface IRechargeRecordService {
    void insert(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Long id);

    List<RechargeRecord> selectAll();

    void refundVip(RechargeRecord record);

    List<RechargeRecord> selectRechargeRecordByVipNumber(Long number);
}
