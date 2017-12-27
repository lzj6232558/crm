package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Shoppingcar;

public interface IShoppingCarService {
    int deleteByPrimaryKey(Long id);
    int insert(Shoppingcar record);
}
