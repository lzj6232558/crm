package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Orderbill;
import cn.wolfcode.crm.domain.Productstock;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IProductStockService {

    PageResult query(QueryObject qo);

    int deleteByPrimaryKey(Long id);

    int insert(Productstock record);

    Productstock selectByPrimaryKey(Long id);

    List<Productstock> selectAll();

    int updateByPrimaryKey(Productstock record);

    //采购单审核库存操作
    void stockIncome(Orderbill old);

    //退货单审核库存操作
    void stockOutcome(Orderbill old);
}
