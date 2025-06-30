package com.perfect.java.part2;

import java.util.List;
import java.util.stream.Stream;

public class Chapter10_1 {

    static void streamExampleRedundant() {
        // データソースを準備
        List<String> list = List.of("abc", "xyz", "za", "defghi");

        // データソースから初期ストリーム生成
        Stream<String> stream1 = list.stream();

        // ストリームの中間処理:filter処理（"a"を含む文字列として"abc"と"za"だけ通過）
        Stream<String> stream2 = stream1.filter(s -> s.contains("a"));

        // ストリーム中間処理:map処理（大文字変換で"ABC"と"ZA"になる）
        Stream<String> stream3 = stream2.map(s -> s.toUpperCase());

        // ストリーム終端処理:画面出力
        stream3.forEach(s -> System.out.println(s));
    }

    static void streamExample() {
        // データソースを準備
        List<String> list = List.of("abc", "xyz", "za", "defghi");

        list.stream().filter((String s) -> {
            return s.contains("a");
        }).map((String s) -> {
            return s.toUpperCase();
        }).forEach(System.out::println);

        List.of("abc", "xyz", "za", "defghi").stream().filter(s -> s.contains("a"))
                .map(String::toUpperCase).forEach(System.out::println);
    }

    static void streamErrorPeek() {
        List.of("abc", "xyz", "za", "defghi").stream().filter(s -> s.contains("a"))
                // .forEach(System.out::println) //終端処理の後続処理はエラー
                .peek(System.out::println).map(String::toUpperCase).forEach(System.out::println);
    }

    public static void main(String[] args) {
        streamErrorPeek();
    }
}
