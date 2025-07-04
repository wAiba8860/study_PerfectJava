package com.perfect.java.part2.Chap11classInterface;

import java.io.Console;

//MyEchoオブジェクトを使うコード
public class BeforeCallBackMyEcho {
    public static void main(String... args) {
        var echo = new MyEcho();
        echo.execute();
    }
}

class MyEcho {
    // replaceとcapitalizeは下請けメソッド
    private String replace(String input, String oldStr, String newStr) {
        return input.replaceAll(oldStr, newStr);
    }

    private String capitalized(String input) {
        return input.toUpperCase();
    }

    void execute() {
        Console console = System.console();
        while (true) {
            System.out.println("Input any text");
            String msg = console.readLine();

            // 整形処理（実際にはもっと複雑なコードを仮定してください）
            System.out.println("You said, " + capitalized(replace(msg, "he", "she")));
        }
    }
}