package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DisburseBigClassify;

import java.util.List;

public interface IDisburseBigClassifyService {
    void deleteByPrimaryKey(Long id);

    void insert(DisburseBigClassify record);

    List<DisburseBigClassify  > selectAll();

    void updateByPrimaryKey(DisburseBigClassify   record);
}
