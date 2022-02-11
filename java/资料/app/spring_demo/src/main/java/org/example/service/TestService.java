package org.example.service;

import org.example.entity.MyTestBean;
import org.example.lookup.GetBeanTest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 11:41
 */
public class TestService {


    public static void main(String[] args) {
//        getBean();
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
