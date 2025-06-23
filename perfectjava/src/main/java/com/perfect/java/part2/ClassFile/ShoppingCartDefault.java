package com.perfect.java.part2.ClassFile;

public class ShoppingCartDefault {
    private String customerName = "";

    // 引数ありコンストラクタ
    ShoppingCartDefault(String customerName) {
        this.customerName = customerName;
    }
}

class CallError {
    void Example() {
        // 使用例
        // 実引数なしでnew式を使うと内部的にはデフォルトコンストラクタでオブジェクトを生成

        // var cart = new ShoppingCartDefault();
        // 修正前は動いていた実引数なしnew式がコンパイルエラーもしくは実行時エラーになる

        var my = new My();
    }
}

class Base {

    // Baseクラスに引数ありコンストラクタを追加（デフォルトコンストラクタが消滅）
    Base(String s) {
    }

} // デフォルトコンストラクタあり

class My extends Base {
    // 明示的にコンストラクタを書き足してもエラーのまま（暗黙の引数なしsuper呼び出しがあるため）
    My() {
        // 明示的に引数ありのsuper呼び出しを追記してエラーを修正
        super("abc");
    }
} // デフォルトコンストラクタあり
  // 変更前は動いていたコードがコンパイルエラーもしくは実行時エラー
