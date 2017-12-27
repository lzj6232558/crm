package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Orderbillitem {
    private Long id;

    private BigDecimal costprice;

    private BigDecimal number;

    private BigDecimal amount;

    private String remark;

    private Product product;

    private Orderbill bill;

}