package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DisburseLittleClassify;
import cn.wolfcode.crm.service.IDisburseLittleClassifyService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("littleClassify")
public class DisburseLittleClassifyController {

    @Autowired
    IDisburseLittleClassifyService service;

    @RequestMapping("myView")
    @RequiresPermissions("disburseLittleClassify :myView")
    @RequiredName("小分类视图")
    public String view() {
        return "disburseLittleClassify ";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("disburseLittleClassify :query")
    @RequiredName("小分类查询")
    public List<DisburseLittleClassify> query() {
        return service.selectAll();
    }

    @RequestMapping("getLittleByBigName")
    @ResponseBody
    public List<DisburseLittleClassify> getLittleByBigName(String bigName) {
        return service.getLittleByBigName(bigName);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("disburseLittleClassify :saveOrUpdate")
    @RequiredName("小分类信息保存/编辑")
    public JsonResult saveOrUpdate(DisburseLittleClassify disburseLittleClassify) {
        try {
            if (disburseLittleClassify.getBigClassify().getId() == null) {
                return new JsonResult(false, "请选择大分类,再进行保存");
            }
            if (disburseLittleClassify.getId() == null) {
                service.insert(disburseLittleClassify);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(disburseLittleClassify);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
