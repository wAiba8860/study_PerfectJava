package com.perfect.java.part2;

public class Chapter4_2 {

    static void integerInitialize() {
        int i; // 変数iの宣言

        // 宣言と同時に初期化する変数の例
        int p = 1;

        // 基本型変数の代入例
        int j = 42; // jの値は42
        int k = j + 1; // kの値は43
        int u = j; // uの値は42

        // 変数の値に加算
        j++; // 変数jの値が42から43になる

        final double PI = 3.14;
        // PI = 3.14159; // 再代入はコンパイルエラー
    }

    static void variableScope() {
        // System.out.println(i); // コンパイルエラー（変数iのスコープ外だから）
        int i = 0;
        System.out.println(i); // OK（変数iのスコープ内だから）
    }

    static void blockScope1() {
        { // ブロック開始
            int i = 0;
            System.out.println(i); // OK（変数iのスコープ内だから）
        } // ブロック終了
          // System.out.println(i); // コンパイルエラー（変数iのスコープ外だから）
    }

    static void blockScope2() {
        {
            int i = 0;
            System.out.println(i); // OK（変数iのスコープ内だから）
        }
        int i = 1; // OK（上記ブロック内の変数iのスコープ外だから）
        System.out.println(i); // OK（新しい変数iのスコープ内だから）
    }

    static void shadowing() {
        int i = 1;
        {
            // int i = 0; // コンパイルエラー（シャドーイングだから）
        }
    }

    static void parameterShadowing(int i) {
        // int i = 0; // コンパイルエラー（シャドーイングだから）
    }

    static void fieldVariable() {
        class My {
            void method() {
                int i = 10; // ローカル変数
                System.out.println(i); // OK（変数iのスコープ内だから）ローカル変数iを優先
                System.out.println(this.i); // フィールド変数の参照
            }

            int i = 0; // フィールド変数の宣言と初期化
        }
        var p = new My();
        p.method();
    }

    public static void main(String[] args) {
        fieldVariable();
    }
}
