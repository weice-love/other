package org.example.service;

import org.example.aop.AopEntity;
import org.example.aop.AopEntityV2;
import org.example.aop.Print;
import org.example.aop.PrintV3;
import org.example.context.HelloMessage;
import org.example.context.SensitiveAttributeFilter;
import org.example.context.UserManager;
import org.example.converter.Student;
import org.example.event.TestEvent;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.ClassPathResource;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 11:41
 */
public class TestSpringContextService {


    public static void main(String[] args) {
//        selfPropertySettingTest();
//        msgGettingTest();
//        Teacher teacher = loadBean1("beans.xml", "teacher", Teacher.class);
//        Teacher teacher = loadBean("beans.xml", "teacher", Teacher.class);
//        System.out.println(teacher);
//        localeTest();
//        eventTest();
//        convertTest();
        aopTest1();
//        aopTest2();
//        aopTest3();

    }

    private static void aopTest3() {
        PrintV3 aopEntity = loadBean("aop-beans.xml", "aopEntityV3", PrintV3.class);
        aopEntity.print();
    }

    private static void aopTest2() {
        Print aopEntity = loadBean("aop-beans.xml", "aopEntityV2", Print.class);
        aopEntity.print();
        AopEntityV2 aopEntityV2 = loadBean("aop-beans.xml", "aopEntityV2", AopEntityV2.class);
        aopEntityV2.print();
    }

    private static void aopTest1() {
        AopEntity aopEntity = loadBean("aop-beans.xml", "aopEntity", AopEntity.class);
        aopEntity.print();
    }

    private static void convertTest() {
        ConversionService conversionService = loadBean("converter.xml", "conversionService", ConversionService.class);
        Student convert = conversionService.convert("2022-02-25 11:35:35", Student.class);
        System.out.println(convert);
        Date date = conversionService.convert("2022-02-25 11:35:35", Date.class);
        System.out.println(date);
    }

    private static void eventTest() {
        ApplicationContext applicationContext = loadContext("event.xml");
        TestEvent testEvent = new TestEvent("hello", "msg");
        applicationContext.publishEvent(testEvent);
    }

    private static void localeTest() {
        ApplicationContext applicationContext = loadContext("internation.xml");
        Object[] params = {"John", new GregorianCalendar().getTime()};
        String CHINA = applicationContext.getMessage("test", params, Locale.CHINA);
        String US = applicationContext.getMessage("test", params, Locale.US);
        System.out.println(US);
        System.out.println(CHINA);
    }

    private static void msgGettingTest() {
        HelloMessage helloMessage = loadBean("beans.xml", "message", HelloMessage.class);
        System.out.println(helloMessage.getMsg());
    }

    private static void selfPropertySettingTest() {
        UserManager userManager = loadBean("org/example/context/support/beans.xml", "userManager", UserManager.class);
        System.out.println("date: " + userManager.getDateValue());
    }

    private static <T> T loadBean(String filePath, String beanName, Class<T> clazz) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(filePath);
        return context.getBean(beanName, clazz);
    }

    private static ApplicationContext loadContext(String filePath) {
        return new ClassPathXmlApplicationContext(filePath);
    }
    private static <T> T loadBean1(String filePath, String beanName, Class<T> clazz) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(filePath);
        ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(filePath));
        SensitiveAttributeFilter sensitiveAttributeFilter = beanFactory.getBean("sensitiveAttributeFilter", SensitiveAttributeFilter.class);
        sensitiveAttributeFilter.postProcessBeanFactory(beanFactory);
        return beanFactory.getBean(beanName, clazz);
    }
}
