package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Producttype;
import cn.wolfcode.crm.mapper.ProducttypeMapper;
import cn.wolfcode.crm.service.IProducttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducttypeServiceImpl implements IProducttypeService{
    @Autowired
    ProducttypeMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Producttype record) {
        return mapper.insert(record);
    }

    @Override
    public Producttype selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Producttype> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Producttype record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Producttype> selectParentClassify() {
        return mapper.selectParentClassify();
    }

    @Override
    public List<Producttype> selectChildClassify(Long id) {
        return mapper.selectChildClassify(id);
    }

    @Override
    public List<Producttype> selectAllTwoType() {
        return mapper.selectAllTwoType();
    }
}
