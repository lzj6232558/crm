package cn.wolfcode.crm.domain;

import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gift {
    private Long id;

    private String sn;

    private String imagepath;

    private String name;

    private Long point;//积分

    private Long currentNum;//当前数量

    private Long totalNum;//总数量

    private String remark;//备注

    public String getSmallImagePath() {
        if (StringUtils.isEmpty(imagepath)) {
            return "";
        } else {
            //xxx_small.jpg
            int index = imagepath.lastIndexOf(".");
            return imagepath.substring(0, index) + "_small" + imagepath.substring(index);
        }
    }
}