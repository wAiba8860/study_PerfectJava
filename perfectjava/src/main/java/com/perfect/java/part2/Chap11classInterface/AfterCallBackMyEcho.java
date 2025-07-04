package com.perfect.java.part2.Chap11classInterface;

import java.io.Console;
import java.util.List;

//MyEchoオブジェクトを使うコード
public class AfterCallBackMyEcho {
    public static void main(String... args) {
        var echo = new MyEcho2(List.of(new ReplaceFilter("I", "MyI"), new CapitalizeFilter()));
        echo.execute();
    }
}

class MyEcho2 {
    private final List<MyFilter> filters;

    public MyEcho2(List<MyFilter> filters) {
        this.filters = filters;
    }

    void execute() {
        Console console = System.console();
        while (true) {
            System.out.println("Input any text");
            String msg = console.readLine();
            String output = msg;

            for (MyFilter filter : filters) {
                output = filter.doJob(output); // コールバック処理
            }
            // ストリーム処理で書くと下記のように再代入コードをなくせます
            // // 順序性があるので並列ストリーム処理にはできません
            // String output = filters.stream().reduce(msg, (acc, filter) -> {
            // return filter.doJob(acc);
            // }, (acc1, acc2) -> {
            // return acc1;
            // });
            System.out.println("You said, " + output);
        }
    }
}

interface MyFilter {
    String doJob(String input);
}

class ReplaceFilter implements MyFilter {
    private final String oldStr;
    private final String newStr;

    public ReplaceFilter(String oldStr, String newStr) {
        this.oldStr = oldStr;
        this.newStr = newStr;
    }

    @Override
    public String doJob(String input) {
        return input.replaceAll(oldStr, newStr);
    }
}

class CapitalizeFilter implements MyFilter {
    @Override
    public String doJob(String input) {
        return input.toUpperCase();
    }
}