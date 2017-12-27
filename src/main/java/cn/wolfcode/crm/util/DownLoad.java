package cn.wolfcode.crm.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DownLoad {

    @ResponseBody
    public static void downLoad(List<Map<String, Object>> data, String[] headline, HttpServletResponse response) throws Exception {
        if (data != null && headline != null) {
            response.setHeader("Content-Disposition", "attachment;filename=newFile.xls");
            WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
            WritableSheet sheet = workbook.createSheet("page1", 0);
            for (int i = 0; i < headline.length; i++) {
                sheet.addCell(new Label(i, 0, headline[i]));

            }
            for (int i = 0, j = 1; i < data.size(); i++, j++) {
                int k = 0;
                Map<String, Object> map = data.get(i);
                Set<String> strings = map.keySet();
                for (String string : strings) {
                    sheet.addCell(new Label(k++, j, map.get(string).toString()));
                }
            }
            workbook.write();
            workbook.close();
        }
    }
}
