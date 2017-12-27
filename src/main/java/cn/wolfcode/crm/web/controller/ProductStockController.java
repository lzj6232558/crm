package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Productstock;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ProductstockQueryObject;
import cn.wolfcode.crm.service.IProductStockService;
import cn.wolfcode.crm.util.RequiredName;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("productstock")
public class ProductStockController {

    @Autowired
    private IProductStockService productStockService;

    @RequestMapping("view")
    @RequiresPermissions("productstock:view")
    @RequiredName("库存页面")
    public String view() {
        return "productstock";
    }

    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("productstock:query")
    @RequiredName("库存列表")
    public PageResult query(ProductstockQueryObject qo) {

        return productStockService.query(qo);
    }

    @RequestMapping("export")
    @ResponseBody
    @RequiresPermissions("productstock:export")
    @RequiredName("导出库存信息")
    public void export(HttpServletResponse response, ProductstockQueryObject qo) throws Exception {

        //文件下载响应头
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");

        //创建xls文件
        WritableWorkbook workbook =
                Workbook.createWorkbook(response.getOutputStream());

        //创建工作簿
        WritableSheet sheet = workbook.createSheet("库存信息",0);


        //添加标题
        sheet.addCell(new Label(0,0,"商品编码"));
        sheet.addCell(new Label(1,0,"商品名称"));
        sheet.addCell(new Label(2,0,"商品单价"));
        sheet.addCell(new Label(3,0,"所在仓库"));
        sheet.addCell(new Label(4,0,"库存数量"));
        sheet.addCell(new Label(5,0,"库存汇总"));


        //草泥马,发送请求时,rows和page不发送,qo里的rows和page都为为0,查不到数据
        //在xml文件中加判断条件
        //获取真实的员工数据
        PageResult result = productStockService.query(qo);
        List<Productstock> productstocks = (List<Productstock>) result.getRows();

        for (int i = 0,j = 1;i < productstocks.size();i++,j++) {
            Productstock productstock = productstocks.get(i);
            //往工作簿中添加内容
            sheet.addCell(new Label(0,j,productstock.getProduct().getSn()));
            sheet.addCell(new Label(1,j,productstock.getProduct().getName()));
            sheet.addCell(new Label(2,j,productstock.getProduct().getSaleprice().toString()));
            sheet.addCell(new Label(3,j,productstock.getDepot().getName()));
            sheet.addCell(new Label(4,j,productstock.getStorenumber().toString()));
            sheet.addCell(new Label(5,j,productstock.getAmount().toString()));
        }

        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }

}
