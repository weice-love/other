package com.junit_demo.app.java8.model.pattern;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/19 10:07
 */
public class Number extends Expr{
    int val;

    public Number(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Number{" +
                "val=" + val +
                '}';
    }
}
