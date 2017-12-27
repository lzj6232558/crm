package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int queryForCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);

    void changeState(Long id);

    void insertEmployeeAndRoleRelation(@Param("employee_id")Long employee_id, @Param("role_id")Long role_id);

    void  deleteEmployeeAndRoleRelation(Long id);

    List<Long> getRoleIdByEmpId(Long id);

    Employee selectEmployeeByUsername(String username);
}