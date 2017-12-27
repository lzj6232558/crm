package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.DisburseDetail;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.DisburseDetailMapper;
import cn.wolfcode.crm.mapper.DisburseLittleClassifyMapper;
import cn.wolfcode.crm.query.DisburseQueryObject;
import cn.wolfcode.crm.service.IDisburseDetailService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DisburseDetailImpl implements IDisburseDetailService {

    @Autowired
    DisburseDetailMapper detailMapper;

    @Override
    public void insert(DisburseDetail record) {
        Employee user = record.getDisburseUser();
        if (user == null) {
            Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
            record.setDisburseUser(employee);
        }
        detailMapper.insert(record);
    }

    @Override
    public List<DisburseDetail> selectAll() {
        return detailMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(DisburseDetail record) {
        detailMapper.updateByPrimaryKey(record);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        detailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String,Object>> selectGroupBy(DisburseQueryObject qo) {

        return detailMapper.selectGroupBy(qo);
    }
}
