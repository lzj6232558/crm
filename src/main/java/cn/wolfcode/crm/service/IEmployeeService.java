package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IEmployeeService {
    void deleteByPrimaryKey(Long id);

    void insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    void updateByPrimaryKey(Employee record);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    List<Long> getRoleIdByEmpId(Long id);

    void implorXsl(MultipartFile file)throws Exception ;

    void export(HttpServletResponse response) throws Exception;

    Employee selectEmployeeByUsername(String username);
}
