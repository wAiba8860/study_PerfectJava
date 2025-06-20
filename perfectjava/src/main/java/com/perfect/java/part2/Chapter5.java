package com.perfect.java.part2;

public class Chapter5 {

    static void IntegerExample() {
        // 基本型変数nに変数10を代入
        int n = 10;
        System.out.println(n);

        // 変数nの値に2を掛けた値を変数mに代入
        int m = n * 2;
        System.out.println(m);
    }

    static void overflowDigits() {
        int n = Integer.MAX_VALUE; // Integerクラス
        System.out.println(n);

        int m = n + 1;
        System.out.println(m);

        // 範囲を超えた数値（コンパイルエラー）
        // int n2 = 2147483648; The literal 2147483648 of type int is out of range
        // int n3 = 2147483648L; Type mismatch: cannot convert from long to int
    }

    static void IntegerUnderscore() {
        int n = 2_147_483_647;
        System.out.println(n);

        // long n2 = 2_147_483_648; //int型リテラル値の型の範囲を超える値（コンパイルエラー）
        long n2 = 2_147_483_648L;
        System.out.println(n2);
    }

    static void IntegerConversion() {
        byte nb = 123;
        short ns = 123;
        char nc = 123;
        System.out.println(nb);
        System.out.println(ns);
        System.out.println(nc);
    }

    static void validIntegerLiteral() {
        int n;
        n = 255; // 10進数
        n = 0xff;// 16進数
        n = -0xff;// 16進数の負数
        n = 0xFF;// aからfまでの文字は大文字でも良い（小文字のほうが一般的）
        n = 0Xff;// 0xの代わりに0Xでも良い（0xの方が一般的）
        n = 0377;// 8進数
        n = -0377;// 8進数の負数
        n = 0b1111111;// 2進数
        n = 0B1111111;// 0bの代わりに0Bでも良い（0bの方が一般的）
        n = 0b1111_1111;// _（アンダースコア文字）は2進数で特に有効

        // コンパイルエラーになる例
        // n = 0b102; //2進数リテラルで0もしくは1以外は不可
        // n = 08; //8進数リテラルで、8もしくは9は不可
        // n = 0b1111_1111_; //最後のアンダースコア文字は不可
        // n = 0b_1111_1111; //最初のアンダースコア文字は不可
    }

    static void overflowingDigitsExample() {
        int n = 2147483647;
        System.out.println(n * 2);

        int m = -2147483648;
        System.out.println(m - 1);

        System.out.println(m * -1);

        System.out.println(m / -1);
    }

    static void signInversion() {
        // 正常な符号反転
        int n = 10;
        System.out.println(-n);

        // 異常な符号反転
        int m = -2147483648;
        System.out.println(-m);
    }

    static void increment() {
        int n = 0;

        // nは1になる。n= n+ 1 や n += 1 と同じ効果
        n++; // ++nの記述も可能
        System.out.println(n);
    }

    static void errorConversion() {
        // int iを仮定
        int i = 0;
        // String s = i; //整数値を文字列型変数に代入するとコンパイルエラー
        // String s = 0; //整数リテラル値を文字列型変数に代入するとコンパイルエラー

        // String sを仮定
        // int i = s; //文字列オブジェクト（の参照）を整数型変数に代入するとコンパイルエラー
        // int i = "abc"; //文字列リテラル値を整数型変数に代入するとコンパイルエラー

        // 有効な代入（拡大変換）
        long ll = i;

        // コンパイルエラーになる代入（縮小変換）
        // short si = i;

        // キャストによりコンパイルがとおるコード（縮小変換）
        short si = (short) i;

        // 変換によるビット落ち
        short si1 = (short) 65536;
        short si2 = (short) 65537;
        System.out.println(si1);
        System.out.println(si2);

        // キャストで整数の符号が反転する例
        int i2 = 32768;
        short si3 = (short) i2;
        System.out.println(si3);
    }

    static void signPreservation() {
        // shortからintへの拡大変換
        short si = -1;
        int i = si;
        System.out.println(i);

        // intからshortへの縮小変換
        int n = -1;
        short si2 = (short) n;
        System.out.println(si2);

        // 負数をchar型へ型変換して符号反転する例
        byte b = -128;
        char c = (char) b;
        System.out.println(c);
        System.out.println((int) c);
    }

