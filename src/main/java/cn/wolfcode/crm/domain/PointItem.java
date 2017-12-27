package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Charles on 2017/12/22.
 */

/**
 * 积分明细类,本类的mapper层没有更新方法,只能查询和保存
 * 会员所有的积分的增减都会记录并保存到数据库
 */
@Getter
@Setter
public class PointItem {
    private Long id;
    private Vip vip;
    private Vipcard card;
    private Employee inputUser;
    private int op;//操作类型  1表示积分充值  0表示积分扣除
    private Long changeNum;//变动金额
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GTM-8")
    private Date opTime;//操作时间
    private String remark;//备注
}
