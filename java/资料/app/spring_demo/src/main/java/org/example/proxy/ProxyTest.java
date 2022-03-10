package org.example.proxy;

import org.example.proxy.cglib.MethodAInterceptImpl;
import org.example.proxy.jdk.UserInvocation;
import org.example.proxy.jdk.UserService;
import org.example.proxy.jdk.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/3 15:20
 */
public class ProxyTest {

    public static void main(String[] args) {
//        JdkProxyTest();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ProxyTest.class);
        enhancer.setCallback(new MethodAInterceptImpl());
        ProxyTest proxyTest = (ProxyTest) enhancer.create();
        proxyTest.hello();
    }

    private void JdkProxyTest() {
        UserService userService = new UserServiceImpl();
        UserInvocation userInvocation = new UserInvocation(userService);
        UserService proxy = userInvocation.getProxy();
        proxy.listAll();
    }

    private void hello() {
        System.out.println("hello world!!!");
    }
}
