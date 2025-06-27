package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chapter9_1 {

    static void methodVarError() {
        // メソッド参照を変数methに代入したいがコンパイルエラー
        // My::methodの意味は本文で説明します
        // 意図はMyクラスのmethodという名前のメソッドの参照の取得です
        // var meth = My::method;
    }

    static void methodRunnable() {
        // 代入
        Runnable meth = My::method;
        // 参照を持つ変数を通じてメソッド呼び出し
        meth.run();
    }

    static void comparatorSort() {
        // sortメソッドは引数の配列を変更するので可変リストを使用
        var list = new ArrayList<>(List.of("abc", "xyz", "za", "defghi"));

        // 下記第二引数のMyComparator::myCompareがメソッド参照
        Collections.sort(list, MyComparator::myCompare);
        System.out.println(list);
    }

    public static void main(String[] args) {
        comparatorSort();
    }
}


class My {
    static void method() {
        System.out.println("メソッド呼び出し");
    }
}


class MyComparator {
    static int myCompare(String s1, String s2) {
        return s2.length() - s1.length();
    }
}
