package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SaleAccount;

import java.util.List;

//消费报表
public interface ISaleAccountService {

    SaleAccount selectByPrimaryKey(Long id);

    List<SaleAccount> selectAll();
}
