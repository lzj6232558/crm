package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface IVipService {
    int deleteByPrimaryKey(Long id);

    int insert(Vip record);

    Vip selectByPrimaryKey(Long id);

    List<Vip> selectAll();

    int updateByPrimaryKey(Vip record);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    /**
     * 根据会员名字获取电话号码进行查询大概会员信息
     * @param nameOrPhone
     * @return 多个会员信息
     */
    List<Vip> selectVipByNameOrPhone(String nameOrPhone);


    void clearPoint(Long id);

    void pointRecharge(Vip vip);

    void renew(Vip vip);
    void checkVipPhone(String  number);

    void editVipPassword(String oldPassword, String newPassword, Long id);

    void initPassword(Long id);

    public void checkVipPassword(String password,Long id);

    List<Map<String,Object>> report();
}
