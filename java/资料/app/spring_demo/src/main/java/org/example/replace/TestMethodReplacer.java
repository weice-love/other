package org.example.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 16:51
 */
public class TestMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("方法已被替换");
        return null;
    }
}
