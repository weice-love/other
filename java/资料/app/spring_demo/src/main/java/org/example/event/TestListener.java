package org.example.event;

import org.springframework.context.ApplicationListener;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/24 17:42
 */
public class TestListener implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent event) {
        event.print();
    }
}
