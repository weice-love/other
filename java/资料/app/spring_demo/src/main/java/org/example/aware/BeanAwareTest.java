package org.example.aware;

import org.example.entity.MyTestBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/22 16:06
 */
public class BeanAwareTest implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("注入beanFactory");
        this.test();
    }

    public void test() {
        MyTestBean myTestBean = (MyTestBean) beanFactory.getBean("myTestBean");
        System.out.println(myTestBean.getTestStr());
    }
}
