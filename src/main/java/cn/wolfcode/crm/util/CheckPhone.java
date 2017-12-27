package cn.wolfcode.crm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhone {
    private CheckPhone(){}

    public static boolean checkCellphone(String cellphone) {
        String phone = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher(cellphone);
        boolean b = matcher.find();
        System.out.println(b);
        return b;
    }

    public static boolean checkTelephone(String telephone) {
        String tel = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        Pattern pattern = Pattern.compile(tel);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.find();
    }
}
