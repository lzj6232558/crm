package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordQueryObject extends QueryObject{

    //按时间的格式查询(年/月/日)
    private String timeFormat;
    //具体的数值
    private Integer formatNum;

}
