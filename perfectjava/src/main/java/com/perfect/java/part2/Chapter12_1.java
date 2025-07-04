package com.perfect.java.part2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Chapter12_1 {

    static void contextualKeywordUse() {
        // 予約語(ContextualKeyword)を識別子に使用可能（非推奨）
        var var = "abc";
        String record = "abc";
    }

    static void formulaSpecificExample() {
        int[] array = new int[5];
        // 変数
        int i;
        // オブジェクト生成式
        new StringBuilder();
        // フィールドアクセス式
        // obj.field;
        // 配列アクセス式
        i = array[0];
        // メソッド呼び出し式
        // obj.method();
        // メソッド参照式
        // String::valueOf;
        // ラムダ式
        // (x , y) -> {return x + y};
        // switch式
        // switch(i) {case 1 -> "one"; default -> "other";}
    }

    public static void prefixPostfixOperator() {
        // nは11になる。mは11になる（++nの評価値は加算後の値）
        int n = 10;
        int m = ++n;
        System.out.println(m);
        System.out.println(n);

        // nは11になる。mは10になる（n++の評価値は加算前の値）
        int n2 = 10;
        int m2 = n2++;
        System.out.println(m2);
        System.out.println(n2);
    }

    static void prefixOperatorExample() {
        int n = 0;
        while (++n < 10) {
            // while(n ++ <10) {
            System.err.println(n + " 回目のループです");
        }
    }

    static void objectToString() {
        // 暗黙的にlist.toString()が呼ばれてListを文字列に型変換。その後、文字列結合
        var list = List.of(123, 456);
        String s = "abc" + list;
        System.out.println(s);

        // 数値と文字リテラル
        System.out.println('a' + 1);
        System.out.println('a' + 'b');
    }

    static void rightJoinOperator() {
        int i, j, k;
        i = j = k = 0;
        // は右結合なので
        i = (j = (k = 0));
        // と評価されて、i,j,kのすべてに0を代入する
    }

    static void instanceOfExample() {
        // 変数objの型はObject。参照先のオブジェクトの型はString型
        Object obj = "abc";

        // 右オペランドが型名。オブジェクトの型と型名が一致すると式の評価値がtrue
        System.out.println(obj instanceof String);

        // 右オペランドが型名。オブジェクトの基底型と型名が一致するとtrue
        System.out.println(obj instanceof CharSequence);

        // 右オペランドがパターン（sはパターン変数）。オブジェクトの方とパターン型名が一致するとtrue
        System.out.println(obj instanceof String s);

        // 右オペランドがパターン（csはパターン変数）。オブジェクトの基底型とパターンの型名が一致するとtrue
        System.out.println(obj instanceof CharSequence cs);
    }

    static void typeComparisonOperationsPatternMatchCompare() {
        // 変数objの型はobject。参照先のオブジェクトの型はString
        Object obj = "abc";

        // 型比較演算
        if (obj instanceof String) {
            String s = (String) obj; // キャストを使う代入式
            System.out.println("型は " + s.getClass());
        }

        // パターンマッチ演算
        if (obj instanceof String s) {
            System.out.println("型は " + s.getClass());
        }
    }

    static void patternVariableScope(Object obj) {
        if (!(obj instanceof String s)) {
            System.out.println("not match");
            return;
        }
        // ここでパターン変数sを使える
        // ただし上記のif文のreturn文がないとコンパイルエラー
        System.out.println(s);

        // 下記コードは問題なし。パターン変数sをinstanceof演算が真の時のみ使うため
        if (obj instanceof String s1 && !s1.isEmpty()) {
            System.out.println("有効な文字列");
        }

        // 下記コードはコンパイルエラー
        // if (obj instanceof String s2 || s2.isEmpty()) {
        // System.out.println("書き換えで不正になったコード例");
        // }
    }

    static void genericTypeInstanceOf() {
        // 演算対象のオブジェクト
        var list = new ArrayList<String>();

        // もっとも素直なコード
        System.out.println(list instanceof ArrayList<String> alist);

        // 型引数がなくてもtrue
        System.out.println(list instanceof ArrayList alist2);

        // オブジェクトをList<String>型の変数に代入可能なのでtrue
        System.out.println(list instanceof List<String> aList3);

        // 同上
        System.out.println(list instanceof List aList4);

        // オブジェクトをList<? extends String>型の変数に代入可能なのでtrue
        System.out.println(list instanceof List<? extends String> aList5);

        // ArrayList<? extends CharSequence>はArrayList<String>の上位型（「19-3-3
        // 型引数のワイルドカード」参照）なのでtrue
        // List<? extends CharSequence>でもtrue
        System.out.println(list instanceof ArrayList<? extends CharSequence> aList6);

        // 同上。ArrayList<?>の記述も可能
        System.out.println(list instanceof ArrayList<? extends Object> aList7);
    }

    static void useInstanceOfDownCast(Object obj) {
        if (obj instanceof String s) {
            System.out.println(s + " is String");

        } else {
            System.out.println(obj + " is not String");
        }
    }

    static void sealedInterfaceInstanceOf() {
        // シールインターフェースの使用例
        Stream<Result> results = Stream.of(new Result.Success(), new Result.Success(), new Result.Failure());

        // ストリーム中のResult.Success型の要素をカウント
        long countSuccess = results.filter((Result result) -> {
            return result instanceof Result.Success;
        }).count();
        System.out.println(countSuccess);

        // switch構文で書き換えた例
        long countSuccessSwitch = results.filter(result -> {
            return switch (result) {
                case Result.Success suc -> true;
                case Result.Failure fal -> false;
            };
        }).count();
    }

    public static void main(String... args) {
        sealedInterfaceInstanceOf();
    }
}

// シールインターフェース
sealed interface Result {
    // シールインターフェースの派生型
    record Success() implements Result {
    }

    record Failure() implements Result {
    }
}
