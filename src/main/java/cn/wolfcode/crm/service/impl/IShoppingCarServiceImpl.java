package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Shoppingcar;
import cn.wolfcode.crm.mapper.ShoppingcarMapper;
import cn.wolfcode.crm.service.IShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IShoppingCarServiceImpl implements IShoppingCarService {

    @Autowired
    private ShoppingcarMapper mapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Shoppingcar record) {
        //得到所有购物车商品
        List<Shoppingcar> shoppingcars = mapper.selletAll();
        //判断是否重复
       for (Shoppingcar shoppingcar : shoppingcars) {
            if ( shoppingcar.getPId()!=record.getPId()) {
                mapper.insert(record);
            }
        }
        return 0;
    }

}
