package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Supplier;
import cn.wolfcode.crm.mapper.SupplierMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService{

    @Autowired
    private SupplierMapper supplierMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        supplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Supplier record) {
        supplierMapper.insert(record);
    }

    @Override
    public Supplier selectByPrimaryKey(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Supplier> selectAll() {
        return supplierMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Supplier record) {
        supplierMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = supplierMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, supplierMapper.queryList(qo));
    }


}
