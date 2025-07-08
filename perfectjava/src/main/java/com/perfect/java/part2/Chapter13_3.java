package com.perfect.java.part2;

import java.util.stream.IntStream;

public class Chapter13_3 {

    static void whileBlockExample() {
        var flag = false;
        // 紛らわしいインデント。なぜなら、2番目のprintlnは実際にはwhile文の外なので
        while (flag)
            System.out.println("in loop");
        System.out.println("not in loop"); // バグの元

        // ブロック文にすると構造を明確にできる
        while (flag) {
            System.out.println("in loop1");
            System.out.println("in loop2");
        }
    }

    static void nestWhileExample() {
        var condition = false;
        var condition2 = false;
        // 入れ子のwhile文
        while (condition) {
            while (condition2) {
                System.out.println("in double loop");
            }
        }

        // while文の中のif文
        while (condition) {
            if (condition2) {
                System.out.println("in loop");
            }
        }
    }

    static void infiniteLoop() {
        while (true) {
            System.out.println("infinite loop");
        }
    }

    static void escapeLoop() {
        // 10回ループがまわるwhile文
        int i = 0;
        while (i < 10) {
            i++;
        }
        System.out.println("ループを抜けました");

        // 10回ループが回るwhile文の別解
        boolean doing = true; // フラグ定数
        int j = 0;
        while (doing) {
            j++;
            if (j == 10) {
                doing = false; // break文のほうが適切
            }
        }
    }

    static void dpWhileExample(int n) {
        do {
            System.out.println(n % 10);
            n /= 10;
        } while (n > 0);
    }

    static void streamLoopLikeFor() {
        // for文のイディオム担当。0から9までの数字列のストリームになる
        IntStream.iterate(0, i -> i < 10, i -> i + 1).forEach(System.out::println);

        // for文のイディオム担当。rangeの引数に0と10を渡すと、0から9までの数字列ストリームになる
        IntStream.range(0, 10).forEach(System.out::println);

        // rangeClosedの引数に0と10を渡すと、0から10までの数字ストリームになる
        IntStream.rangeClosed(0, 10).forEach(System.out::println);
    }

    void infiniteLoopFor() {
        for (;;) {
            System.out.println("infinite");
        }
    }

    public static void main(String... args) {
        streamLoopLikeFor();
    }
}
