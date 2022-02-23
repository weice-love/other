package org.example.service;

import org.example.context.HelloMessage;
import org.example.context.SensitiveAttributeFilter;
import org.example.context.Teacher;
import org.example.context.UserManager;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
        Teacher teacher = loadBean("beans.xml", "teacher", Teacher.class);
        System.out.println(teacher);
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

    private static <T> T loadBean1(String filePath, String beanName, Class<T> clazz) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(filePath);
        ConfigurableListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource(filePath));
        SensitiveAttributeFilter sensitiveAttributeFilter = beanFactory.getBean("sensitiveAttributeFilter", SensitiveAttributeFilter.class);
        sensitiveAttributeFilter.postProcessBeanFactory(beanFactory);
        return beanFactory.getBean(beanName, clazz);
    }
}
