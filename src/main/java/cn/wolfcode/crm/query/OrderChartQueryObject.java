package cn.wolfcode.crm.query;

import cn.wolfcode.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Setter
@Getter
public class OrderChartQueryObject extends QueryObject{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String productName;
    private Long supplierId = -1L;
    private String groupBy = "p.name";

    public Date getEndDate() {
        if(endDate != null) {
            return DateUtil.getEndDate(endDate);
        }
        return null;
    }

    public List<Map<String,String>> getGroupByMap() {
        Map<String,String> map1 = new HashMap<>();
        map1.put("key","e.username");
        map1.put("value","订货人员");

        Map<String,String> map2 = new HashMap<>();
        map2.put("key","p.name");
        map2.put("value","货品名称");

        Map<String,String> map3 = new HashMap<>();
        map3.put("key","s.name");
        map3.put("value","供应商");

        Map<String,String> map4 = new HashMap<>();
        map4.put("key","DATE_FORMAT(ob.vdate,'%Y-%m')");
        map4.put("value","订货日期(月)");

        Map<String,String> map5 = new HashMap<>();
        map5.put("key","DATE_FORMAT(ob.vdate,'%Y-%m-%d')");
        map5.put("value","订货日期(日)");

        List<Map<String,String>> groupBy = new ArrayList<>();
        groupBy.add(map1);
        groupBy.add(map2);
        groupBy.add(map3);
        groupBy.add(map4);
        groupBy.add(map5);
        return groupBy;
    }
}
