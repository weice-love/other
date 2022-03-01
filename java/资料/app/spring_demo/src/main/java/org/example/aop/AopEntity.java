package org.example.aop;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/25 15:06
 */

public class AopEntity {

    private String val = "testStr";

    public void print() {
        System.out.println(this.val);
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
