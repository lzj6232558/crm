package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DisburseBigClassify;
import cn.wolfcode.crm.domain.DisburseDetail;
import cn.wolfcode.crm.query.DisburseQueryObject;
import cn.wolfcode.crm.service.IDisburseDetailService;
import cn.wolfcode.crm.util.DownLoad;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("disburse")
public class DisburseDetailController {

    @Autowired
    IDisburseDetailService service;

    @RequestMapping("myView")
    @RequiresPermissions("disburse:myView")
    @RequiredName("支出视图")
    public String view() {
        return "disburse/disburse";
    }

    @RequestMapping("analyze")
    @RequiresPermissions("disburse:analyze")
    @RequiredName("支出分析")
    public String analyze() {
        return "disburse/analyze";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("disburse:query")
    @RequiredName("支出查询")
    public List<DisburseDetail> query() {
        List<DisburseDetail> dd = service.selectAll();
        for (DisburseDetail d : dd) {
            String classify = d.getLittleClassify().getClassify();
            String classify1 = d.getBigClassify().getClassify();
            DisburseBigClassify bigClassify = d.getBigClassify();
            bigClassify.setClassify(classify1 + "--" + classify);
        }
        return dd;
    }

    @RequestMapping("analyzeInquire")
    @ResponseBody
    @RequiresPermissions("disburse:analyzeInquire")
    @RequiredName("支出分析查询")
    public List<Map<String, Object>> analyzeInquire(String type) {
        DisburseQueryObject qo = new DisburseQueryObject();
        if (type != null) {
            qo.setGroupBy(type);
        }
        List<Map<String, Object>> map = service.selectGroupBy(qo);
        return map;
    }

    @RequestMapping("download")
    @ResponseBody
    @RequiresPermissions("disburse:download")
    @RequiredName("支出报表下载")
    public void derivation(HttpServletResponse response) throws Exception {
        List<DisburseDetail> dd = service.selectAll();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (DisburseDetail d : dd) {
            Map<String, Object> map = new HashMap<>();
            String classify = d.getLittleClassify().getClassify();
            String classify1 = d.getBigClassify().getClassify();
            map.put("classify", classify1 + "--" + classify);
            map.put("money", d.getMoney());
            map.put("disburseTime", d.getDisburseTime().toString());
            map.put("disburseUser", d.getDisburseUser().getUsername());
            map.put("detail", d.getDetail());
            list.add(map);
        }
        String[] strings = {"支出分类", "支出金额", "支出时间", "支出人员", "备注"};
        DownLoad.downLoad(list, strings, response);
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("disburse:saveOrUpdate")
    @RequiredName("支出信息保存/编辑")
    public JsonResult saveOrUpdate(DisburseDetail disburseDetail) {
        System.out.println(disburseDetail.getDisburseTime());
        try {
            if (disburseDetail.getId() == null) {
                service.insert(disburseDetail);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(disburseDetail);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("disburse:delete")
    @RequiredName("支出删除")
    public JsonResult delete(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new JsonResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
