package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DisburseBigClassify;
import cn.wolfcode.crm.service.IDisburseBigClassifyService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("disburseBigClassify")
public class DisburseBigClassifyController {

    @Autowired
    IDisburseBigClassifyService service;

    @RequestMapping("myView")
    @RequiresPermissions("disburseBigClassify:myView")
    @RequiredName("大分类视图")
    public String view() {
        return "disburseBigClassify";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("disburseBigClassify:query")
    @RequiredName("大分类查询")
    public List<DisburseBigClassify> query() {
        return service.selectAll();
    }




    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("disburseBigClassify:saveOrUpdate")
    @RequiredName("大分类信息保存/编辑")
    public JsonResult saveOrUpdate(DisburseBigClassify disburseBigClassify) {
        try {
            if (disburseBigClassify.getId() == null) {
                service.insert(disburseBigClassify);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(disburseBigClassify);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
