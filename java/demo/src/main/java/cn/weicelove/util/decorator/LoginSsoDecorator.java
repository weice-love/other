package cn.weicelove.util.decorator;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/5 14:22
 */
public class LoginSsoDecorator extends SsoDecorator{

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandler(String request, String response, Object handler) {
        boolean success = super.preHandler(request, response, handler);
        if (!success) {
            System.out.println("error");
        }
        return "success".equals(request);
    }
}
