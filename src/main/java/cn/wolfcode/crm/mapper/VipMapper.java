package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vip record);

    Vip selectByPrimaryKey(Long id);

    List<Vip> selectAll();

    int updateByPrimaryKey(Vip record);

    int queryForCount(QueryObject qo);

    List<Vip> queryForList(QueryObject qo);

    /**
     * 通过会前台传递的员卡号码获取获取当前会员
     * @param vipNumber
     * @return
     */
    Vip selectVipByCardNumber(Long vipNumber);

    /**
     * 在消费过后修改会员的余额
     * @param vip
     */
    void updateRemainingByVipId(Vip vip);

    void updateVipNumber(@Param("vipNumber")Long vipNumber,@Param("id")Long id);

    void changeState(Long id);

    int getVipCardState(Long id);

    /**
     * 根据会员名字获取电话号码进行查询大概会员信息
     * @param nameOrPhone
     * @return 多个会员信息
     */
    List<Vip> selectVipByNameOrPhone(String nameOrPhone);
    Vip checkVipPhone(String  phone);

    void editVipPassword(@Param("password") String newPassword,@Param("id")Long id);

    void initPassword(Long id);

    List<Map<String,Object>> report();

    void clearPoint(Long id);

    void pointRecharge(Vip vip);

    void renew(Vip vip);
}