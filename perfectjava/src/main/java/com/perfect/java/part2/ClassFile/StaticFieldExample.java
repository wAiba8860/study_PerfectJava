package com.perfect.java.part2.ClassFile;

public class StaticFieldExample {
    // クラスフィールド変数Sを宣言および初期化
    // privateから別のアクセス制御に変更しても成り立ちます
    public static final String S = "staticフィールド値";

    // インスタンスフィールド変数の初期化子でSを使用可能
    // （別の）クラスフィールド変数の初期化子、コンストラクタ、初期化ブロックでも使用可能
    private final String s = S;

    // インスタンス・メソッド内でSを使用可能
    // クラスメソッド内でもSを使用可能
    void method() {
        System.out.println(S);
    }

    // クラスメソッド宣言（クラス内で各順序は無関係）
    // privateから別のアクセス制御に変更しても成り立ちます
    public static String METHOD() {
        return "staticメソッドの返り値";
    }

    // インスタンスフィールド変数の初期化子で呼び出し可能
    // クラスフィールド変数の初期化子、コンストラクタ、初期化ブロックでも使用可能
    private final String sm = METHOD();

    // インスタンス・メソッド内で呼び出し可能
    // （別の）クラスメソッド内でも呼び出し可能
    public void callMethod() {
        System.out.println(METHOD());
    }

    static void methodError() {
        // System.out.println(this.s); //コンパイルエラー。thisの有無は無関係
        // this.METHOD(); //コンパイルエラー。thisの有無は無関係
    }

    // 引数で受け取るオブジェクトのインスタンメンバにアクセスするクラスメソッド
    // クラスメソッド
    static void METHOD(StaticFieldExample my) {
        System.out.println(my.s);
    }

    // 上記コードの使用例
    static void useMETHODExample() {
        StaticFieldExample.METHOD(new StaticFieldExample());
    }

    // StaticFieldクラスとOtherクラスの使用例
    public static void main(String[] args) {
        useMETHODExample();
    }

}

class OtherStatic {
    void method() {
        // クラス名と.（ドット）でクラスフィールドアクセス可能
        System.out.println(StaticFieldExample.S);

        // オブジェクト参照と.（ドット）でクラスフィールドアクセス可能（非推奨）
        var my = new StaticFieldExample();
        System.out.println(my.S);
    }

    void callMyMethod() {
        // クラス名と.（ドット）でクラスメソッド呼び出し
        String s1 = StaticFieldExample.METHOD();
        System.out.println(s1);

        // オブジェクト参照と.（ドット）でクラスメソッド呼び出し（非推奨）
        var my = new StaticFieldExample();
        String s2 = my.METHOD();
        System.out.println(s2);
    }
}

// 元のコード
class MyStatic {
    static final String FIELD = "3.14"; // クラスフィールド

    static void METHOD() {
    } // クラスメソッド

    private final String field = "3"; // インスタンスフィールド

    void method() {
    }

}
// 上記コードの擬似コード
// String MyStatic.FIELD; // クラスフィールドのイメージ
// void MyStatic.METHOD(){} // クラスメソッドのイメージ

// class MyStatic{
// private final String field; //インスタンスフィールド
// void method(){} //インスタンス・メソッド
// }

// オブジェクト生成数を数えるクラス
class CountObject {
    private CountObject() {
    }

    static int instanceNum; // オブジェクト生成数

    static CountObject getInstance() { // オブジェクトを生成して返すファクトリメソッド
        CountObject.instanceNum++;
        return new CountObject();
    }
}