package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Supplier;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISupplierService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Supplier> selectAll() {

        return supplierService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions("supplier:view")
    @RequiredName("供应商页面")
    public String view() {

        return "supplier";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("supplier:query")
    @RequiredName("供应商列表")
    public PageResult query(QueryObject qo) {

        return supplierService.query(qo);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("supplier:saveOrUpdate")
    @RequiredName("供应商保存/编辑")
    public JsonResult saveOrUpdate(Supplier supplier) {
        try {
            if (supplier.getId() == null) {
                supplierService.insert(supplier);
                return new JsonResult("保存成功");
            } else {
                supplierService.updateByPrimaryKey(supplier);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequiresPermissions("supplier:delete")
    @RequiredName("供应商删除")
    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            supplierService.deleteByPrimaryKey(id);
            return new JsonResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(false, "删除失败");
    }



}
