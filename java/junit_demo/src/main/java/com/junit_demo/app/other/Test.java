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

    private static long count = 0;

    public static void main(String[] args) {
//        long count = 0;
        while (true) {
            long t1 = System.nanoTime();
            for(int i = 0; i < 100000000; i++) {
                count++;
                if((count & 1) == 0) {
                    count =1;
                }
            }
            long t2  = System.nanoTime();
            System.out.println((t2 - t1) + "ns " + count);
        }
    }

}
