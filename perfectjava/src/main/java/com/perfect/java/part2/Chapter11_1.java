package com.perfect.java.part2;

import java.util.function.Consumer;

import com.perfect.java.part2.Chap11classInterface.MyClassExample;
import com.perfect.java.part2.Chap11classInterface.MyInterface;
import com.perfect.java.part2.Chap11classInterface.MyString;

public class Chapter11_1 {

    static void useInterfaceExample() {
        // 使用例
        MyInterface my = new MyClassExample();
        my.method();
        my.method_d();
    }

    // static void myStringMethodExample(MyString ms) {
    // 引数の型をCharSequenceインターフェースに変更
    static void myStringMethodExample(CharSequence ms) {
        System.out.println("arg's length is %d".formatted(ms.length()));
    }

    static void staticInterfaceExample() {
        Runnable meth = MyInterface::method_s;
        meth.run();
    }

    static void defaultInterfaceExample() {
        // 使用例
        // インターフェース経由のdefaultメソッドのメソッド参照は不可
        // Runnable meth = MyInterface::method_d;

        // 実装クラス経由のdefaultメソッドのメソッド参照は可能
        Consumer<MyClassExample> meth = MyClassExample::method_d;
        meth.accept(new MyClassExample());
    }

    public static void main(String[] args) {
        useInterfaceExample();
    }
}
