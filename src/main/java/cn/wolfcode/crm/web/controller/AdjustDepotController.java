package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Adjustdepot;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IAdjustDepotService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("adjustdepot")
public class AdjustDepotController {

    @Autowired
    private IAdjustDepotService adjustDepotService;

    @RequestMapping("view")
    @RequiresPermissions("adjustdepot:view")
    @RequiredName("仓库调拨页面")
    public String view() {
        return "adjustdepot";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("adjustdepot:query")
    @RequiredName("仓库调拨条目")
    public PageResult query(QueryObject qo) {

        return adjustDepotService.query(qo);
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("adjustdepot:saveOrUpdate")
    @RequiredName("库存调拨保存")
    public JsonResult saveOrUpdate(Adjustdepot adjustdepot) {
        try {
            if (adjustdepot.getId() == null) {
                adjustDepotService.insert(adjustdepot);
                return new JsonResult("保存成功");
            } else {
                adjustDepotService.updateByPrimaryKey(adjustdepot);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "调出仓库库存不足");
    }

    @RequiresPermissions("adjustdepot:delete")
    @RequiredName("库存调拨删除")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            adjustDepotService.deleteByPrimaryKey(id);
            return new JsonResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(false, "删除失败");
    }
}
