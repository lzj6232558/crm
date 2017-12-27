package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Adjustdepot {
    private Long id;

    //调入调出差数
    private BigDecimal differ;

    //调入商品
    private Product inproduct;

    //调入的仓库
    private Depot indepot;

    //调出商品
    private Product outproduct;

    //调出仓库
    private Depot outdepot;

    //调入仓库原数量
    private BigDecimal oldinnumber;

    //调入仓库现数量
    private BigDecimal newinnumber;

    //调出仓库原数量
    private BigDecimal oldoutnumber;

    //调出仓库现数量
    private BigDecimal newoutnumber;

    //操作人
    private Employee inputuser;

    //操作时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    //备注
    private String remark;

}