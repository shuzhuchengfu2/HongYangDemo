package com.chuxin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * author： xiongdejin
 * date: 2017/9/18
 * describe:
 */

public class F {
    public static String formatMoneyForCxLine(String money){
        try{
            money = FormatMoneyWithout(money);
            if(F.checkEmpty(money)){
                if(money.indexOf(".") > 0){
                    //正则表达
                    money = money.replaceAll("0+?$", "");//去掉后面无用的零
                    money = money.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
                    return money;
                }
            }
            return "0";
        }catch (Exception e){
            return "0";
        }
    }

    /**
     * 格式化金额，添加千分符，小数点后保留两位(没有，号)
     *
     * @param money
     * @return
     */
    public static String FormatMoneyWithout(String money){
        if (money==null ||money.length() == 0 || "null".equals(money)) {
            return "0.00";
        }
        String str_money = "";
        try {
            money = money.replace(",", "");
            BigDecimal bd = new BigDecimal(money);
            DecimalFormat myformat = new DecimalFormat();
            myformat.setRoundingMode(RoundingMode.DOWN);
            myformat.applyPattern("##0.00");
            str_money = myformat.format(bd);
        } catch (Exception e) {
            str_money = money;
        }
        return str_money;
    }

    /**
     *
     * @param str
     * @return false 空
     */
    public static boolean checkEmpty(String str) {
        if (str == null || str.equals("null") || str.length() == 0) return false;
        return true;
    }
}
