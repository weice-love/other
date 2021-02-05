package cn.weicelove.util.decorator;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/5 14:21
 */
public abstract class SsoDecorator implements HandlerInterceptor{

    private HandlerInterceptor handlerInterceptor;

    public SsoDecorator() {
    }

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandler(String request, String response, Object handler) {
        return this.handlerInterceptor.preHandler(request, response, handler);
    }
}
