package com.perfect.java.part2.Chap11classInterface;

//インターフェース宣言
public sealed interface MyInterface permits My1, My2, MyClassExample {
    String S = "定数"; // インターフェース定数。暗黙的にpublic static final

    void method(); // 本体（実装）を持たない抽象メソッド宣言

    // void method(String s); // インターフェースのメソッドのオーバーロードの例

    static void method_s() {
        System.out.println("インターフェースのstaticメソッド");
    }

    private void method_p() {
        System.out.println("インターフェースのprivateメソッド");
    }

    default void method_d() {
        System.out.println("インターフェースのdefaultメソッド");
    }
}

final class My1 implements MyInterface {
    public void method() {

    }
}

final class My2 implements MyInterface {
    public void method() {

    }
}

// シールインターフェース継承ができないクラス(permits節に記述がないのでコンパイルエラー)
// final class My3 implements MyInterface {
// public void method() {

// }
// }

// objectクラスのメソッドのオーバーライドは抽象メソッド扱いにならない
// @FunctionalInterface
// Invalid '@FunctionalInterface' annotation; My4 is not a functional
// interfaceJava(553648792)

interface My4 {
    String toString();
}

// defaultメソッドは抽象メソッド扱いにならない
// @FunctionalInterface
interface My5 {
    default void method() {
    }
}

// これは関数型
@FunctionalInterface
interface My6 {
    void method1(); // 抽象メソッドはこの1つのみ

    default void method2() {
    } // defaultメソッド（非抽象メソッド）
}