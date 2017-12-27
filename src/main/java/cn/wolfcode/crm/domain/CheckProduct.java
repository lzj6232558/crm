package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品盘点的domain类
 */
@Setter
@Getter
@ToString
public class CheckProduct {

    private Long id;
    //盘点日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkTime;

    //盘点的产品
    private Product product;

    //相应产品总数
    private BigDecimal storeNumber = new BigDecimal("0");

    //操作人员
    private Employee inputuser;
}
