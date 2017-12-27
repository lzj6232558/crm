package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepotService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("depot")
public class DepotController {

    @Autowired
    private IDepotService depotService;

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Depot> selectAll() {

        return depotService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions("depot:view")
    @RequiredName("仓库页面")
    public String view() {
        return "depot";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("depot:query")
    @RequiredName("仓库列表")
    public PageResult query(QueryObject qo) {

        return depotService.query(qo);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("depot:saveOrUpdate")
    @RequiredName("仓库保存/编辑")
    public JsonResult saveOrUpdate(Depot depot) {
        try {
            if (depot.getId() == null) {
                depot.setState(true);
                depotService.insert(depot);
                return new JsonResult("保存成功");
            } else {
                depotService.updateByPrimaryKey(depot);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions("depot:changeState")
    @RequiredName("仓库状态修改")
    public JsonResult changeState(Long id) {
        try {
            depotService.changeState(id);
            return new JsonResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("list")
    @ResponseBody
    @RequiresPermissions("department:lsit")
    public List<Depot> query() {
        return depotService.selectAll();
    }
}
