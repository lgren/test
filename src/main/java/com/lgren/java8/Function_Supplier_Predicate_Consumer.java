package com.lgren.java8;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * TODO
 * @author lgren
 * @create 2019-04-08 5:42 PM
 **/
public class Function_Supplier_Predicate_Consumer {
    public static void main(String[] args) {
        // Function<参数类型, 返回值类型> 有参数 有返回值
        // 所有的Bi开头代表具有两个参数
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> f2 = x -> x << 1;
        Function<Integer, Integer> f3 = f1.andThen(f2);
        Function<Integer, Integer> f4 = f1.compose(f2);

        // Function<返回值类型> 无参数 有返回值
        Supplier<Integer> s1 = () -> 5;

        // Predicate<参数类型> 有参数 有返回值(true/false)
        Predicate<Integer> p1 = i -> i > 0;

        // BiConsumer<参数类型, 参数类型> 有参数 无返回值
        BiConsumer<Integer, Integer> bc1 = (i1, i2) -> {
            i1 <<= 1;
            i2 <<= 2;
        };
        System.out.println(f1.apply(10));
        System.out.println(f2.apply(10));
        System.out.println(f3.apply(10));
        System.out.println(f4.apply(10));
        System.out.println(s1.get());

        System.out.println(s1.get());
        System.out.println(p1.test(6));
    }
}
