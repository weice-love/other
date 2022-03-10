package org.example.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/3 15:21
 */
public class UserInvocation implements InvocationHandler {

    private UserService target;

    public UserInvocation(UserService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk代理开始");
        return method.invoke(target, args);
    }

    public UserService getProxy() {
        return (UserService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
