package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IGiftService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import cn.wolfcode.crm.util.UploadUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

/**
 * Created by Charles on 2017/12/21.
 */
@Controller
@RequestMapping("gift")
public class GiftController {

    @Autowired
    IGiftService giftService;
    @Autowired
    private ServletContext ctx;

    @RequestMapping("view")
    @RequiresPermissions("gift:view")
    @RequiredName("礼品视图")
    public String view() {
        return "gift";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("gift:query")
    @RequiredName("礼品查询")
    public PageResult query(QueryObject qo) {
        return giftService.query(qo);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("gift:saveOrUpdate")
    @RequiredName("礼品信息保存/编辑")
    public JsonResult saveOrUpdate(Gift gift, MultipartFile pic, Model model) {
        try {
            // 更新时如果要更换图片,说明之前是有图片的,更换图片就需要删除之前的图片,再上传新的图片
            if (pic != null && !StringUtils.isEmpty(gift.getImagepath())) {
                UploadUtil.deleteFile(ctx, gift.getImagepath());
            }
            // 更换图片,获取要保存文件的相对路径
            if (pic != null) {
                String fileName = ctx.getRealPath("/upload/");
                String imagePath = UploadUtil.upload(pic, fileName);
                gift.setImagepath(imagePath);
            model.addAttribute("gift",gift);
            }
            if (gift.getId() == null) {
                giftService.insert(gift);
                return new JsonResult("保存成功");
            } else {
                giftService.updateByPrimaryKey(gift);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("gift:delete")
    @RequiredName("礼品删除")
    public JsonResult delete(Long id) {
        try {
            giftService.deleteByPrimaryKey(id);
            return new JsonResult("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }
}
