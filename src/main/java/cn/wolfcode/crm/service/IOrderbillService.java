package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Orderbill;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IOrderbillService {
    void deleteByPrimaryKey(Long id);

    void insert(Orderbill record);

    Orderbill selectByPrimaryKey(Long id);

    List<Orderbill> selectAll();

    void updateByPrimaryKey(Orderbill record);
    
    PageResult query(QueryObject qo);

    //订单审核
    void audit(Long id);


    //查询已入库的订单
    PageResult queryReturnbill(QueryObject qo);

    //订单退货
    void returnbill(Long id);
    
}
