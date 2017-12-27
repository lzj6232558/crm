package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class RechargeRecord {
    private Long id;
    //会员编号
    private Long vipNumber;
    //会员名字
    private String vipName;
    //当前充值金额
    private BigDecimal currentInMoney;
    //充值后总金额  通过计算得到
    private BigDecimal vipAmountMoney;
    //充值后会员的当前金额
    private BigDecimal vipCurrentMoney;
    //充值时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date inputTime;
    //操作人
    private Employee inputUser;
    //备注信息
    private String postscript;
}