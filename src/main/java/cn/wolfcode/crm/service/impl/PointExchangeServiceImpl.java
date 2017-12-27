package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.PointExchange;
import cn.wolfcode.crm.mapper.PointExchangeMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPointExchangeService;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Charles on 2017/12/21.
 */
@Service
public class PointExchangeServiceImpl implements IPointExchangeService {
    @Autowired
    private PointExchangeMapper pointExchangeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return pointExchangeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PointExchange record) {
        return pointExchangeMapper.insert(record);
    }

    @Override
    public PointExchange selectByPrimaryKey(Long id) {
        return pointExchangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PointExchange> selectAll() {
        return pointExchangeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(PointExchange record) {
        return pointExchangeMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = pointExchangeMapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, pointExchangeMapper.queryForList(qo));
    }

    /**
     * 生成excel表格基本为三个步骤： 1.创建excel工作簿 2.创建工作表sheet 3.创建单元格并添加到sheet中
     *
     * @param response
     * @throws Exception
     */
    @Override
    public void exportXls(HttpServletResponse response) throws IOException, WriteException {
        // 文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fileName = sdf.format(new Date()) + ".xls";

        response.setContentType("application/x-excel");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename="
                + fileName);// excel文件名

        // 业务数据
        // 1.创建excel文件
        WritableWorkbook book = Workbook.createWorkbook(response
                .getOutputStream());
        // 居中
        //时间格式
        WritableCellFormat wf = new WritableCellFormat();
        wf.setAlignment(Alignment.CENTRE);

        WritableSheet sheet = book.createSheet("sheet1", 0);
        ;
        SheetSettings settings = null;
        //设置标题
        sheet.addCell(new Label(0, 0, "会员卡号", wf));
        sheet.addCell(new Label(1, 0, "会员名称", wf));
        sheet.addCell(new Label(2, 0, "礼品名称", wf));
        sheet.addCell(new Label(3, 0, "兑换数量", wf));
        sheet.addCell(new Label(4, 0, "操作人员", wf));
        sheet.addCell(new Label(5, 0, "兑换时间", wf));
        //获取真实员工数据
        List<PointExchange> list = pointExchangeMapper.selectAll();
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            settings = sheet.getSettings();
            settings.setVerticalFreeze(2);
            PointExchange pointExchange = list.get(i);
            //创建文本单元格
            sheet.addCell(new Label(0, j, pointExchange.getVip().getVipNumber().toString(), wf));
            sheet.addCell(new Label(1, j, pointExchange.getVip().getVipName(), wf));
            sheet.addCell(new Label(2, j, pointExchange.getGift().getName(), wf));
            sheet.addCell(new Label(3, j, pointExchange.getNumber().toString(), wf));
            sheet.addCell(new Label(4, j, pointExchange.getInputUser().getUsername(), wf));
            if (pointExchange.getInputTime() != null) {
                sheet.addCell(new Label(5, j, sdf2.format(pointExchange.getInputTime()),wf));
            }
        }
        // 6.写入excel并关闭
        book.write();
        book.close();

    }

}
