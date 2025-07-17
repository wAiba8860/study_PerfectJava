package com.perfect.java.part3.Chapter17Class;

//派生クラス
public class MyBaseOverride extends BaseOverride {
    // privateから別のアクセス制御に変更しても結果は同じ
    private final String field = "Myクラスのフィールド";

    // メソッドのオーバーライド
    @Override
    public void overrideMethod() {
        // Baseクラスのメソッド呼び出し（メソッド内のどこにでも記述可能）
        super.overrideMethod();
        // 下記のthis.filedのthisは省略可能。省略しても結果は同じ
        System.out.println("Myクラスのメソッド、%s、%s".formatted(this.field, super.field));
    }

    // 返り値の型はのStringはObjectの派生型。オーバーライド可能
    @Override
    public String objectToString() {
        return "abc";
    }
}
