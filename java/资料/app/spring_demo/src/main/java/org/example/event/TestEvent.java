package org.example.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/24 17:37
 */
public class TestEvent extends ApplicationEvent {

    private String msg;

    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(msg);
    }
}
