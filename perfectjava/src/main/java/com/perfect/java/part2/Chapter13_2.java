package com.perfect.java.part2;

import java.time.LocalDateTime;
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

    static void nullDefaultSwitch(String s) {
        switch (s) {
            case "" -> {
                System.out.println("空文字列");
            }
            case null, default -> {
                System.out.println("nullもじくは非空文字列");
            }
        }
    }

    static void comprehensivenessEnumSwitch() {
        enum Status {
            SUCCESS, FAILURE,
        }
        var status = Status.SUCCESS;

        // 下記にFAILUREチェックはないがコンパイルエラーにならない
        switch (status) {
            case SUCCESS -> {
                System.out.println("SUCCESS");
            }
            case FAILURE -> {
                System.out.println("FAILURE");
            }
            case null -> {

            }
        }
    }

    void omission() {
        int i = 0;
        switch (i) {
            case 1, 2, 3, 4, 5 -> System.out.println("1から5");
            default -> throw new IllegalArgumentException("1から5の範囲外");
        }
    }

    static void blockSwitchExample(int i) {
        switch (i) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            default:
                System.out.println("default");
                break; // このbreak文はなくても同じだが、対称性のために記述します
        }
    }

    static void fallThroughSwitch(int i) {
        switch (i) {
            case 0:
                System.out.println("0もしくは1の場合の共通処理");
                // fall through
            case 1:
                System.out.println("1の場合のみの共通処理");
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    static String switchExpression(int i, boolean flag) {
        return switch (i) {
            case 0 -> "ゼロ"; // 式のみ記述
            case 1 -> { // ブロック文の記述。yield文またはthrow文のいずれかで終える
                yield "イチ";
            }

            case 2, 3, 4, 5 -> {
                if (flag) { // 全ての実行パスにyield文またはthrow文のいずれかが必要
                    yield "2から5";
                } else {
                    yield "2と3と4と5";
                }
            }
            case -1 -> throw new IllegalArgumentException("マイナスイチ"); // throw文
            default -> "その他";
        };
    }

    static void typeComparisonSwitch(Object obj) {
        switch (obj) {
            case String s when s.isEmpty() -> {
                System.out.println("objは空のString");
            }
            case String s -> {
                System.out.println("objの型はString。値は" + s);
            }
            case Integer i -> {
                System.out.println("objの型はInteger。値は" + i);
            }
            case null, default -> {
                System.out.println("objはnullまたはobjの型はその他");
            }
        }
    }

    static void typeComparisonIf(Object obj) {
        if (obj instanceof String s) {
            System.out.println("objの型はString。値は" + s);
        } else if (obj instanceof Integer i) {
            System.out.println("objの型はInteger。値は" + i);
        } else {
            System.out.println("objの型はその他");
        }
    }

    static void typeComparisonSwitchError(Object obj) {
        switch (obj) {
            case Object o -> {
                System.out.println("objの型はObject");
            }
            // 上記のObjectと型比較で一致すると下記のStringと型比較の一致が原理上ないため
            // This case label is dominated by one of the preceding case labelsJava(2099058)
            // case String s -> {
            // System.out.println("objの型はString。値は" + s);
            // }
        }
    }

    static void guardSwitchException(Object obj) {
        switch (obj) {
            case String s when s.charAt(0) == ' ' -> {
                System.out.println("objは先頭が空白の文字列");
            }
            case null, default -> {
                System.out.println("その他");
            }
        }
    }

    static void sealedRecordSwitch(Result2 result) {

        switch (result) {
            case Result2.Success(String output) when output.isEmpty() -> {
                System.out.println("空文字列");
            }

            // case Result2.Success(String output) -> {
            // varも使える
            case Result2.Success(var output) -> {
                System.out.println(output);
            }
            case Result2.Failure fail -> {
                System.out.println("failure case");
            }
            case null, default -> {
                System.out.println("null case");
            }
        }
    }

    static void nestRecordSwitch(Result3 result) {
        switch (result) {
            case Result3.Success(Output(String message1), Output(String message2)) -> {
                System.out.println("message1は" + message1);
                System.out.println("message2は" + message2);
            }
            case Result3.Failure fail -> {
                System.out.println("failure case");
            }
            case null -> {
                System.out.println("null case");
            }
        }
    }

    static void genericRecordSwitch(Result4 result) {
        switch (result) {
            case Result4.Success(String key, String value) -> {
                System.out.println("Keyは%s,valueは%s".formatted(key, value));
            }
            case Result4.Success(String key, Integer value) -> {
                System.out.println("Keyは%s,valueは%d".formatted(key, value));
            }
            case Result4.Success(Object key, Object value) -> {
                System.out.println("KeyObjectは%s,valueObjectは%s".formatted(key, value));
            }
            case Result4.Failure fail -> {
                System.out.println("failure case");
            }
            case null -> {
                System.out.println("null case");
            }
        }
    }

    static void genericRecordBasic() {
        // 上記コードの使用例
        genericRecordSwitch(new Result4.Success<String, String>("key1", "value1"));
        genericRecordSwitch(new Result4.Success<String, Integer>("key2", 123));
        genericRecordSwitch(new Result4.Success<String, LocalDateTime>("key2", LocalDateTime.now()));
    }

    public static void main(String[] args) {
        genericRecordBasic();
    }
}

// シールインターフェースと派生レコードクラス
sealed interface Result2 {
    record Success(String output) implements Result2 {
    }

    record Failure() implements Result2 {
    }
}

sealed interface Result3 {
    record Success(Output output1, Output output2) implements Result3 {
    }

    record Failure() implements Result3 {
    }
}

record Output(String message) {
}

sealed interface Result4<K, V> {
    record Success<K, V>(K outputKey, V outputValue) implements Result4<K, V> {
    }

    record Failure<K, V>() implements Result4<K, V> {
    }
}
