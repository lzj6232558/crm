package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.RecordQueryObject;
import cn.wolfcode.crm.service.IConsumptionRecordService;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("consumptionRecord")
public class ConsumptionRecordController {
    @Autowired
    IConsumptionRecordService consumptionRecordService;

    @RequestMapping("myView")
    @RequiresPermissions("consumptionRecord/myView")
    @RequiredName("充值信息视图")
    public String view(){
        return "consumptionRecord/consumptionRecord";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("consumptionRecord/query")
    @RequiredName("充值信息列表")
    public PageResult query(Model model , RecordQueryObject qo){
        PageResult query = consumptionRecordService.query(qo);
        return query;
    }

}
