package cn.weicelove.util.decorator;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/5 14:20
 */
public class SsoInterceptor  implements HandlerInterceptor{

    @Override
    public boolean preHandler(String request, String response, Object handler) {
        return true;
    }
}
