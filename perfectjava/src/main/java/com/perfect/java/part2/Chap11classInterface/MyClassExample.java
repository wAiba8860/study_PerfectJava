package com.perfect.java.part2.Chap11classInterface;

public non-sealed class MyClassExample implements MyInterface { // MyClassがMyInterfaceを継承
    @Override
    public void method() {
        System.out.println("Myクラスのメソッド呼び出し");
        System.out.println(S); // 定数を出力
    }

    @Override
    public void method_d() {
        // public void methodd() {
        MyInterface.super.method_d(); // 継承元のdefaultメソッド呼び出し
        System.out.println("method_dのつもりでmethoddとタイプミス");
    }

    // コンパイルエラーにならない
    // オーバーライドではなく、単に別のメソッド
    static void method_s() {

    }

    // コンパイルエラーにならない
    // オーバーライドではなく、単に別のメソッド
    private void method_p() {

    }
}

class MyClassNotImplements { // MyInterfaceを実装していないクラス
    void method() {
        System.out.println(MyInterface.S); // 定数を出力
    }
}
