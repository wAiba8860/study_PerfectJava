package com.perfect.java.part2.ClassFile;

//不変クラスになってない例
final public class NotImmutableClass {
    private final StringBuilder sb;

    public NotImmutableClass(StringBuilder sb) {
        this.sb = sb; // 参照型のフィールド変数に外部から与えられたオブジェクトの参照をそのまま代入
    }

    public StringBuilder getBuffer() {
        return sb; // 参照型のフィールド変数の値を外部にそのまま返す
    }
}

// 上記コードの不変性を破る例
class BreakingImmutable {
    // コンストラクタに渡したオブジェクトを呼び出し側で変更する例
    static StringBuilder sb = new StringBuilder("012");

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("012");
        System.out.println(sb);
        sb.append("345");
        System.out.println(sb);
        method();
    }

    // フィールド変数の参照オブジェクトを外部から取得して変更する例
    static void method() {
        NotImmutableClass my = new NotImmutableClass(sb);
        StringBuilder sb = my.getBuffer();
        sb.append("345"); // myオブジェクトのフィールド変数の参照オブジェクトを変更
        System.out.println(sb.toString());
    }
}
