package org.example.service;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/24 15:39
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = IntStream.generate(() -> new Random().nextInt(1000)).peek(a -> System.out.print(a + ",")).limit(10).toArray();
        sort(array, 0 , array.length);
    }

    public static void sort(int a[], int left, int right) {

        int start = left;
        int end = right;
        while (start < end) {

        }

    }
}
