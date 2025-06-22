package com.perfect.java.part2.ClassFile;

public class FactoryMethodExample {
    // コンストラクタが呼べないようにprivateにする
    private FactoryMethodExample() {}

    // ファクトリメソッド
    public static FactoryMethodExample getInstance() {
        // 下記二行は return new Factory~()の1行でもかけます
        FactoryMethodExample my = new FactoryMethodExample();
        return my;
    }
}
