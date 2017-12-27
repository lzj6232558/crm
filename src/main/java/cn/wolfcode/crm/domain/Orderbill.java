package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Orderbill {
    private Long id;

    private String sn;

    private Supplier supplier;

    private BigDecimal totalnumber;

    private BigDecimal totalamount;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date audittime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    private Employee inputuser;

    private Employee auditor;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;

    //订单和订单明细,一对多,关系有订单维护
    private List<Orderbillitem> items = new ArrayList<>();

    //采购订单和商品一对一
    private Product product;


    //仓库  一对一关系
    private Depot depot;
}