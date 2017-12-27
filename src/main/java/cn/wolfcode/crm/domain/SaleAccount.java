package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

//销售报表

@Getter
@Setter
public class SaleAccount {
    private Long id;
    //销售时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;
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
    //商品
    private Product product;
    //业务员
    private Employee saleman;
    //客户
    private Vip client;
}