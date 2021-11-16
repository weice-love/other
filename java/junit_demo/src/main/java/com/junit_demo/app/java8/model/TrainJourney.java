package com.junit_demo.app.java8.model;

/**
 * <p> @author     :  清风
 * <p> description :  旅行实体类
 * <p> create date :  2021/11/16 14:39
 */
public class TrainJourney {

    // todo
    private String fname;
    private String Ename;
    // 价格
    public int price;
    // 下一个旅行地
    public TrainJourney onward;
    public TrainJourney(int p, TrainJourney trainJourney) {
        this.price = p;
        this.onward = trainJourney;
    }

    @Override
    public String toString() {
        return "该旅程费用: " + getAllPrice(this) + "（元）.";
    }

    private int getAllPrice(TrainJourney a) {
        if (a.onward == null) {
            return a.price;
        }
        return a.price + getAllPrice(a.onward);
    }
}
