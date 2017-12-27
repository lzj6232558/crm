package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

//支出小分类
@Getter
@Setter
public class DisburseLittleClassify {
    private Long id;
    //分类类型
    private String Classify;
    //父分类
    private DisburseBigClassify bigClassify;
}