    static void calculationPromotion() {
        short s1 = 1;
        short s2 = 2;
        // short sum = s1 + s2; // 代入の右辺がint型。コンパイルエラーを取り除くには、short型へのキャストが必要。

        // 昇格により正しい値を得られる例
        char c = 65535;
        int i = c + 1;
        System.out.println((int) i);

        byte b = 127;
        int ib = b + 1;
        System.out.println((int) ib);
    }

    static void longCompilationError() {
        // 全てコンパイルエラー。エラー回避にはキャストが必要
        // byte b = 127L;
        // short si = 127L;
        // char c = 127L;
        // int i = 127L;
    }

    static void methodParameterError() {
        class My {
            void method(char c) {
                System.out.println("コンパイル");
            }
        }
        // コンパイルエラーになる呼び出しコード
        var m = new My();
        // m.method(1);

        // コンパイルエラーを回避した呼び出しコード
        m.method((char) 1);
    }

    static void numberToString() {
        var s = String.valueOf(255);
        System.out.println(s);
    }

    static void IntegerToString() {
        // 数値255を基数16（16進数）で文字列に変換
        // 表5.7のInteger.toHexStringメソッドも使用可能
        var sh = Integer.toString(255, 16);
        System.out.println(sh);

        // 数値255を基数2（2進数）で文字列に変換
        // 表5.7のInteger.BinaryStringメソッドも使用可能
        var sb = Integer.toString(255, 2);
        System.out.println(sb);

        // 基数10（10進数）の文字列変換も可能
        var s = Integer.toString(255, 10);
        System.out.println(s);

        // 数値35を基数36（36進数）で文字列に変換
        var s36 = Integer.toString(35, 36);
        System.out.println(s36);

        // 負数も文字列変換可能
        var sm = Integer.toString(-255, 16);
        System.out.println(sm);
    }

    static void conversionFormatted() {
        // 書式処理を扱う数値から文字列への変換例
        var s = "0x%08x".formatted(255);
        System.out.println(s);
    }

    static void internalRepresentation() {
        var sh = Integer.toHexString(-1);
        System.out.println(sh);

        var sb = Integer.toBinaryString(-1);
        System.out.println(sb);
    }

    static void stringToNumber() {
        int i = Integer.parseInt("255");// 基数を省略（10進数表記とみなして変換）
        System.out.println(i);

        int ih = Integer.parseInt("ff", 16); // 基数16（16進数表記と見なして変換）
        System.out.println(ih);

        int i36 = Integer.parseInt("z", 36); // 基数36（36進数表記と見なして変換）
        System.out.println(i36);

        int iER = Integer.parseInt("z", 37); // 範囲外の基数指定はNumberFormatException例外発生
        System.out.println(iER);
    }

    static void numberFormatExceptionExample() {
        // すべてNumberFormatException例外発生
        byte b = Byte.parseByte("128");

        Integer.parseInt("ff");
        Integer.parseInt("0.1");
        Integer.parseInt("0xf", 16);
        Integer.parseInt(null);
    }


    // ブーリアン型変数を使う冗長なコード例
    // 文字列haystack内に文字列needleが含まれていれば真を返す
    boolean contains1(String haystack, String needle) {
        boolean found; // ブーリアン型変数
        if (haystack.indexOf(needle) >= 0) {
            found = true;
        } else {
            found = false;
        }
        return found;
    }

    boolean contains2(String haystack, String needle) {
        if (haystack.indexOf(needle) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    static boolean contains3(String haystack, String needle) {
        return haystack.indexOf(needle) >= 0;
    }

    // containsメソッドを呼ぶ例
    static void callContains() {
        if (contains3("foo bar", "bar")) {
            System.out.println("contains");
        }
    }

    static void booleanEquals() {
        // 偽の判定コード（パターン1）
        if ("foo bar".indexOf("bar") < 0) {
            System.out.println("not contains");
        }

        // 偽の判定コード（パターン2）
        if (!("foo bar".indexOf("bar") >= 0)) {
            System.out.println("not contains");
        }
        // 2つは同じ条件判定
    }

    static void deMorganLaws() {
        // 論理演算式のある例
        // char cを仮定
        char c = 0;
        if (!Character.isDigit(c) && !Character.isLetter(c)) {
            System.out.println("any symbol?");
        }

        // ド・モルガンの法則を使って書き換え
        if (!(Character.isDigit(c) || Character.isLetter(c))) {
            System.out.println("any symbol?");
        }

        // 否定演算子をなくす書き換え
        if (Character.isDigit(c) || Character.isLetter(c)) {
            ; // through
        } else {
            System.out.println("any symbol");
        }
    }


    public static void main(String... args) {
        numberFormatExceptionExample();
    }
}
