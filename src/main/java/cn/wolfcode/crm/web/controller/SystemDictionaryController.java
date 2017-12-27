package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemDictionary")
public class SystemDictionaryController {

    @Autowired
    private ISystemDictionaryService service;

    @RequestMapping("myView")
    @RequiresPermissions("systemDictionary:myView")
    @RequiredName("字典视图")
    public String view() {
        return "systemDictionary/systemDictionary";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("systemDictionary:query")
    @RequiredName("字典查询")
    public List<SystemDictionary> query() {
        return service.selectAll();
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemDictionary systemDictionary) {
        try {
            if (systemDictionary.getId() == null) {
                service.insert(systemDictionary);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(systemDictionary);
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
