package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Vipcard;
import cn.wolfcode.crm.service.IVipcardService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("vipcard")
public class VipcardController {

    @Autowired
    IVipcardService service;

    @RequestMapping("myView")
    @RequiresPermissions("vipcard:myView")
    @RequiredName("会员卡视图")
    public String view() {
        return "/vipcard/vipcard";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("vipcard:query")
    @RequiredName("会员卡查询")
    public List<Vipcard> query() {
        return service.selectAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("vipcard:saveOrUpdate")
    @RequiredName("会员卡信息保存/编辑")
    public JsonResult saveOrUpdate(Vipcard vipcard) {
        try {
            if (vipcard.getId() == null) {
                service.insert(vipcard);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(vipcard);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions("vipcard:changeState")
    @RequiredName("会员卡状态修改")
    public JsonResult changeState(Long id) {
        try {
            service.changeState(id);
            return new JsonResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
