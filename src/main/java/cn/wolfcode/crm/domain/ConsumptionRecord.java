package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//消费单
@Getter
@Setter
public class ConsumptionRecord {
    private Long id;
    //会员卡号
    private Long vipnumber;
    //会员名
    private String vipname;
    //总消费金额
    private BigDecimal amountmoney;
    //操作时间
    private Date inputtime;
    //操作人员
    private Employee inputuser;
    //销售明细
    private List<ConsumptionDetail> items = new ArrayList<>();
}