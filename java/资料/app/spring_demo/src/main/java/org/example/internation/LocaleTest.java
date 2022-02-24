package org.example.internation;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/24 15:09
 */
public class LocaleTest {

    public static void main(String[] args) {
        Locale zh = new Locale("zh", "CN");
        Locale china = Locale.CHINA;
        Locale zh1 = new Locale("zh");
        Locale chinese = Locale.CHINESE;
        String p1 = "{0}，您好！您于{1}在工商银行存入{2}元。";
        String p2 = "At {1,time,short}, On{1,date,long} {0} paid {2,number,currency}.";

        Object[] params = {"John",new GregorianCalendar().getTime(),1.0E3};
        System.out.println(MessageFormat.format(p1, params));
        System.out.println(MessageFormat.format(p2, params));
    }
}
