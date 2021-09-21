package com.junit_demo.app.java8.trade;

import com.junit_demo.app.java8.model.Trader;
import com.junit_demo.app.java8.model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2021/9/8 17:51
 */
public class StreamUseDemo_1 {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        print(transactions.stream().filter(e -> e.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList()));
        // 交易员都在哪些不同的城市工作过？
//        print(transactions.stream().map(Transaction::getTrader).distinct().map(Trader::getCity).collect(Collectors.toList()));
        print(transactions.stream().map(e -> e.getTrader().getCity()).distinct().collect(Collectors.toList()));
        // 查找所有来自于剑桥的交易员，并按姓名排序
        print(transactions.stream().map(Transaction::getTrader).filter(e -> "Cambridge".equals(e.getCity())).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList()));
        // 返回所有交易员的姓名字符串，按字母顺序排序。
//        print(transactions.stream().map(Transaction::getTrader).distinct().map(e -> e.getName().split("")).flatMap(Arrays::stream).sorted(String::compareToIgnoreCase).collect(Collectors.toList()));
        System.out.println(transactions.stream().map(e -> e.getTrader().getName()).distinct().sorted().reduce("", (str1, str2) -> str1 + str2));
        System.out.println(transactions.stream().map(e -> e.getTrader().getName()).distinct().sorted().collect(Collectors.joining()));
        // 有没有交易员是在米兰工作的？
        System.out.println(transactions.stream().map(Transaction::getTrader).anyMatch(e -> "Milan".equals(e.getCity())));
        // 打印生活在剑桥的交易员的所有交易额
//        System.out.println(transactions.stream().filter(e -> "Milan".equals(e.getTrader().getCity())).map(Transaction::getValue).reduce(0, Integer::sum));
        transactions.stream().filter(e -> "Milan".equals(e.getTrader().getCity())).forEach(System.out::println);
        // 所有交易中，最高的交易额是多少
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(Integer::max));
        // 找到交易额最小的交易
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(Integer::min));
    }

    public static <T> void print(List<T> result1) {
        System.out.println("打印开始...");
        result1.forEach(e -> System.out.print(e + ", "));
        System.out.println("打印结束...");

    }
}
