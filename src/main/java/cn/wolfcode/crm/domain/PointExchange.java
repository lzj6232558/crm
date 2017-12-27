package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 积分兑换记录
 */
@Setter
@Getter
public class PointExchange {
    private Long id;

    private Vip vip;

    private Gift gift;

    private Long number;//兑换数量

    private Employee inputUser;//操作人

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM-8")
    private Date inputTime;//兑换时间

}