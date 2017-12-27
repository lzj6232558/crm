package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

//消费明细
@Getter
@Setter
public class ConsumptionDetail {
    private Long id;
    //商品数量
    private BigDecimal number;
    //商品
    private Product product;
    //成本价
    private BigDecimal costprice;
    //销售价格
    private BigDecimal saleprice;
    //这一类商品消费总计
    private BigDecimal amountmoney;
    //消费单id
    private Long recordId;
    //仓库
    private Long depotId;
}