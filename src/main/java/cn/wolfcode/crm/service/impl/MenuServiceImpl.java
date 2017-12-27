package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.mapper.MenuMapper;
import cn.wolfcode.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Menu record) {
        return mapper.insert(record);
    }

    @Override
    public Menu selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Menu> getRootMenu() {
        return mapper.getRootMenu();
    }

    @Override
    public List<Menu> selectByPrentIdKey(Long prentid) {
        return  mapper.selectByPrentIdKey( prentid);
    }


}
