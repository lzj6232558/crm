package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//支出大分类
@Getter
@Setter
public class DisburseBigClassify {
    private Long id;
    //分类 类型
    private String classify;
    //所有子分类
    private List<DisburseLittleClassify> littleList = new ArrayList<>();
}