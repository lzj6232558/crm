package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.RechargeRecord;
import cn.wolfcode.crm.service.IRechargeRecordService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("rechargeRecord")
public class RechargeRecordController {

    @Autowired
    IRechargeRecordService service;

    @RequestMapping("myView")
    @RequiresPermissions("rechargeRecord:myView")
    @RequiredName("充值视图")
    public String view() {
        return "rechargeRecord/rechargeRecord";
    }

    @RequestMapping("recharge")
    @ResponseBody
    @RequiresPermissions("rechargeRecord:recharge")
    @RequiredName("会员充值")
    public JsonResult recharge(RechargeRecord record) {
        try {
            service.insert(record);
            return new JsonResult("充值成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());

        }
    }

    @RequestMapping("refund")
    @ResponseBody
    @RequiresPermissions("rechargeRecord:refund")
    @RequiredName("会员退卡")
    public  JsonResult refund(RechargeRecord record){
        try{
            service.refundVip(record);
            return new JsonResult("退卡成功");
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }
    @RequestMapping("order")
    @ResponseBody
    public List<RechargeRecord> order(Long number){
        if(number != null){
             return service.selectRechargeRecordByVipNumber(number);
        }
        return null;
    }

}
