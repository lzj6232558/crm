package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.OrderChartQueryObject;
import cn.wolfcode.crm.service.IOrderChartService;
import cn.wolfcode.crm.util.RequiredName;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("orderchart")
public class OrderChartController {

    @Autowired
    private IOrderChartService orderChartService;

    @RequestMapping("view")
    @RequiresPermissions("orderchart:view")
    @RequiredName("仓库页面")
    public String view() {
        return "orderchart";
    }


    /**
     * 订货报表数据
     * @param queryObject
     * @return
     */
    @RequestMapping("query")
    @RequiresPermissions("orderchart:orderchart")
    @RequiredName("订货报表")
    @ResponseBody
    public List<Map<String, Object>> orderChart(OrderChartQueryObject queryObject) {
        return orderChartService.orderChart(queryObject);
    }

    @RequestMapping("groupBy")
    @ResponseBody
    public List<Map<String,String>> groupBy(OrderChartQueryObject queryObject) {

        return queryObject.getGroupByMap();
    }


    @RequestMapping("pie")
    public String saleChartByPie(Model model, @ModelAttribute("qo")OrderChartQueryObject queryObject) {
        if(queryObject.getGroupBy() == null ||  queryObject.getGroupBy() == "") {
            queryObject.setGroupBy("p.name");
        }


        String groupByName = null;

        List<Map<String,String>> mapList = queryObject.getGroupByMap();

        //获取比如对应p.name的中文名:货品名称
        for(Map<String,String> m : mapList) {
            for(String k : m.keySet()) {
                if(m.get(k).equals(queryObject.getGroupBy())) {
                    groupByName = m.get("value");
                    System.out.println(groupByName);
                }
            }
        }


        model.addAttribute("groupByName", groupByName);

        //获取到分类的类型
        List<Map<String,Object>> maps = orderChartService.orderChart(queryObject);
        //存放所有的分类名称
        List<String> groupByNames = new ArrayList<>();
        //存放所有的分组类型对应的销售总额
        List<Map<String,Object>> mapList2 = new ArrayList<>();
        BigDecimal max = BigDecimal.ZERO;

        for(Map<String,Object> map : maps) {
            Map<String,Object> orderChartMap = new HashMap<>();
            groupByNames.add(map.get("groupByName").toString());
            orderChartMap.put("value",map.get("totalAmount").toString());
            orderChartMap.put("name",map.get("groupByName").toString());
            mapList2.add(orderChartMap);
            BigDecimal totalAmount = new BigDecimal(map.get("totalAmount").toString());
            if(totalAmount.compareTo(max) > 0) {
                max = totalAmount;
            }
        }
        model.addAttribute("groupByNames", JSON.toJSONString(groupByNames));
        model.addAttribute("mapList2",JSON.toJSONString(mapList2));
        model.addAttribute("max",max);
        return "saleChartsByPie";
    }


}
