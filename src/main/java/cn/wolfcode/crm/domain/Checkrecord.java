package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class Checkrecord {
    private Long id;

    //原有库存
    private BigDecimal storeNumber;

    //修改数量
    private BigDecimal newNumber;

    //操作人员
    private Employee inputter;

    //盘点时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    //盘点备注
    private String remark;

}