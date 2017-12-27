package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Orderbill;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IOrderbillService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orderbill")
public class OrderbillController {

    @Autowired
    private IOrderbillService orderbillService;

    /**
     * 采购订单页面
     * @return
     */
    @RequestMapping("view")
    @RequiresPermissions("orderbill:view")
    @RequiredName("采购订单页面")
    public String view() {
        return "orderbill";
    }

    /**
     * 退货订单页面
     * @return
     */
    @RequestMapping("returnbillView")
    @RequiresPermissions("returnbill:view")
    @RequiredName("退货订单页面")
    public String returnBillView() {
        return "returnbill";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("orderbill:query")
    @RequiredName("采购订单列表")
    public PageResult query(QueryObject qo) {

        return orderbillService.query(qo);
    }

    @RequestMapping("querydepotbill")
    @ResponseBody
    @RequiresPermissions("returnbill:querydepotbill")
    @RequiredName("入库单列表")
    public PageResult querydepotbill(QueryObject qo) {

        return orderbillService.queryReturnbill(qo);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("orderbill:saveOrUpdate")
    @RequiredName("采购订单保存/编辑")
    public JsonResult saveOrUpdate(Orderbill orderbill) {
        try {
            if (orderbill.getId() == null) {
                orderbillService.insert(orderbill);
                return new JsonResult("保存成功");
            } else {
                orderbillService.updateByPrimaryKey(orderbill);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("orderbill:delete")
    @RequiredName("采购订单删除")
    public JsonResult delete(Long id) {
        try {
            orderbillService.deleteByPrimaryKey(id);
            return new JsonResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("audit")
    @ResponseBody
    @RequiresPermissions("orderbill:audit")
    @RequiredName("采购订单审核")
    public JsonResult audit(Long id) {
        try {
            orderbillService.audit(id);
            return new JsonResult("审核成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "审核失败");
    }


    /**
     * 订单退货
     * @param id
     * @return
     */
    @RequestMapping("returnbill")
    @ResponseBody
    @RequiresPermissions("returnbill:returnbill")
    @RequiredName("订单退货")
    public JsonResult returnbill(Long id) {
        try {
            orderbillService.returnbill(id);
            return new JsonResult("退货成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "该订单数据有误,不能退货");
    }
}
