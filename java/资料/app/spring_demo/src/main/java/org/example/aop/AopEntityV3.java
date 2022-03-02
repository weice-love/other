package org.example.aop;

import org.springframework.aop.framework.AopContext;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/2 11:18
 */
public class AopEntityV3 implements PrintV3{

    private String val = "testStr";

    @Override
    public void print() {
        System.out.println("print aop test3: " + this.val);
//        this.print1();
        ((PrintV3) AopContext.currentProxy()).print1();
    }

    @Override
    public void print1() {
        System.out.println("print1 aop test3: " + this.val);
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
