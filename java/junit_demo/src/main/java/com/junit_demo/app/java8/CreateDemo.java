package com.junit_demo.app.java8;


import com.alibaba.fastjson.JSON;
import com.junit_demo.app.java8.model.Apple;
import com.junit_demo.app.java8.model.Position;
import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

/**
 * @author :  清风
 * description :
 * create date :  2021/3/4 17:34
 */
public class CreateDemo {

    @Test
    @DisplayName("predicate使用")
    public void predicateTest() {
        Predicate<Apple> redApple = Apple::getBeRed;
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> and = redApple.and(t -> t.getHeavy() > 150);
    }

    @Test
    @DisplayName("自定义创建实体类")
    public void createPosition() {
        Position position = new Position(1, 1, 1);
        PositionCreate<Integer,Integer, Integer, Position> positionCreate = Position::new;
        Position apply = positionCreate.apply(12, 12, 12);
        PrintTool.print(JSON.toJSONString(apply));
    }

    @FunctionalInterface
    private interface PositionCreate<X,Y,Z,R> {
        R apply(X x, Y y, Z z);
    }
}
