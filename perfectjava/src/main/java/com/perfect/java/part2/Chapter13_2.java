package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Chapter13_2 {

    static void ifElseIndent() {
        int i = 0, j = 0;
        if (i == 0)
            if (j == 0)
                System.out.println("i == 0 and j == 0");
            else
                System.out.println("i==0 and j!=0");
        else
            System.out.println("i!=0");

        if (i == 0)
            if (j == 0)
                System.out.println("i==0 and j==0");
            else
                System.out.println("i==0 and j!=0");
    }

    static void ifElseBlock() {
        int i = 0, j = 0;
        if (i == 0) {
            if (j == 0) {
                System.out.println("i == 0 and j == 0");
            } else {
                System.out.println("i == 0 and j != 0");
            }
        }
    }

    static void constantConditional() {
        // コメントアウトする場合に用いる
        if (true) {

        }
        if (false) {

        }
    }

    void nestConditional() {
        // 変数flag1とflag2の型はbooleanを仮定
        boolean flag1 = true;
        boolean flag2 = false;
        int i = flag1 ? (flag2 ? 0 : 1) : 2;
    }

    void ternaryOperator() {
        int i; // 未初期化変数
        boolean flag = false;
        if (flag) {
            i = 1;
        } else {
            i = 0;
        }

        // 上記if文を条件演算子の四季で書き換えた例
        int j = flag ? 1 : 0;

        // コンパイルエラーになる例
        // 条件演算式は式文にならないのでコンパイルエラー
        // flag ? System.out.println("1") : System.out.println("0");

        // 式にブロック文を書けないのでコンパイルエラー
        // int len = flag ? {new StringBuilder("abc").length();} : new
        // StringBuilder("defghi").length();

        // これは有効
        int len = flag ? new StringBuilder("abc").length() : new StringBuilder("defghi").length();

        // コンパイルは通るが非推奨
        var n = flag ? 0 : "abc"; // 内部的には変数nの型がObjectになる

        // 条件演算式の2つの式の型が完全一致してない例（有効なコード）
        // 式全体の型は2つの参照型の共通の基底型
        List<String> list = flag ? new ArrayList<>() : new LinkedList<>();
    }

    public static void main(String[] args) {

    }
}
