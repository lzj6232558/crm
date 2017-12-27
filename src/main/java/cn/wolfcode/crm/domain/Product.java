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
public class Product {
    private Long id;

    private String sn;

    private String name;

    //进货价
    private BigDecimal costprice;

    //零售价
    private BigDecimal saleprice;

    //会员价
    private BigDecimal vipprice;

    //会员折扣
    private BigDecimal vipdiscount;

    //商品单位
    private Unit unit;

    //备注
    private String remark;

    //库存量
    private Productstock productstock;

    //一级类别
    private Producttype parent;

    //二级类别
    private Producttype child;

    //图片路径
    private String imagePath;

    //下架上架状态
    private boolean state;

    //入库时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inputtime;
}