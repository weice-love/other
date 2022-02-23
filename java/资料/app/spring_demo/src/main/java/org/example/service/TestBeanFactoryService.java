package org.example.service;

import org.example.aware.BeanAwareTest;
import org.example.entity.MyTestBean;
import org.example.factorybean.Car;
import org.example.label.Person;
import org.example.lookup.GetBeanTest;
import org.example.replace.TestChangeMethod;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 11:41
 */
public class TestBeanFactoryService {


    public static void main(String[] args) {
//        getBean();
//        lookupTest();
//        replaceTest();
//        selfParse();
//        factoryBeanTest();

        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        BeanAwareTest test = (BeanAwareTest) beanFactory.getBean("test");

    }

    private static void factoryBeanTest() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("factoryBeanTest.xml"));
        Car car = (Car) beanFactory.getBean("car");
        System.out.println("brand: " + car.getBrand() + ", maxSpeed: " + car.getMaxSpeed());
    }

    private static void selfParse() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("personTest.xml"));
        Person testBean = (Person) beanFactory.getBean("testBean");
        System.out.println("name: " + testBean.getUserName() + ", email: " + testBean.getEmail() + ", id: ");
    }

    private static void replaceTest() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("replaceTest.xml"));
        TestChangeMethod testChangeMethod = (TestChangeMethod) beanFactory.getBean("testChangeMethod");
        testChangeMethod.changeMe();
    }

    private static void lookupTest() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("lookUpTest.xml"));
        GetBeanTest getBeanTest = (GetBeanTest) beanFactory.getBean("getBeanTest");
        getBeanTest.showMe();
    }

    private static void getBean() {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("myTestBean");
        System.out.println(myTestBean.getTestStr());
    }
}
