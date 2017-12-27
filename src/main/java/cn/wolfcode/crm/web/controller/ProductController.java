package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ProductQueryObject;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.RequiredName;
import cn.wolfcode.crm.util.UploadUtil;
import com.alibaba.druid.util.StringUtils;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    IProductService service;
    @Autowired
    private ServletContext ctx;

    @RequestMapping("view")
    @RequiresPermissions("product:view")
    @RequiredName("商品视图")
    public String view() {
        return "product/product";
    }

    @RequestMapping("saveView")
    @RequiresPermissions("product:saveView")
    @RequiredName("新增商品视图")
    public String saveView() {

        return "product/product";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiredName("商品查询")
    @RequiresPermissions("product:query")
    public PageResult query(ProductQueryObject qo) {
        PageResult result = service.query(qo);
        return result;
    }

    @RequestMapping("selectProByName")
    @ResponseBody
    public List<Product> query(String proName) {
        return service.selectByProName(proName);
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiredName("商品信息保存/修改")
    @RequiresPermissions("product:saveOrUpdate")
    public JsonResult saveOrUpdate(Product product, MultipartFile pic, Model model) {
        try {
            // 更新时如果要更换图片,说明之前是有图片的,更换图片就需要删除之前的图片,再上传新的图片
            if (pic != null && !StringUtils.isEmpty(product.getImagePath())) {
                UploadUtil.deleteFile(ctx, product.getImagePath());
            }
            // 更换图片,获取要保存文件的相对路径
            if (pic != null) {
                String fileName = ctx.getRealPath("/upload/");
                String imagePath = UploadUtil.upload(pic, fileName);
                product.setImagePath(imagePath);
                model.addAttribute("product", product);
            }
            if (product.getId() == null) {
                product.setState(true);
                service.insert(product);
                return new JsonResult("保存成功");
            } else {
                service.updateByPrimaryKey(product);
                return new JsonResult("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("changeState")
    @RequiresPermissions("product:changeState")
    @RequiredName("商品上下架")
    @ResponseBody
    public JsonResult changeState(Long id) {
        try {
            service.changeState(id);
            return new JsonResult("保存成功");
        } catch (Exception e) {

        }
        return new JsonResult(false, "操作失败!");
    }

    @RequestMapping("delete")
    @RequiresPermissions("product:delete")
    @RequiredName("商品删除")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            service.deleteByPrimaryKey(id);
            return new JsonResult("保存成功");
        }catch (Exception e){

        }
        return new JsonResult(false,"操作失败!");
    }

    //文件导入
    @RequestMapping("imlport")
    @ResponseBody
    public JsonResult implortXsl(MultipartFile file) {
        try {
            service.implorXsl(file);
            return new JsonResult("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "上传失败");
        }

    }

    //文件导出
    @RequestMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        //解决下载文件名是export.do的问题,设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //添加标题
        sheet.addCell(new Label(0, 0, "商品名"));
        sheet.addCell(new Label(1, 0, "商品编码"));
        sheet.addCell(new Label(2, 0, "进货价"));
        sheet.addCell(new Label(3, 0, "零售价"));

        //获取真实的员工数据
        List<Product> products = service.selectAll();

        for (int i = 0, j = 1; i < products.size(); i++, j++) {
            Product product = products.get(i);
            //往工作簿中添加内容
            sheet.addCell(new Label(0, j, product.getName()));
            sheet.addCell(new Label(1, j, product.getSn()));
            sheet.addCell(new Label(2, j, product.getCostprice().toString()));
            sheet.addCell(new Label(3, j, product.getSaleprice().toString()));
        }
        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }
}
