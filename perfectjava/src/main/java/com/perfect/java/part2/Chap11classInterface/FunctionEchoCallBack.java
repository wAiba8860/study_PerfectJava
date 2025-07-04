package com.perfect.java.part2.Chap11classInterface;

import java.io.Console;
import java.util.function.Function;

//MyEcho3オブジェクトを使うコード
public class FunctionEchoCallBack {
    public static void main(String... args) {
        Function<String, String> fn = s -> s.replaceAll("he", "she");
        var echo = new MyEcho3(fn.andThen(s -> s.toUpperCase()));
        echo.execute();
    }
}

class MyEcho3 {
    private final Function<String, String> filter;

    public MyEcho3(Function<String, String> filter) {
        this.filter = filter;
    }

    public void execute() {
        Console console = System.console();
        while (true) {
            System.out.println("Input any text");
            String msg = console.readLine();

            String output = this.filter.apply(msg);
            System.out.println("You said, " + output);
        }
    }
}
