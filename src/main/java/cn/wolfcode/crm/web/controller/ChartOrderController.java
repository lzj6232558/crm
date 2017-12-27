package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.OrderSaleQueryObject;
import cn.wolfcode.crm.service.IChartOrderService;
import cn.wolfcode.crm.util.DownLoad;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("chart")
public class ChartOrderController {

    @Autowired
    IChartOrderService chartOrderService;

    @RequestMapping("myView")
    @RequiredName("销售报表视图")
    @RequiresPermissions("pointItem:view")
    public String view(Model model) {
        return "chart/view";
    }

    @RequestMapping("analyze")
    @RequiresPermissions("chart:sale")
    @RequiredName("销售分析视图")
    public String analyze(Model model) {
        return "chart/analyze";
    }

    @RequestMapping("sale")
    @ResponseBody
    @RequiresPermissions("chart:sale")
    @RequiredName("销售查询")
    public List<Map<String, Object>> saleChartsByBar(HttpServletRequest request, @ModelAttribute("qo") OrderSaleQueryObject qo) {
        List<Map<String, Object>> cherts = chartOrderService.saleChart(qo);
        request.getSession().setAttribute("qo", qo);
        return cherts;
    }

    @RequestMapping("salesAnalyze")
    @ResponseBody
    @RequiresPermissions("chart:analyze")
    @RequiredName("销售分析查询")
    public List<Map<String, Object>> analyze(String type) {
        OrderSaleQueryObject qo = new OrderSaleQueryObject();
        if (type != null) {
            qo.setGroupBy(type);
        }
        List<Map<String, Object>> cherts = chartOrderService.selectGroupBy(qo);
        return cherts;
    }

    @RequestMapping("sumProfit")
    @ResponseBody
    public BigDecimal sumProfit() {
        BigDecimal sumProfit = chartOrderService.selectSumProfit();
        return sumProfit;
    }

    @RequestMapping("download")
    @ResponseBody
    @RequiresPermissions("chart:download")
    @RequiredName("销售分析报表下载")
    public void derivation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        OrderSaleQueryObject qo = (OrderSaleQueryObject) request.getSession().getAttribute("qo");
        List<Map<String, Object>> cherts = chartOrderService.saleChart(qo);
        String[] strings = {"销售总金额", "消费对象", "个数", "销售价", "进货价", "折扣","消费时间", "商品名","利润"};
        DownLoad.downLoad(cherts, strings, response);
    }
}
