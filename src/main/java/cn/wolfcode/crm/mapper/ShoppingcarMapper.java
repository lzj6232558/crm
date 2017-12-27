package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Shoppingcar;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingcarMapper {
    //查询所有
    List<Shoppingcar> productAll();
    //根据条件查询
    List<Shoppingcar>productGroubByName(Long id);

    void insert(Shoppingcar shopping);

    List<Shoppingcar>selletAll();

    void update(Shoppingcar shoppingcar);

    void delete();

    List<Long> selletAllId();

    BigDecimal selectTocalPrice(Long pId);

    Long selectNumber(Long pId);

    void deleteById(String name);

    BigDecimal selectTocl(Long id);

    void updataSta();

    void getselectmenoy();

    void insertVipNumber(Long id);

    List<Shoppingcar> selectVipNumber();

    List<Shoppingcar> selectAllStatis();

    void updateshoppingstatus();

    void updateshopingByVipNumber(Long vipNumber);

    void insertBlus(Shoppingcar shopping);

    List<Shoppingcar> selectVipProduct(Long vipNumber);

    void deleteselect(Long vipNumber);
}