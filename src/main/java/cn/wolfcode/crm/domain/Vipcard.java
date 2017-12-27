package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Vipcard {
    public static final int STATE_NORMAL =1;//正常状态
    public static final int STATE_LOSS =0;//挂失状态
    public static final int STATE_OVER =-1;//停用状态

    private Long id;

    private String name;

    private BigDecimal discount;

    private Long minimum;

    private Long maximum;

    private int state;
}