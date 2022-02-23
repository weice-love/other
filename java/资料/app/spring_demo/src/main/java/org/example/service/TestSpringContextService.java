package org.example.service;

import org.example.context.UserManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 11:41
 */
public class TestSpringContextService {


    public static void main(String[] args) {
        UserManager userManager = loadBean("beans.xml", "userManager", UserManager.class);
        System.out.println("date: " + userManager.getDateValue());
    }

    private static <T> T loadBean(String filePath, String beanName, Class<T> clazz) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(filePath);
        return context.getBean(beanName, clazz);
    }
}
