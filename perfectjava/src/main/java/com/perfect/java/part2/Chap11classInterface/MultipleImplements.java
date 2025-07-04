package com.perfect.java.part2.Chap11classInterface;

//2つのインターフェースを多重継承した実装クラス
//2つのメソッドのオーバーライド実装が必要
//2つのメソッドの関係はオーバーロードになる
public class MultipleImplements implements MyInterface1, MyInterface2 {

    // 同名のフィールドを宣言してコンパイルエラーを回避（非推奨）
    String VALUE = "def";

    // Javaのメソッドのシグネチャは返り値の型を含まない
    // 返り値の型だけことなるメソッドを多重継承したコードはコンパイルエラー

    // 継承関係のある型の返り値を持つ同名のメソッドの多重継承は可
    @Override
    public String method() { // 返り値の型をStringにする。Objectにするとコンパイルエラー
        return "";
    }

    // @Override
    // public int method() {
    // return 0;
    // }

    // 同じシグネチャのdefaultメソッドを持つインターフェースを多重継承するとコンパイルエラー
    // 多重継承したメソッドをオーバーライドするとエラーを解消可能
    @Override
    public void defaultMethod() {
        // The field VALUE is ambiguous

        // インターフェースフィールドの使用（推奨）
        System.out.println(MyInterface1.VALUE);
        System.out.println(MyInterface2.VALUE);
    }
}

interface MyInterface1 {
    // void method();
    // String method();
    Object method();

    default void defaultMethod() {
    }

    String VALUE = "abc";
}

interface MyInterface2 {
    // void method(String s);
    // int method();
    String method();

    default void defaultMethod() {
    }

    String VALUE = "abc";
}
