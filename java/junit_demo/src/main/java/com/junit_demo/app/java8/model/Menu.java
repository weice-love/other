package com.junit_demo.app.java8.model;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> @author     :  清风
 * <p> description :  菜单
 * <p> create date :  2021/9/23 16:54
 */
public class Menu {

    private String name;

    private Integer type;

    private Integer calories;

    public Menu(String name, Integer type, Integer calories) {
        this.name = name;
        this.type = type;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getCalories() {
        return calories;
    }

    public enum CaloriesEnum {
        DIET,NORMAL,FAT;
    }

    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("鱼香肉丝", 0, 170));
        menus.add(new Menu("西红柿炒蛋", 0, 120));
        menus.add(new Menu("酸菜鱼", 1, 700));
        menus.add(new Menu("干锅茄子", 0, 150));
        menus.add(new Menu("炒螺蛳", 1, 200));
        System.out.println(JSON.toJSONString(menus));
    }
}
