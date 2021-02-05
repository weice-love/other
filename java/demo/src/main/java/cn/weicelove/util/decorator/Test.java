package cn.weicelove.util.decorator;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/5 14:26
 */
public class Test {

    public static void main(String[] args) {
        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        System.out.println("result: " + loginSsoDecorator.preHandler("success", "", null));
        System.out.println("result: " + loginSsoDecorator.preHandler("false", "", null));
    }
}
