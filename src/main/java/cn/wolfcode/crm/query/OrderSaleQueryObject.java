package cn.wolfcode.crm.query;

import cn.wolfcode.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//销售报表的高级查询


@Getter
@Setter
@ToString
public class OrderSaleQueryObject extends QueryObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private int dateState = -1;

    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        }
        return null;
    }
    //会员信息
    private Long vipId = -1L;
    //流水单号
    private Long sellId = -1L;
    //消费对象
    private Long consumeObjectId = -1L;
    //商品id
    private Long proId = -1L;
    //消费来源
    private String consumeSource;
    //分组
    private String groupBy = "e.username";
}
