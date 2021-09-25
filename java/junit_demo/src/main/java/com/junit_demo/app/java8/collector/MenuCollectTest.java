package com.junit_demo.app.java8.collector;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.annotion.ListSource;
import com.junit_demo.app.java8.model.Menu;
import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.List;

public class MenuCollectTest {

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("使用自定义收集器收集菜单")
    public void menuCollect(List<Menu> menus) {
        List<Menu> collect = menus.stream().collect(ArrayList::new, List::add, List::addAll);
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("使用自定义收集器收集菜单")
    public void menuCollect2(List<Menu> menus) {
        List<Menu> collect = menus.stream().collect(new ToListCollector<>());
        PrintTool.print(JSON.toJSONString(collect));
    }

}
