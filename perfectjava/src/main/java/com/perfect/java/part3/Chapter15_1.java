package com.perfect.java.part3;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Chapter15_1 {

    static void charExample() {
        char c = 'a';
        int value = c - 1;
        System.out.println(value);

        boolean result = '0' == 0x30;
        System.out.println(result);
    }

    // 文字から数値への変換例（'0'から0へ、などの変換）
    static void characterToDigitExample() {
        // 10進数として'1'を扱い、変換値は1になる
        int n1 = Character.digit('1', 10);
        System.out.println(n1);

        // 16進数として'f'を扱い、変換値は15になる
        int n2 = Character.digit('f', 16);
        System.out.println(n2);

        // 16進数として'F'を扱い、変換値は15になる
        // 大文字と小文字、どちらの文字リテラルも変換可能
        int n3 = Character.digit('F', 16);
        System.out.println(n3);

        // 変換できない値の場合、変換値は-1になる
        int n4 = Character.digit('f', 10);
        System.out.println(n4);

        // 36進数として扱う場合、'0'から'9'および'a'から'z'までを変換可能
        int n5 = Character.digit('z', 36);
        System.out.println(n5);

        // 範囲外の基数指定は-1になる
        int n6 = Character.digit('1', 37);
        System.out.println(n6);
    }

    // 数値から文字への変換例
    static void characterForDigit() {
        // 変換値'0'。値は0x30
        char c1 = Character.forDigit(0, 10);
        System.out.println(c1);

        // 変換値'1'。値は0x31
        char c2 = Character.forDigit(1, 10);
        System.out.println(c2);

        // 変換値は'a'。値は0x61
        char c3 = Character.forDigit(10, 16);
        System.out.println(c3);

        // 変換できない場合、変換値は0x0（整数のゼロ）
        char c4 = Character.forDigit(10, 10);
        System.out.println(c4);

        // 36進数変換
        char c5 = Character.forDigit(35, 36);
        System.out.println(c5);

        // 範囲外の基数指定の場合、変換値は0x0（整数のゼロ）
        char c6 = Character.forDigit(10, 37);
        System.out.println(c6);
    }

    static void stringCharAtExample() {
        // Stringオブジェクトから文字の取り出し
        var s = "abc";
        char c1 = s.charAt(0); // 引数はインデックス値
        System.out.println(c1);

        // 日本語の文字も同じ扱いです。
        char c2 = "あい".charAt(0);
        System.out.println(c2);
    }

    static void stringToCharArray() {
        // Stringからcharの配列に変換
        char[] arr = "abcあい".toCharArray();
        for (var c : arr) {
            System.out.println(c);
        }
    }

    static void stringGetChars() {
        var s = "abc";

        // 必要な長さを確保した配列
        char arr[] = new char[s.length()];

        // 引数の意味はAPIドキュメントを参照
        s.getChars(0, arr.length, arr, 0);
        System.out.println(arr);
    }

    static void stringToIntStream() {
        int[] arr = "abc".chars().toArray();
        for (var i : arr) {
            System.out.println(i);
        }

        // BMP以外の文字を扱うにはコードポイントを使います
        int[] arr2 = "abc".codePoints().toArray();
        for (var i : arr2) {
            System.out.println(i);
        }
    }

    static void stringBuilderGetChars() {
        var sb = new StringBuilder("abc");

        // 必要な長さを確保した配列を事前準備
        char arr[] = new char[sb.length()];

        // getCharsメソッド呼び出し
        sb.getChars(0, arr.length, arr, 0);
        for (var c : arr) {
            System.out.println(c);
        }

        String s = sb.toString();
        for (var c : s.toCharArray()) {
            System.out.println(c);
        }
    }

    // char配列からStringオブジェクトを生成
    static void charArrayToString() {
        char[] arr = { 'a', 'b', 'c' };
        var s = new String(arr);
        System.out.println(s);

        // 上記コードを一行で書く例
        System.out.println(new String(new char[] { 'a', 'b', 'c' }));

        // 文字の配列の部分文字列の指定も可能
        // 第２引数がoffset、第３引数が文字数
        var s2 = new String(arr, 1, 2);
        System.out.println(s2);
    }

    // int配列からStringオブジェクト生成
    static void intArrayToString() {
        int[] arr = { 'a', 'b', 'c' };

        // 第２引数がoffset、第３引数が文字数
        var s = new String(arr, 0, 3);
        System.out.println(s);
    }

    static void charArrayToStringBuilder() {
        char[] arr = { 'a', 'b', 'c' };
        var sb = new StringBuilder();
        sb.append(arr);
        System.out.println(sb);
    }

    static void variableLengthByte() {
        // 書き足すバイト列
        byte[] b1 = { 'a', 'b', 'c' };
        byte[] b2 = { 'd', 'e', 'f' };

        // 可変長バイト列生成
        var bos = new ByteArrayOutputStream();

        // 可変長バイト列に追記
        bos.write(b1, 0, b1.length);
        bos.write(b2, 0, b2.length);

        // 可変長バイト列をbyte型配列に変換
        byte[] buf = bos.toByteArray();
        for (var b : buf) {
            System.out.println(b);
        }

        // 可変長バイト列を文字列に変換（UTF-8の文字列）
        System.out.println(bos.toString());
    }

    // byte配列からStringオブジェクト生成
    static void byteArrayToString() {
        // 英数字のバイト列
        byte[] bytes = new byte[] { 0x61, 0x62, 0x63 };

        // 文字コード指定なしの場合、デフォルトでUTF-8
        var s = new String(bytes);
        System.out.println(s);

        // ひらがなのバイト列（このバイト列の求め方は下記メソッドを参照）
        byte[] bytes2 = new byte[] { (byte) 0xe3, (byte) 0x81, (byte) 0x82, (byte) 0xe3, (byte) 0x81, (byte) 0x84 };
        var s2 = new String(bytes2);
        System.out.println(s2);

        // UTF-8の明示的指定（デフォルトなので指定不要）
        var s3 = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s3);
    }

    // Stringオブジェクトからbyte配列に変換
    static void stringToByteArray() {
        // UTF-8のバイト列に変換
        byte[] bytes = "あい".getBytes();
        for (var b : bytes) {
            System.out.println(b);
        }

        // 下記で8ビット符号なしかつ16進数の表示ができます
        for (byte b : bytes) {
            System.out.printf("%02x,", b);
        }

        // UTF-8の明示的指定（デフォルトなので指定不要）
        // 古いJavaでは引数なしgetBytesがOSの言語設定依存だったので必要でした
        try {
            byte[] bytes2 = "あい".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void main(String... args) {
        stringToByteArray();
    }
}
