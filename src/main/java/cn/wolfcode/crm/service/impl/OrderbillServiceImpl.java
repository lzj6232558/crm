package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Orderbill;
import cn.wolfcode.crm.mapper.OrderbillMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IOrderbillService;
import cn.wolfcode.crm.service.IProductStockService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderbillServiceImpl implements IOrderbillService{

    @Autowired
    private OrderbillMapper orderbillMapper;

    @Autowired
    private IProductStockService productStockService;


    @Override
    public void deleteByPrimaryKey(Long id) {
        orderbillMapper.deleteRelation(id);
        orderbillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Orderbill bill) {
        //设置单据中的其他信息
        //设置单据中的其他信息
        bill.setInputuser((Employee) SecurityUtils.getSubject().getPrincipal());
        bill.setInputtime(new Date());
        bill.setTotalamount(bill.getTotalnumber().multiply(bill.getTotalnumber()));
        orderbillMapper.insert(bill);

        /*if(pid != null) {
            for(Long productId : pid) {
                orderbillMapper.insertRelation(bill.getId(),productId);
            }
        }*/

    }

    @Override
    public Orderbill selectByPrimaryKey(Long id) {
        return orderbillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Orderbill> selectAll() {
        return orderbillMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Orderbill bill) {

        /*if(pid != null) {
            for(Long productId : pid) {
                orderbillMapper.insertRelation(bill.getId(),productId);
            }
        }*/
        orderbillMapper.updateByPrimaryKey(bill);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = orderbillMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, orderbillMapper.queryList(qo));
    }

    @Override
    public void audit(Long id) {
        Orderbill old = orderbillMapper.selectByPrimaryKey(id);
        old.setAuditor((Employee) SecurityUtils.getSubject().getPrincipal());
        old.setAudittime(new Date());
        orderbillMapper.audit(old);

        //添加库存
        productStockService.stockIncome(old);
    }

    @Override
    public PageResult queryReturnbill(QueryObject qo) {
        int count = orderbillMapper.queryReturnbillCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, orderbillMapper.queryReturnbillList(qo));
    }


    /**
     * 订单退货
     * @param id
     */
    @Override
    public void returnbill(Long id) {
        Orderbill old = orderbillMapper.selectByPrimaryKey(id);
        productStockService.stockOutcome(old);
        orderbillMapper.deleteByPrimaryKey(id);

    }

}
