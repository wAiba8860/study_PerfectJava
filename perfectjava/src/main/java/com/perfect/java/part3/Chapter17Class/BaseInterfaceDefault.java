package com.perfect.java.part3.Chapter17Class;

public interface BaseInterfaceDefault {
    public default void method() {
        System.out.println("BaseInterfaceのメソッド");
    }
}

interface MyInterface1 extends BaseInterfaceDefault {
    // 本体省略
}

interface MyInterface2 extends BaseInterfaceDefault {
    default void method() {
        System.out.println("MyInterface2のメソッド");
    }
}

class MyImplements implements MyInterface1, MyInterface2 {
    // 本体省略
}

class Example {
    public static void main(String[] args) {
        // 使用例
        var my = new MyImplements();
        my.method();
    }
}
