package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class Vip {
    public static final int STATE_NORMAL = 1;//正常状态
    public static final int STATE_LOSS = 0;//挂失状态
    public static final int STATE_OVER = -1;//停用状态
    private Long id;
    //会员编号
    private Long vipNumber;
    //密码
    private String vipPassword;
    //会员名称
    private String vipName;
    //会员电话
    private String vipPhone;
    //性别
    private boolean gender;
    //充值总金额
    private BigDecimal amountMoney;
    //当前金额
    private BigDecimal currentMoney;
    //邮箱
    private String email;
    //加入时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GTM-8")
    private Date inputTime;
    //会员卡
    private Vipcard vipcard;
    //当前积分
    private Long currentpoint;
    //总积分
    private Long totalpoint;
    //录入员工
    private Employee employee;
    //会员来源
    private String source;
    //会员卡的状态
    private int vipCardState;
}