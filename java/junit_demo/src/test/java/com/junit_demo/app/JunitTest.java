package com.junit_demo.app;

import com.junit_demo.app.annotion.ExcelSource;
import com.junit_demo.app.annotion.FileSource;
import com.junit_demo.app.model.Student;
import com.junit_demo.app.model.UserSyncVO;
import com.junit_demo.app.util.PrintTool;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/14 11:08
 */
public class JunitTest {

    @BeforeEach
    @DisplayName("测试准备")
    public void start() {
        System.out.println("start test!!!");
    }

    @Test
    @DisplayName("异常测试(断言)")
    public void exceptionTest() {
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> System.out.println(1 / 0));
    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("ValueSource参数化测试")
    public void parameterizedTest1(String string) {
        System.out.println("para: " + string);
        assertTrue(StringUtils.isNotBlank(string));
    }

    @ParameterizedTest
    // 内置的文件读入
    @CsvFileSource(resources = "/test.csv")
    @DisplayName("参数化测试-csv文件")
    public void parameterizedTest2(String name, Integer age) {
        System.out.println("name:" + name + ",age:" + age);
        Assertions.assertNotNull(name);
        Assertions.assertNotNull(age);
    }

    /**
     * @author     : 清风
     * <p>description :
     * <p>create time : 16:48 2021/9/14
     *
     * @param student 入参
     * @see com.junit_demo.app.provider.JsonFileArgumentProvider
     * @see FileSource
     */
    @ParameterizedTest
    // 自定义文件读入
    @FileSource(resource = "file.json")
    @DisplayName("参数化测试-json文件(读入实体类)")
    public void parameterizedTest4(Student student) {
        System.out.println("student name:" + student.getName());
    }


    @ParameterizedTest
    // 自定义文件读入
    @ExcelSource(resource = "C:\\Users\\EDZ\\Desktop\\异常\\优惠券同步\\mq.xlsx", clazz = UserSyncVO.class)
    @DisplayName("参数化测试-excel文件(读入实体类)")
    public void parameterizedTest4(List<UserSyncVO> userSyncVOS) {
        HashMap<String, String> reduce = userSyncVOS.stream()
                .reduce(new HashMap<>(), (a, b) -> {
                    a.put(b.getMsgId(), new String(Base64.decodeBase64(b.getBody()), StandardCharsets.UTF_8));
                    return a;
                }, (a, b) -> {
                    HashMap<String, String> result = new HashMap<>();
                    for (Map.Entry<String, String> stringStringEntry : a.entrySet()) {
                        result.put(stringStringEntry.getKey(), stringStringEntry.getValue());
                    }
                    for (Map.Entry<String, String> stringStringEntry : b.entrySet()) {
                        result.put(stringStringEntry.getKey(), stringStringEntry.getValue());
                    }
                    return result;
                });
        for (Map.Entry<String, String> stringStringEntry : reduce.entrySet()) {
            System.out.println("==============================================");
            System.out.println("messageId: " + stringStringEntry.getKey());
            System.out.println("=>");
            System.out.println(stringStringEntry.getValue());
        }
        System.out.println("size:" + reduce.size());
    }

    // 2: 重复次数
    @RepeatedTest(2)
    @DisplayName("重复测试")
    public void testRepeated() {
        Random random = new Random();
        int i = random.nextInt(100);
        PrintTool.print(i);
//        assertTrue(i < 50);
    }

    static Stream<String> source() {
        return Stream.of("1", "2", "3");
    }

    @ParameterizedTest
    // 方法名
    @MethodSource("source")
    @DisplayName("方法来源参数(stream)")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println("source: " + name);
        Assertions.assertNotNull(name);
    }

    static IntStream intSource() {
        return IntStream.rangeClosed(1, 100);
    }

    @ParameterizedTest
    // 方法名
    @MethodSource("intSource")
    @DisplayName("方法来源参数(stream)")
    public void testWithExplicitLocalMethodIntSource(int source) {
        System.out.println("source: " + source);
    }


    @TestFactory
    @DisplayName("动态测试")
    Iterator<DynamicTest> dynamicTests() {
        return Arrays.asList(
                dynamicTest("第一个动态测试", () -> assertTrue(true)),
                dynamicTest("第二个动态测试", () -> assertEquals(4, 2 * 2))
        ).iterator();
    }

//    @Test
//    @DisplayName("抛出异常测试")
//    public void exceptionStart() {
//        int a = 1 /0;
//    }
//
//    @Test
//    @Disabled
//    @DisplayName("抛出空指针异常测试")
//    public void nullExceptionStart() {
//        throw new NullPointerException();
//    }
}
/*
@Test :表示方法是测试方法。职责单一,不能声明属性, 由Jupiter提供额外测试

@ParameterizedTest :表示方法是参数化测试

@RepeatedTest :表示方法可重复执行

@DisplayName :为测试类或者测试方法设置展示名称

@BeforeEach :表示在每个单元测试之前执行

@AfterEach :表示在每个单元测试之后执行

@BeforeAll :表示在所有单元测试之前执行

@AfterAll :表示在所有单元测试之后执行

@Tag :表示单元测试类别，类似于JUnit4中的@Categories

@Disabled :表示测试类或测试方法不执行，类似于JUnit4中的@Ignore

@Timeout :表示测试方法运行如果超过了指定时间将会返回错误

@ExtendWith :为测试类或测试方法提供扩展类引用
 */
