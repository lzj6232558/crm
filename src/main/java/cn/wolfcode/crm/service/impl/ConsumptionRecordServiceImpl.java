package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.*;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IConsumptionRecordService;
import cn.wolfcode.crm.util.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ConsumptionRecordServiceImpl implements IConsumptionRecordService {
    @Autowired
    ConsumptionRecordMapper mapper;

    @Autowired
    VipMapper vipMapper;

    @Autowired
    private ShoppingcarMapper shoppingcarMapper;

    @Autowired
    private ConsumptionDetailMapper consumptionDetailMapper;

    @Autowired
    private ProductstockMapper productstockMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    SaleAccountMapper saleAccountMapper;

    @Autowired
    SellStatementMapper sellStatementMapper;

    @Override  //接收会员id
    public JsonResult insert(Long vipNumber) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        // 1.看商品库存够不够，通过购物车里面的库存数量判断
        // 2.判断当前用户金额够不够
        // 返回jsonresult
            //会员信息
            Vip vip = vipMapper.selectVipByCardNumber(vipNumber);

            if (vip == null) {
                return new JsonResult(false, "该卡号不存在");
            } else if (vip.getVipCardState() == 0) {
                return new JsonResult(false, "该卡号已经挂失");
            } else if (vip.getVipCardState() == -1) {
                return new JsonResult(false, "该卡号已经停用");
            }

            //获取总金额
            BigDecimal bigDecimal = shoppingcarMapper.selectTocl(vip.getVipNumber());

            List<Shoppingcar> shoppingcars = shoppingcarMapper.selletAll();
            for (Shoppingcar shoppingcar : shoppingcars) {
                //库存
                Long number = shoppingcar.getNumber();
                //购买数量
                Long shoppingnumber = shoppingcar.getShoppingnumber();
                if (number < shoppingnumber) {
                    return new JsonResult(false, "库存不足");
                }
                //=============生成报表
                Product product = productMapper.selectByPrimaryKey(shoppingcar.getPId());
                SaleAccount saleAccount = new SaleAccount();
                //数量
                BigDecimal productNumber = new BigDecimal(shoppingcar.getNumber());
                saleAccount.setNumber(productNumber);
                //成本价  成本总计,
                BigDecimal costprice = product.getCostprice();
                saleAccount.setCostprice(costprice);
                saleAccount.setCostamount(costprice.multiply(productNumber));
                //销售价  销售总计
                BigDecimal saleprice = product.getSaleprice();
                saleAccount.setSaleprice(saleprice);
                saleAccount.setSaleamount(saleprice.multiply(productNumber));
                //商品
                saleAccount.setProduct(product);
                //业务员
                saleAccount.setSaleman(employee);
                //客户
                saleAccount.setClient(vip);
                saleAccountMapper.insert(saleAccount);
            }

            if (vip.getCurrentMoney().compareTo(bigDecimal) == -1) {
                return new JsonResult(false, "余额不足");
            }
            //减数量
            vip.setCurrentMoney(vip.getCurrentMoney().subtract(bigDecimal));
            vipMapper.updateRemainingByVipId(vip);

            //消费单
            ConsumptionRecord consumptionRecord = new ConsumptionRecord();
            consumptionRecord.setVipname(vip.getVipName());
            consumptionRecord.setVipnumber(vipNumber);
            consumptionRecord.setAmountmoney(bigDecimal);
            consumptionRecord.setInputtime(new Date());
            //少一个操作人
            mapper.insert(consumptionRecord);

            //消费明细
            for (Shoppingcar shoppingcar : shoppingcars) {

                //添加消费明细
                ConsumptionDetail consumptionDetail = new ConsumptionDetail();
                Product product = new Product();
                //商品对象
                product.setId(shoppingcar.getPId());
                consumptionDetail.setNumber(new BigDecimal(shoppingcar.getShoppingnumber()));
                product.setName(shoppingcar.getName());
                consumptionDetail.setProduct(product);
                //仓库成本价
                Productstock productstock = productstockMapper.selectStockByPidAndDid(shoppingcar.getDepotId(), shoppingcar.getPId());
                consumptionDetail.setCostprice(productstock.getPrice());
                //售价
                consumptionDetail.setSaleprice(shoppingcar.getPrice());
                //仓库id
                consumptionDetail.setDepotId(shoppingcar.getDepotId());
                //总金额
                consumptionDetail.setAmountmoney(bigDecimal);
                //消费的id
                consumptionDetail.setRecordId(consumptionRecord.getId());
                consumptionDetailMapper.insert(consumptionDetail);
            }
        return new JsonResult("消费成功");
    }

    @Override
    public ConsumptionRecord selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ConsumptionRecord> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = mapper.queryForCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, mapper.queryForList(qo));
    }

}
