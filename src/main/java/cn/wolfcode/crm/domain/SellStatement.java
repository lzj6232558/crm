package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//销售报表

@Getter
@Setter
public class SellStatement {
    private Long id;
    //数量
    private BigDecimal number;
    //成本价
    private BigDecimal costprice;
    //成本总计
    private BigDecimal costamount;
    //售价
    private BigDecimal saleprice;
    //销售总计
    private BigDecimal saleamount;
    //商品id
    private Long productId;
    //业务员
    private Employee saleman;
    //客户
    private Vip client;
}