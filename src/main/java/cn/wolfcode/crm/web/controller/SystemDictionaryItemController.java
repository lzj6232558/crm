package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemDictionaryItem")
public class SystemDictionaryItemController {

    @Autowired
    private ISystemDictionaryItemService service;

    @RequestMapping("myView")
    @RequiresPermissions("systemDictionaryItem:myView")
    @RequiredName("明细视图")
    public String view() {
        return "systemDictionaryItem/systemDictionaryItem";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<SystemDictionaryItem> query() {
        return service.selectAll();
    }

    @RequestMapping("queryById")
    @ResponseBody
    public List<SystemDictionaryItem> queryById(SystemDictionaryItem item) {
        return service.queryById(item);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemDictionaryItem systemDictionaryItem) {
        try {
            if (systemDictionaryItem.getId() == null) {
                service.insert(systemDictionaryItem);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(systemDictionaryItem);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try{
            service.deleteByPrimaryKey(id);
            return new JsonResult("删除完成");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new JsonResult(false,"删除失败");
    }
}
