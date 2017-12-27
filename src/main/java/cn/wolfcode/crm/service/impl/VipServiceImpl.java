package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.RechargeRecord;
import cn.wolfcode.crm.domain.Vip;
import cn.wolfcode.crm.domain.Vipcard;
import cn.wolfcode.crm.mapper.RechargeRecordMapper;
import cn.wolfcode.crm.mapper.VipMapper;
import cn.wolfcode.crm.mapper.VipcardMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IVipService;
import cn.wolfcode.crm.util.CheckPhone;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("all")
public class VipServiceImpl implements IVipService{
    @Autowired
    VipMapper mapper;
    @Autowired
    VipcardMapper vipcardMapper;
    @Autowired
    RechargeRecordMapper rechargeRecordMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Vip record) {
        record.setInputTime(new Date());
        if(record.getAmountMoney() == null){
            record.setCurrentMoney(BigDecimal.ZERO);
            record.setCurrentpoint(0L);
            record.setTotalpoint(0L);
            record.setAmountMoney(BigDecimal.ZERO);
        }else {
            record.setCurrentMoney(record.getAmountMoney());
            record.setCurrentpoint(new Long(record.getAmountMoney().toString()));
            record.setTotalpoint(new Long(record.getAmountMoney().toString()));
        }
        Vipcard vipcard = vipcardMapper.selectCardByPoint(new Long(record.getAmountMoney().toString()));
        System.out.println(vipcard.getId());
        record.setVipcard(vipcard);

        if(record.getEmployee() == null){
            Employee employee= (Employee) SecurityUtils.getSubject().getPrincipal();
            record.setEmployee(employee);
        }

        int insert = mapper.insert(record);
        mapper.updateVipNumber(record.getId()+1000L,record.getId());
        //首次开卡,生成充值记录
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setVipNumber(record.getVipNumber());
        rechargeRecord.setVipName(record.getVipName());
        rechargeRecord.setCurrentInMoney(record.getCurrentMoney());
        rechargeRecord.setVipAmountMoney(record.getAmountMoney());
        rechargeRecord.setInputUser(record.getEmployee());
        rechargeRecord.setPostscript("首次充值");
        rechargeRecordMapper.insert(rechargeRecord);
        return insert;
    }

    @Override
    public Vip selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Vip> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Vip record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = mapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, mapper.queryForList(qo));
    }

    @Override
    public void changeState(Long id) {
        int vipCardState = mapper.getVipCardState(id);
        if(vipCardState == Vip.STATE_NORMAL || vipCardState ==Vip.STATE_LOSS){
            mapper.changeState(id);
        }else if(vipCardState == Vip.STATE_OVER){
            throw new RuntimeException("会员卡已停用");
        }
    }

    @Override
    public void checkVipPhone(String number) {
        if(!CheckPhone.checkCellphone(number)){
            throw new RuntimeException("请输入正确的格式");
        }
        Vip vip = mapper.checkVipPhone(number);
        if(vip != null){
            throw new RuntimeException("号码已经注册,请更换号码");
        }
    }

    @Override
    public void clearPoint(Long id) {
        mapper.clearPoint(id);
    }

    @Override
    public void pointRecharge(Vip vip) {
        mapper.pointRecharge(vip);
    }

    @Override
    public void renew(Vip vip) {
        mapper.renew(vip);
    }


    @Override
    public void editVipPassword(String oldPassword, String newPassword, Long id) {
        checkVipPassword(oldPassword,id);
        mapper.editVipPassword(newPassword,id);
    }

    @Override
    public void initPassword(Long id) {
        Vip vip = mapper.selectByPrimaryKey(id);
        if(vip == null){
            throw new RuntimeException("该会员不存在");
        }else if(vip.getVipCardState() ==Vip.STATE_OVER){
            throw new RuntimeException("该会员会员卡已经停用");
        }
        mapper.initPassword(id);
    }

    public void checkVipPassword(String password,Long id){
        Vip vip = mapper.selectByPrimaryKey(id);
        if(vip == null){
            throw new RuntimeException("该会员不存在");
        }
        if(!vip.getVipPassword().equals(password)){
            throw new RuntimeException("密码输入错误");
        }
    }

    @Override
    public List<Map<String, Object>> report() {
        return mapper.report();
    }


    @Override
    public List<Vip> selectVipByNameOrPhone(String nameOrPhone) {
        return mapper.selectVipByNameOrPhone(nameOrPhone);
    }
}
