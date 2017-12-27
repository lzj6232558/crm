package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Shoppingcar {

    private Long id;
    private Long pId;

    private String name;

    private BigDecimal price;

    private String img;

    private Long number;

    //购买数量
    private Long shoppingnumber;

    //总价格
    private BigDecimal tocalPrice;

    //库存id
    private Long depotId;

    //状态
    private int status;

    //vip号码
    private Long vipId;

    //购买状态
    private int shoppingstatus;

}