package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import jxl.*;
import jxl.write.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.Boolean;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteEmployeeAndRoleRelation(id);
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Employee record) {
        Md5Hash md5Hash = new Md5Hash(record.getPassword(),record.getUsername(),2);
        record.setPassword(md5Hash.toString());
        mapper.insert(record);
        List<Role> roles = record.getRoles();
        if (roles != null) {
            for (Role role : roles) {
                mapper.insertEmployeeAndRoleRelation(record.getId(), role.getId());
            }
        }
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Employee record) {
        mapper.deleteEmployeeAndRoleRelation(record.getId());
        List<Role> roles = record.getRoles();
        if (roles != null) {
            for (Role role : roles) {
                mapper.insertEmployeeAndRoleRelation(record.getId(), role.getId());
            }
        }
        mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = mapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, mapper.queryForList(qo));
    }

    @Override
    public void changeState(Long id) {
        mapper.changeState(id);
    }

    @Override
    public List<Long> getRoleIdByEmpId(Long id) {
        return mapper.getRoleIdByEmpId(id);
    }

    @Override
    public void implorXsl(MultipartFile file)throws Exception {
        //读取xls文件
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //读取某个工作薄
        Sheet sheet = workbook.getSheet(0);

        //获取行
        int rows = sheet.getRows();

        for (int i=1;i<rows;i++){
            Employee employee = new Employee();
            employee.setUsername(sheet.getCell(0, i).getContents());
            employee.setRealname(sheet.getCell(1, i).getContents());
            employee.setTel(sheet.getCell(2, i).getContents());
            employee.setEmail(sheet.getCell(3, i).getContents());
            Cell cell = sheet.getCell(4, i);
            if (cell.getType() == CellType.DATE) {
                DateCell dc = (DateCell) cell;
                String cellcon = "";
                Date date = dc.getDate();
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy/MM/dd");
                cellcon = ds.format(date);
                Date newDate = ds.parse(cellcon);
                System.out.println(newDate.toLocaleString());
                employee.setInputtime(newDate);
            }
            employee.setState(true);
            Department dept = new Department();
            dept.setId(new Long(sheet.getCell(5, i).getContents()));
            employee.setDept(dept);
            //得到admin
            employee.setAdmin(new Boolean(sheet.getCell(6, i).getContents()));
            mapper.insert(employee);
        }
        //关闭资源
        workbook.close();
    }

    @Override
    public void export(HttpServletResponse response) throws Exception {
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=employee.xls");
        //创建xml文件
        WritableWorkbook book = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿的sheet:位置第1个,名字为工作表1,
        WritableSheet sheet = book.createSheet("工作表1",0);

        //添加标题
        sheet.addCell(new Label(0,0,"姓名"));
        sheet.addCell(new Label(1,0,"真实姓名"));
        sheet.addCell(new Label(2,0,"电话"));
        sheet.addCell(new Label(3,0,"邮箱"));
        sheet.addCell(new Label(4,0,"所在部门"));
        sheet.addCell(new Label(5,0,"入职时间"));

        List<Employee> employees = mapper.selectAll();
        for (int i=0;i<employees.size();i++){
            Employee employee = employees.get(i);
            Date inputtime = employee.getInputtime();
            DateFormat dateFormat = new DateFormat("yyyy-MM-dd");
            DateTime time = new DateTime(5,i,inputtime,new WritableCellFormat(dateFormat));
            //创建文本单元格
            //给工作簿添加内容  c是列,r是行,第三个参数数据p
            sheet.addCell(new Label(0,i,employee.getUsername()));
            sheet.addCell(new Label(1,i,employee.getRealname()));
            sheet.addCell(new Label(2,i,employee.getTel()));
            sheet.addCell(new Label(3,i,employee.getEmail()));
            sheet.addCell(new Label(4,i,employee.getDept().getName()));
            sheet.addCell(time);
        }
        //写入数据
        book.write();
        //关闭资源
        book.close();
    }

    @Override
    public Employee selectEmployeeByUsername(String username) {
        return mapper.selectEmployeeByUsername(username);
    }
}
