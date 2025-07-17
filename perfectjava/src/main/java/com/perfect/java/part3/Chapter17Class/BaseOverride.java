package com.perfect.java.part3.Chapter17Class;

//基底クラス
public class BaseOverride {
    // privateから別のアクセス制御に変更しても結果は同じ
    final String field = "Baseクラスのフィールド";

    public void overrideMethod() {
        // 下記のthis.fieldのthisは省略可能。省略しても結果は同じ
        System.out.println("Baseクラスのメソッド、" + this.field);
    }

    public Object objectToString() {
        return new Object();
    }
}
