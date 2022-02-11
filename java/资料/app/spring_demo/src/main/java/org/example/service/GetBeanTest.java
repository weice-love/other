package org.example.service;

import org.example.entity.User;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 16:42
 */
public abstract class GetBeanTest {

    public void showMe() {
        this.getBean().showMe();
    }
    public abstract User getBean();
}
