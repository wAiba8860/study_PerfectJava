package com.perfect.java.part2;

public class Chapter4_1 {

    public static void stringReference() {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append("012"));
    }

    public static void stringToStringBuilderError() {
        // 変数の型（String型）とオブジェクトの型（StringBuilder型）が一致しないのでコンパイルエラー
        String s;
        // s = new StringBuilder();

        // 変数の型とオブジェクトの型の規則２の例
        StringBuilder sb = new StringBuilder("");
        sb.length();
        // sb.size(); //コンパイルエラー。StringBuilderクラスにsizeメソッドはないから

        // 基底型変数に派生型のオブジェクトの参照を代入できる例
        CharSequence cs = new StringBuilder();
    }

    public static void notRecommended() {
        class My {
            void method() {
                String My; // クラス名と一致しているがコンパイルできる
                String method; // メソッド名と一致しているがコンパイルできる
                String var;// 変数名varはコンパイルできる
            }
        }
    }

    public static void stringFinal() {
        final var sb1 = new StringBuilder("012");
        // sb1 = new StringBuilder("345"); //再代入でコンパイルエラー

        // 特にエラーにはならず、参照先のStringBuilderオブジェクトの文字列を変更できる
        final var sb2 = new StringBuilder("012");
        sb2.append("345");
    }

    public static void stringAssignment() {
        // 同じオブジェクトを参照する２つの変数の例
        var sb = new StringBuilder();
        var sb2 = sb;

        // 同じオブジェクトを参照する２つの変数への操作
        sb.append("012");
        sb2.append("345");

        // 下記のどちらも012345を出力する
        System.out.println(sb);
        System.out.println(sb2);
    }

    static void print(StringBuilder param) {
        System.out.println(param);
    }

    static void printAndUpdate(StringBuilder param) {
        System.out.println(param);
        param.append("345");

        var sb = new StringBuilder("012");
        printAndUpdate(sb);

        // printAndUpdate内のparam.append呼び出しの結果が見える
        System.out.println(sb);
    }

    public static void stringNullPointer() {
        try {
            StringBuilder sb = null;
            sb.append("012");
        } catch (NullPointerException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void stringNullCheck() {
        class My {
            void method(String s) {
                if (s != null) { // 変数sの非nullチェック
                    // sがnullだとNullPointerException例外が起きるコード
                    System.out.println(s.length());
                }
            }
        }
    }

    public static void stringMethodCallExample() {
        System.out.println(new StringBuilder("012").length());
        System.out.println("abc".length());
    }

    public static void stringMethodChain() {
        var sb = new StringBuilder();
        int len = sb.append("012").append("345").length();
        System.out.println(len);

        // 変数sbを使う予定がない場合、上記２行を次のように書いても同じです。
        int len2 = new StringBuilder().append("012").append("345").length();
        System.out.println(len2);
    }

    public static void main(String... args) {
        stringMethodChain();
    }
}
