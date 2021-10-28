package com.junit_demo.app.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/10/26 17:24
 */

public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1,2,3");
        String[] strings1 = strings.toArray(new String[0]);
        log.info("{}", strings1);
    }
}
