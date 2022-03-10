package org.example.proxy.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/3 15:33
 */
public class MethodAInterceptImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before: " + method);
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("after: " + method);
        return o1;
    }
}
