package cn.wolfcode.crm.service;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.math.BigDecimal;

public interface IcheckproductService {
    /**
     * 查询商品盘点条目
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 确认商品数量
     */
    void check(Long id);

    /**
     * 商品数量调整
     */
    void adjust(BigDecimal newNumber,Long id);
}
