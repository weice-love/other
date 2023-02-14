package org.example.service;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/15 14:59
 */
public class Test {

    static {
        System.out.println("static");
    }
    public static Test s = new Test();

    public static Test[] test = new Test[1];

    public static int a= 100;

    public Test() {
        System.out.println("构造器初始化");
    }

    //    static {
//        System.out.println("初始化");
//    }

    public static void main(String[] args) {
        double a = Math.ceil(4)/2 -1;
        System.out.println(a);
    }
}
