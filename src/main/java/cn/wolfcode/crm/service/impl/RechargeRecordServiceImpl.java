package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.RechargeRecord;
import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.domain.Vipcard;
import cn.wolfcode.crm.mapper.RechargeRecordMapper;
import cn.wolfcode.crm.mapper.VipMapper;
import cn.wolfcode.crm.mapper.VipcardMapper;
import cn.wolfcode.crm.service.IRechargeRecordService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@SuppressWarnings("all")
public class RechargeRecordServiceImpl implements IRechargeRecordService {
    @Autowired
    RechargeRecordMapper mapper;
    @Autowired
    VipMapper vipMapper;
    @Autowired
    VipcardMapper vipcardMapper;

    @Override
    public void insert(RechargeRecord record) {
        Vip vip = vipMapper.selectVipByCardNumber(record.getVipNumber());
        //如果会员不存在 抛出异常
        if (vip == null) {
            throw new RuntimeException("该会员不存在");
        } else if (vip.getVipCardState() == 0) {
            throw new RuntimeException("该卡号已经挂失");
        } else if (vip.getVipCardState() == -1) {
            throw new RuntimeException("该卡号已经停用");
        } else if (record.getCurrentInMoney() == null || record.getCurrentInMoney() == new BigDecimal("0")) {
            //如果当前充值金额为0 抛出异常
            throw new RuntimeException("充值金额为空");
        }
        //更改会员的当前金额 和 总充值金额
        BigDecimal currentMoney = record.getCurrentInMoney();//本次充值的金额
        BigDecimal vipCurrentMoney = vip.getCurrentMoney();//会员先有余额
        BigDecimal vipAmountMoney = vip.getAmountMoney();
        if (vipCurrentMoney == null) {
            vip.setCurrentMoney(currentMoney);
        } else {
            vip.setCurrentMoney(vipCurrentMoney.add(currentMoney));//设置当前金额
        }
        if (vipAmountMoney == null) {
            vip.setAmountMoney(currentMoney);
        } else {
            vip.setAmountMoney(vipAmountMoney.add(currentMoney));//设置总金额
        }
        //设置当前的总积分
        vip.setTotalpoint(new Long(vip.getAmountMoney().toString()));

        Long currentpoint = vip.getCurrentpoint();//获取当前积分
        currentpoint = currentpoint + new Long(currentMoney.toString());

        vip.setCurrentpoint(currentpoint);//设置当前积分


        //如果总积分达到某一种状态改变会员卡
        Vipcard vipcard = vipcardMapper.selectCardByPoint(vip.getTotalpoint());
        vip.setVipcard(vipcard);
        //修改会员充值的信息
        vipMapper.updateRemainingByVipId(vip);
        //充值明细表里添加充值明细
        //操作人
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        record.setInputUser(employee);
        //会员总充值金额  如果是第一次充值那就用当前的充值金额 当做总金额
        if (vip.getAmountMoney() == null) {
            record.setVipAmountMoney(currentMoney);
        }
        record.setVipCurrentMoney(vip.getCurrentMoney());
        record.setVipAmountMoney(vip.getAmountMoney());
        //会员编号
        record.setVipNumber(new Long(vip.getVipNumber()));
        //会员名字
        record.setVipName(vip.getVipName());
        //插入这次充值记录
        mapper.insert(record);
    }

    @Override
    public RechargeRecord selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RechargeRecord> selectAll() {
        return mapper.selectAll();
    }

    //退卡操作
    @Override
    public void refundVip(RechargeRecord record) {
        //获取当前会员
        Vip vip = vipMapper.selectVipByCardNumber(record.getVipNumber());

        if(vip == null){
            throw new RuntimeException("当前会员不存在");
        }else if(vip.getVipCardState() == Vip.STATE_OVER){
            throw new RuntimeException("当前会员的会员卡已经停用");
        }
        BigDecimal currentMoney = vip.getCurrentMoney();
        //清空当前卡的当前金额,当前积分
        vip.setCurrentMoney(BigDecimal.ZERO);
        vip.setAmountMoney(BigDecimal.ZERO);
        vip.setCurrentpoint(0L);
        vip.setVipCardState(Vip.STATE_OVER);
        vip.setCurrentpoint(0L);
        vip.setTotalpoint(0L);
        vip.getVipcard().setId(7L);
        vipMapper.updateRemainingByVipId(vip);

        record.setVipNumber(vip.getVipNumber());
        record.setVipName(vip.getVipName());
        //这次的新增金额为 负的会员当前余额
        record.setCurrentInMoney(currentMoney.negate());
        //设置更改用户
        record.setInputUser((Employee) SecurityUtils.getSubject().getPrincipal());
        //当前的会员的总金额的改为0
        record.setVipAmountMoney(BigDecimal.ZERO);
        //当前的会员的余额的改为0
        record.setVipCurrentMoney(BigDecimal.ZERO);
        mapper.insert(record);
    }

    @Override
    public List<RechargeRecord> selectRechargeRecordByVipNumber(Long number) {
        return mapper.selectRechargeRecordByVipNumber(number);
    }
}
