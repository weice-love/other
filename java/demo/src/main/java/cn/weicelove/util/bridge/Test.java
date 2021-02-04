package cn.weicelove.util.bridge;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 15:35
 */
public class Test {

    public static void main(String[] args) {
        WxPay wxPay = new WxPay(new FacePayMode());
        System.out.println("12 : " + wxPay.transfer("12"));
        System.out.println("1234 : " + wxPay.transfer("1234"));
    }
}
