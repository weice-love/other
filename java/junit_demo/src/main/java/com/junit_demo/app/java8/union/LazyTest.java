package com.junit_demo.app.java8.union;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p> @author     :  清风
 * <p> description :
 *      延时列表
 * <p> create date :  2021/11/17 11:25
 */
public class LazyTest {

    private static final Logger log = LoggerFactory.getLogger(LazyTest.class);

    @DisplayName("利用延时队列生成质数")
    @Test
    public void primeList() {
//        MyList<Integer> list = new LazyList<>(5, () -> new LazyList<>(10, Empty::new));
        LazyList<Integer> list = from(2);
//        log.info("head： {}", list.head());
//        log.info("head -> head： {}", list.tail().head());
//        log.info("head -> head -> head： {}", list.tail().tail().head());
        // 使用了一个自动生成的数字列表，和生成了一个质数的列表
        MyList<Integer> primes = primes(list);
        // 使用循环
        for (int i = 0; i < 10; i++) {
            log.info("=================> 第{}个质数: {}", i+1, primes.head());
            primes = primes.tail();
        }
        // 使用递归打印，存在堆栈溢出问题（java8不支持尾调优化）
//        printAll(primes, 1);
    }

    public static <T> void printAll(MyList<T> list, int ans) {
        if (list.isEmpty()) {
            return;
        }
        log.info("第{}个质数: {}", ans, list.head());
        printAll(list.tail(), ans + 1);
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)), "primes");
    }


    @DisplayName("构造延时队列(可无限拓展)")
    @Test
    public void lazyList() {
//        MyList<Integer> list = new LazyList<>(5, () -> new LazyList<>(10, Empty::new));
        LazyList<Integer> list = from(2);
        log.info("head： {}", list.head());
        log.info("head -> head： {}", list.tail().head());
        log.info("head -> head -> head： {}", list.tail().tail().head());
    }

    public LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    @DisplayName("构造普通队列")
    @Test
    public void linkList() {
        MyList<Integer> list = new MyLinkList<>(5, new MyLinkList<>(10, new Empty<>()));
    }

    static class LazyList<T> implements MyList<T> {
        private final T head;
        private final Supplier<MyList<T>> tail;
        private String entrance;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
            log.info("创建lazylist: {}", head);
        }

        public LazyList(T head, Supplier<MyList<T>> tail, String entrance) {
            this.head = head;
            this.tail = tail;
            log.info("entrance:{}, 创建lazylist: {}", entrance, head);
        }



        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail.get();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    static class Empty<T> implements MyList<T> {
        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }
    }

    static class MyLinkList<T> implements MyList<T> {

        private final T head;

        private final MyList<T> tail;

        public MyLinkList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        default MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p), "filter") : tail().filter(p);
        }
    }

}
