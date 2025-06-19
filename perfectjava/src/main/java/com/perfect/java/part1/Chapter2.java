package com.perfect.java.part1;

//実行を意図したクラスにはpublic修飾子を書きます
public class Chapter2 {

    // mainメソッドから実行開始
    public static void main(String... args) {

        // printlnの代わりにprintにするとメッセージ出力後に改行しなくなります
        System.out.println("mainメソッド呼び出し");
        jShellSample();
        methodExample();
        method();
    }

    public static void jShellSample() {
        int result = 1 + 2;
        System.out.println(result);
    }

    public static void methodExample() {
        var obj = new My();
        obj.method();
    }

    public static void method() {
        System.out.println("メソッド呼び出し");
    }

}

class My {
    void method() {
        System.out.println("メソッド呼び出し");
    }
}
