package cn.wolfcode.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class VipQueryObject extends QueryObject{
    private Long employeeId;
    //来源
    private String source;
    //卡号
    private Long vipcardId;
    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GTM-8")
    private Date beginTime;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GTM-8")
    private Date endTime;

    //按时间的格式查询(年/月/日)
    private String timeFormat;
    //具体的数值
    private Integer formatNum;

    public Date getEndTime(){
        if(endTime != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return null;
    }
}
