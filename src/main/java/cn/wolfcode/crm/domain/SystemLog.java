package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SystemLog {
    private Long id;
    //操作用户  必填
    private Employee opUser;
    //操作时间  必填 可以在sql里直接写
    private Date opTime;
    //登录IP
    private String opIp;
    //使用功能
    private String function;
    //操作参数信息
    private String params;
}