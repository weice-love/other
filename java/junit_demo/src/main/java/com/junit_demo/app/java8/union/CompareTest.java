package com.junit_demo.app.java8.union;

import com.junit_demo.app.java8.model.TrainJourney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> @author     :  清风
 * <p> description :
 *
 * <p> create date :  2021/11/16 14:37
 */
public class CompareTest {

    private static final Logger log = LoggerFactory.getLogger(CompareTest.class);

    @DisplayName("比较传统方式和函数式方式")
    @Test
    public void test() {
        TrainJourney hangzhou2jinhua = new TrainJourney(74, null);
        TrainJourney jinhua2taizhou = new TrainJourney(120, null);
        TrainJourney h2t = append(hangzhou2jinhua, jinhua2taizhou);
        log.info("h2t: {}", h2t);
        TrainJourney h2t2 = link(hangzhou2jinhua, jinhua2taizhou);
        log.info("h2t2: {}", h2t2);
    }


    /**
     * @author     : 清风
     * <p>description :
     *      函数式方案
     *          缺点: 不能对返回的结果进行修改，否则也会影响数据
     * <p>create time : 17:16 2021/11/16
     *
     */
    private TrainJourney append(TrainJourney a, TrainJourney b) {
        return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
    }

    /**
     * @author     : 清风
     * <p>description :
     *      破坏式
     *          传统方式，会改变传入的参数（有副作用）
     * <p>create time : 17:14 2021/11/16
     *
     */
    private static TrainJourney link(TrainJourney a, TrainJourney b) {
        if (a == null) return b;
        TrainJourney t = a;
        while (t.onward != null) {
            t = t.onward;
        }
        t.onward = b;
        return a;
    }
}
