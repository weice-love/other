package com.junit_demo.app.util;

import org.HdrHistogram.Histogram;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/11/10 11:21
 */
public class HistogramUtil {

    public static void main(String[] args) {
        Histogram histogram = new Histogram(2, 2);
        histogram.recordValue(10);
        histogram.recordValue(20);
        System.out.println(histogram.getTotalCount());
        System.out.println(histogram.getMean());
        System.out.println(histogram.getEstimatedFootprintInBytes());
    }
}
