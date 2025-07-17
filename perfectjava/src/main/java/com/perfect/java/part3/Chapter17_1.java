package com.perfect.java.part3;

import com.perfect.java.part3.Chapter17Class.BaseOverride;
import com.perfect.java.part3.Chapter17Class.MyBase;
import com.perfect.java.part3.Chapter17Class.MyBaseOverride;
import com.perfect.java.part3.Chapter17Class.MyHidingBase;
import com.perfect.java.part3.Chapter17Class.ToStringOverride;
import com.perfect.java.part3.Chapter17Class.hidingBase;

public class Chapter17_1 {

    // Baseクラスの使用例
    static void baseBasic() {
        // varで変数の方を省略可能。暗黙的に変数の型はクラスMyBase型
        var my = new MyBase();
        my.baseMethod();

        // 変数の方を基底クラスにできる
        MyBase my2 = new MyBase();
        my2.baseMethod();
    }

    // MyHidingBaseクラスの使用例
    static void MyHidingBasic() {
        // この変数の型はhidingBase。明示的に指定しても結果は同じ
        // var base = new hidingBase();
        hidingBase base = new hidingBase();
        System.out.println(base.field);

        // この変数の型はMyHidingBase。明示的に指定しても結果は同じ
        var my = new MyHidingBase();
        System.out.println(my.field);

        // 変数の型でフィールドが決定
        hidingBase my2 = new MyHidingBase();
        System.out.println(my2.field);
    }

    // MyBaseOverrideクラスの使用例
    static void baseOverrideBasic() {
        // この変数の型はBaseOverride。明示的に指定しても結果は同じ
        var base = new BaseOverride();
        base.overrideMethod();

        // この変数の型はMyBaseOverride。明示的に指定しても結果は同じ
        var my = new MyBaseOverride();
        my.overrideMethod();

        // 変数の型をBaseOverrideにしてもMyBaseOverrideクラスのメソッドを呼ぶ（オーバーライド）
        BaseOverride my2 = new MyBaseOverride();
        my2.overrideMethod();
    }

    // ToStringOverrideクラスの使用例
    static void toStringBasic() {
        var my = new ToStringOverride();
        System.out.println(my);
    }

    public static void main(String[] args) {
        MyInterfaceExample my = new MyExample();
    }
}

abstract class MyAbstract {
    private final String field = "abc";

    void method() {
        // メソッドの中身は省略
    }
}

interface MyInterfaceExample {
    // 公開メソッドの定義
}

abstract class BaseExample {
    // 骨格実装など
}

class MyExample extends BaseExample implements MyInterfaceExample {
    // メソッド本体
}
