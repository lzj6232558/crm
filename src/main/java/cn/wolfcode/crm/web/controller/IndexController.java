package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.query.OrderSaleQueryObject;
import cn.wolfcode.crm.service.IChartOrderService;
import cn.wolfcode.crm.service.IMenuService;
import cn.wolfcode.crm.service.IVipService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @RequestMapping("login")
    public String login(){
        return "forward:/login.jsp";
    }

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IVipService vipService;
    @Autowired
    IChartOrderService chartOrderService;

    @RequestMapping("index")
    public String main(Model model){
        //系统模块
        List<Menu> system = menuService.selectByPrentIdKey(1L);
        model.addAttribute("system",system);

        //会员模块
        List<Menu> vip = menuService.selectByPrentIdKey(25L);
        model.addAttribute("vip",vip);

        //商品模块
        List<Menu> product = menuService.selectByPrentIdKey(31L);
        model.addAttribute("product",product);

        //礼品模块
        List<Menu> pointex = menuService.selectByPrentIdKey(30L);
        model.addAttribute("pointex",pointex);

        //库存模块
        List<Menu> depot = menuService.selectByPrentIdKey(35L);
        model.addAttribute("depot",depot);

        //销售分析
        List<Menu> chart = menuService.selectByPrentIdKey(43L);
        model.addAttribute("chart",chart);

        List<Map<String, Object>> report = vipService.report();
        List<Map<String,Object>> list = new ArrayList<>();
        List<String > nameList = new ArrayList<>();
        for (Map<String, Object> map : report) {
            Map<String ,Object> valueMap = new LinkedHashMap<>();
            valueMap.put("value",map.get("amount"));
            nameList.add((String) map.get("name"));
            valueMap.put("name",map.get("name"));
            list.add(valueMap);
        }

        try {
            String Jsonlist = new ObjectMapper().writeValueAsString(list);
            String NameList = new ObjectMapper().writeValueAsString(nameList);
            model.addAttribute("Jsonlist",Jsonlist);
            model.addAttribute("NameList",NameList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //销售饼图
        OrderSaleQueryObject qo = new OrderSaleQueryObject();
        List<Map<String, Object>> cherts = chartOrderService.selectGroupBy(qo);
        //将数据(销售人和销售总额)使用Json格式的字符串共享到前台去
        List<String> groupByName = new ArrayList<>();
        List<Map<String, String>> JsonMap = new ArrayList<>();
        for (Map<String, Object> chert : cherts) {
            Map<String, String> map = new LinkedHashMap<>();
            String value = chert.get("saleAmount").toString();
            String name = chert.get("groupByName").toString();
            System.out.println(value);
            System.out.println(name);
            groupByName.add(name);
            map.put("value", value);
            map.put("name", name);
            JsonMap.add(map);
        }
        model.addAttribute("groupByName", JSON.toJSONString(groupByName));
        model.addAttribute("JsonMap", JSON.toJSONString(JsonMap));

        return "forward:/main.jsp";
    }
}
