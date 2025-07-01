package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Chapter10_2 {

    static void collectExample() {
        List<String> result = Stream.of("abc", "xyz", "za", "defghi").collect(() -> {
            return new ArrayList<String>();
        }, (acc, cur) -> {
            acc.add(cur);
        }, (acc1, acc2) -> {
            acc1.addAll(acc2);
        });
        System.out.println(result);

        // メソッド参照
        List<String> result2 = Stream.of("abc", "xyz", "za", "defghi").collect((ArrayList::new),
                List::add, List::addAll);
        System.out.println(result2);

    }

    public static void main(String... args) {
        collectExample();
    }
}
