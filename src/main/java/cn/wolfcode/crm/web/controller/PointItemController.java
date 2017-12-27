package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.PointItem;
import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPointItemService;
import cn.wolfcode.crm.service.IVipService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by Charles on 2017/12/21.
 */
@Controller
@RequestMapping("pointItem")
public class PointItemController {

    @Autowired
    IPointItemService pointItemService;
    @Autowired
    private IVipService vipService;

    @RequestMapping("view")
    @RequiresPermissions("pointItem:view")
    @RequiredName("积分明细视图")
    public String view() {
        return "pointItem";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("pointItem:query")
    @RequiredName("积分明细查询")
    public PageResult query(QueryObject qo) {
        return pointItemService.query(qo);
    }


    //积分充值
    @RequestMapping("recharge")
    @ResponseBody
    public JsonResult recharge(Long id, Long changeNum, boolean flag) {
        try {
            Vip vip = vipService.selectByPrimaryKey(id);
            PointItem pointItem = new PointItem();
            pointItem.setVip(vip);
            pointItem.getVip().setVipNumber(vip.getVipNumber());
            pointItem.getVip().setVipName(vip.getVipName());
            pointItem.setCard(vip.getVipcard());
            pointItem.setRemark("");
            pointItem.setOpTime(new Date());
            //点击充值按钮时
            if (flag) {
                //修改会员总积分
                vip.setCurrentpoint(vip.getCurrentpoint() + changeNum);
                vip.setTotalpoint(vip.getTotalpoint() + changeNum);
                //保存积分明细
                pointItem.setChangeNum(changeNum);
                //设置操作类型为积分充值
                pointItem.setOp(1);
                //更新数据库中会员当前积分
            } else {
                if (vip.getCurrentpoint() < changeNum) {
                    return new JsonResult(false, "积分不足,请充值");
                }
                //修改会员当前积分
                vip.setCurrentpoint(vip.getCurrentpoint() - changeNum);
                vip.setTotalpoint(vip.getTotalpoint() - changeNum);
                //修改积分明细
                pointItem.setChangeNum(0 - changeNum);
                //设置操作类型为积分充值
                pointItem.setOp(0);
                //更新数据库中会员当前积分
            }

            vipService.pointRecharge(vip);
            pointItemService.insert(pointItem);
            return new JsonResult("保存成功");
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    //积分清零
    @RequestMapping("clear")
    @ResponseBody
    public JsonResult clear(Long id) {
        try {
            if (id != null) {
                //查询该id对应的会员
                Vip vip = vipService.selectByPrimaryKey(id);
                Long currentpoint = vip.getCurrentpoint();
                if (currentpoint >= 0) {
                    PointItem pointItem = new PointItem();

                    pointItem.setCard(vip.getVipcard());
                    pointItem.setRemark("");
                    pointItem.setOpTime(new Date());
                    pointItem.setOp(0);
                    Subject subject = SecurityUtils.getSubject();
                    pointItem.setInputUser((Employee)subject.getPrincipal());
                    pointItem.setInputUser(vip.getEmployee());
                    //保存积分明细
                    pointItem.setVip(vip);
                    //清零后,该会员积分为0
                    vip.setCurrentpoint(0L);
                    //积分变动额为负数
                    pointItem.setChangeNum(0L - currentpoint);
                    pointItemService.insert(pointItem);
                    //修改会员当前积分为0
                    vipService.clearPoint(id);
                }
            }
            return new JsonResult("保存成功");
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }


    @RequestMapping("save")
    @ResponseBody
    @RequiresPermissions("pointItem:save")
    @RequiredName("积分明细信息保存")
    public JsonResult save(PointItem pointItem) {
        try {
            if (pointItem.getId() == null) {
                pointItemService.insert(pointItem);
                return new JsonResult("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

}
