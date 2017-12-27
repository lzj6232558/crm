package cn.wolfcode.crm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JsonResult {

    private boolean success = true;
    private String msg;

    public JsonResult(String msg){

        this.msg=msg;
    }
}
