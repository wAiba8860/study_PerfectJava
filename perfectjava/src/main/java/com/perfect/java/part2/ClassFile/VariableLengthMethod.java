package com.perfect.java.part2.ClassFile;

public class VariableLengthMethod {

    // 可変長引数のメソッドの宣言例
    public void method(String... messages) {
        // void method(int i , String... messages) OK
        // void method(int i , String... messages, String... m2) コンパイルエラー
        // void method(String... messages, int i) コンパイルエラー
        for (String s : messages) {
            System.out.println(s);
        }
    }

    public void methodArray(String[] messages) {
        for (String s : messages) {
            System.out.println(s);
        }
    }

}
