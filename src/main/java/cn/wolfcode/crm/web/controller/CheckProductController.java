package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Checkrecord;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICheckRecordService;
import cn.wolfcode.crm.service.IcheckproductService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品盘点
 */
@Controller
@RequestMapping("checkproduct")
public class CheckProductController {

    @Autowired
    private IcheckproductService checkproductService;

    @Autowired
    private ICheckRecordService checkRecordService;

    @RequestMapping("view")
    @RequiresPermissions("checkproduct:view")
    @RequiredName("产品盘点页面")
    public String view() {

        return "checkproduct";
    }

    @RequestMapping("checkproduct")
    @RequiresPermissions("checkproduct:checkproduct")
    @RequiredName("产品盘点列表")
    @ResponseBody
    public PageResult checkproduct(QueryObject qo) {

        return checkproductService.query(qo);
    }


    /**
     * 商品数量确认
     * @return
     */
    @RequestMapping("check")
    @RequiresPermissions("checkproduct:check")
    @RequiredName("商品数量确认")
    @ResponseBody
    public JsonResult check(Long id) {

        try {
            checkproductService.check(id);
            return new JsonResult("确认成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(false, "确认失败");
    }


    /**
     * 商品数量确认
     * @return
     */
    @RequestMapping("adjust")
    @RequiresPermissions("checkproduct:adjust")
    @RequiredName("商品数量调整")
    @ResponseBody
    public JsonResult adjust(BigDecimal newNumber, Long id, Checkrecord checkrecord) {

        try {
            checkproductService.adjust(newNumber,id);
            checkRecordService.insert(checkrecord);
            return new JsonResult("调整成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonResult(false, "调整失败");
    }

    /**
     * 查询盘点记录
     * @return
     */
    @RequestMapping("record")
    @RequiresPermissions("checkproduct:record")
    @RequiredName("盘点记录")
    @ResponseBody
    public List<Checkrecord> record() {
        return checkRecordService.selectAll();
    }


}
