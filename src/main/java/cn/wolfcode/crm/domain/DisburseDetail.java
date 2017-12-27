package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DisburseDetail {
    private Long id;
    //详细支出信息
    private String detail;
    //金额
    private BigDecimal money ;
    //支出时间
    @DateTimeFormat(pattern = "yyy-MM-dd" )
    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GTM-8")
    private Date disburseTime;
    //大分类
    private DisburseBigClassify bigClassify;
    //小分类
    private DisburseLittleClassify littleClassify;
    //支出人
    private Employee disburseUser;
}