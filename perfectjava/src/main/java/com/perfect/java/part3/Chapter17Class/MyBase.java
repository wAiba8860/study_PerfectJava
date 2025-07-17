package com.perfect.java.part3.Chapter17Class;

//派生クラスの単純概念
class Base {

    public Base() {
        System.out.println("Baseクラスのコンストラクタ");
    }

    // 下記フィールドをprivateにしても動作します
    final String field = "Baseクラスのふぃーるど";

    public void baseMethod() {
        System.out.println("Baseクラスのメソッド、" + this.field);
    }
}

public class MyBase extends Base {
    public MyBase() {
        super();
        System.out.println("Myクラスのコンストラクタ");
    }
    // ここにBaseクラスの本体があるイメージ
}
