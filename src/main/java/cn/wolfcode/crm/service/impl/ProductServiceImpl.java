package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.domain.Unit;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IProductService;
import jxl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        productMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(Product record) {
        productMapper.insert(record);
        return 0;
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        productMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = productMapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, productMapper.queryForList(qo));
    }

    @Override
    public void changeState(Long id) {
        productMapper.changeState(id);
    }

    @Override
    public void implorXsl(MultipartFile file) throws Exception{
        //读取xls文件
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //读取某个工作薄
        Sheet sheet = workbook.getSheet(0);

        //获取行
        int rows = sheet.getRows();

        for (int i=1;i<rows;i++){
            Product product = new Product();
            product.setName(sheet.getCell(0, i).getContents());
            product.setSn(sheet.getCell(1, i).getContents());
            product.setCostprice(new BigDecimal(sheet.getCell(2, i).getContents()));
            product.setSaleprice(new BigDecimal(sheet.getCell(3, i).getContents()));
            Cell cell = sheet.getCell(4, i);
            if (cell.getType() == CellType.DATE) {
                DateCell dc = (DateCell) cell;
                String cellcon = "";
                Date date = dc.getDate();
                SimpleDateFormat ds = new SimpleDateFormat("yyyy/MM/dd");
                cellcon = ds.format(date);
                Date newDate = ds.parse(cellcon);
                System.out.println(newDate.toLocaleString());
                product.setInputtime(newDate);
            }
            product.setState(true);
            Unit unit = new Unit();
            unit.setId(new Long(sheet.getCell(5, i).getContents()));
            product.setUnit(unit);
            productMapper.insert(product);
        }
        //关闭资源
        workbook.close();
    }

    @Override
    public List<Product> selectByProName(String proName) {
        return productMapper.selectByProName(proName);
    }
}
