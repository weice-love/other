package com.junit_demo.app.java8.group;

import com.alibaba.fastjson.JSON;
import com.junit_demo.app.annotion.ListSource;
import com.junit_demo.app.java8.model.Menu;
import com.junit_demo.app.util.PrintTool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p> @author     :  清风
 * <p> description :  分组操作
 * <p> create date :  2021/9/23 16:31
 */
public class GroupTest {

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按类型分组")
    public void groupByTypeTest(List<Menu> menus) {
        Map<Integer, List<Menu>> collect = menus.stream().collect(Collectors.groupingBy(Menu::getType));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按热量分组")
    public void groupByCaloriesTest(List<Menu> menus) {
        Map<Menu.CaloriesEnum, List<Menu>> collect = menus.stream().collect(Collectors.groupingBy(menu -> {
            if (menu.getCalories() >= 500) return Menu.CaloriesEnum.FAT;
            if (menu.getCalories() >= 200) return Menu.CaloriesEnum.NORMAL;
            return Menu.CaloriesEnum.DIET;
        }));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按类型先分组，再按热量分组")
    public void groupByTypeAndCaloriesTest(List<Menu> menus) {
        Map<Integer, Map<Menu.CaloriesEnum, List<Menu>>> collect = menus.stream().collect(Collectors.groupingBy(Menu::getType, Collectors.groupingBy(menu -> {
            if (menu.getCalories() >= 500) return Menu.CaloriesEnum.FAT;
            if (menu.getCalories() >= 200) return Menu.CaloriesEnum.NORMAL;
            return Menu.CaloriesEnum.DIET;
        })));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按子组收集数据")
    public void groupBySelfTest(List<Menu> menus) {
        Map<Integer, Long> collect = menus.stream().collect(Collectors.groupingBy(Menu::getType, Collectors.counting()));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按子组收集数据2")
    public void groupBySelfTest2(List<Menu> menus) {
        Map<Integer, Optional<Menu>> collect = menus.stream().collect(Collectors.groupingBy(Menu::getType,
                Collectors.maxBy(Comparator.comparing(Menu::getCalories))));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按子组收集数据2-optional转换")
    public void groupBySelfAndTransferTest2(List<Menu> menus) {
        Map<Integer, Menu> collect = menus.stream().collect(Collectors.groupingBy(Menu::getType,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Menu::getCalories)), Optional::get)
        ));
        PrintTool.print(JSON.toJSONString(collect));
    }

    @ParameterizedTest
    @ListSource(resource = "menu.json", clazz = Menu.class)
    @DisplayName("按子组收集数据2-mapping转换")
    public void groupBySelfAndMappingTest2(List<Menu> menus) {
        Map<Integer, Set<Menu.CaloriesEnum>> collect = menus.stream().collect(Collectors.groupingBy(Menu::getType,
                Collectors.mapping(menu -> {
                    if (menu.getCalories() >= 500) return Menu.CaloriesEnum.FAT;
                    if (menu.getCalories() >= 200) return Menu.CaloriesEnum.NORMAL;
                    return Menu.CaloriesEnum.DIET;
                }, Collectors.toSet())
        ));
        PrintTool.print(JSON.toJSONString(collect));
    }
}
