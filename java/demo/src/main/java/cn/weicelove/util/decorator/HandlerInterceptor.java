package cn.weicelove.util.decorator;


public interface HandlerInterceptor {

    boolean preHandler(String request, String response, Object handler);
}
