package com.perfect.java.part2;

import java.util.StringJoiner;

public class Chapter3 {

    public static void stringExample() {
        var s = "0123456789";
        var t = "123456789";
        System.out.println(s.charAt(2));
        System.out.println(s.chars());
        System.out.println(s.codePointAt(2));
        System.out.println(s.compareTo(t));
        System.out.println(s.contains("0"));
        System.out.println(s.endsWith("9"));
        System.out.println(s.equals(t));
        System.out.println(s.substring(0, 2));
    }

    public static void stringIndexOf() {
        // 文字列の中から["と"]の間に囲まれた文字列を取り出す
        // 対象の文字列
        var s = "012[abc]345";

        // "["の位置を探す
        var begin = s.indexOf("[");

        // "]"の位置を探す
        var end = s.indexOf("]");

        // "["と"]"の間の部分文字列を取り出す
        var sub = s.substring(begin + 1, end);
        System.out.println(sub);
    }

    public static void stringLength() {
        var s1 = "0123456789";
        var len1 = s1.length();
        System.out.println(len1);

        var s2 = "0123456789あいうえお";
        var len2 = s2.length();
        System.out.println(len2);

        var s3 = "";
        var len3 = s3.length();
        System.out.println(len3);

        System.out.println(s3.isEmpty());
    }

    public static void stringFormatted() {
        var unit = "円";
        var n = 100;
        var m = 200;

        // %dは数値に対する書式子。%sは文字列に対する書式子
        var s = "%d%sと%d%sの合計は%d%sです".formatted(n, unit, m, unit, n + m, unit);
        System.out.println(s);

        // テキストブロックでも利用可能
        var sBlock = """
                %d%sと%d%sの
                合計は
                %d%sです""".formatted(n, unit, m, unit, n + m, unit);
        System.out.println(sBlock);
    }

    public static void stringIndexOutOfBounds() {
        var s = "012[abc"; // ]を含まない対象文字列
        var begin = s.indexOf("[");

        var end = s.indexOf("]");
        System.out.println(end);
        System.out.println(s.substring(begin + 1, end));

    }

    public static void stringEscape() {
        var s = "0123456789";
        System.out.println(s);

        var sES = "\"0123456789\"";
        System.out.println(sES);
        System.out.println(sES.length());

        var sBs = "\\0123456789";
        System.out.println(sBs);

        System.out.println(s.getClass());

        var sn = "012\n345";
        System.out.println(sn);

        var sJo = "0123" + "456";
        System.out.println(sJo);
    }

    public static void textBlock() {
        var s = """
                abc
                def
                ghi""";
        System.out.println(s);

        // 文字列の中身が一致
        var result = s.equals("abc\ndef\nghi");
        System.out.println(result);
    }

    public static void textBlockIndent() {
        var s1 = """
                abc
                def
                ghi""";
        System.out.println(s1);

        var s2 = """
                    ABC
                DEF
                    GHI""";
        System.out.println(s2);
    }

    public static void textBlockOneRow() {
        var s = """
                abc\
                def\
                ghi""";
        System.out.println(s);
    }

    public static void textBlockEscape() {
        var s = """
                ab\nc
                "de\"f
                ghi""";
        System.out.println(s);
    }

    public static void textBlockWhiteSpace() {
        // 普通に書くと終端の空白文字は無効
        var s = """
                abc     """;
        System.out.println(s);
        System.out.println(s.length());

        // 空白文字のエスケープ処理
        var s1 = """
                abc\s\s\s""";
        System.out.println(s1);
        System.out.println(s1.length());
    }

    public static void stringJoin() {
        var s1 = "012";
        var s2 = "345";
        System.out.println(s1.concat(s2));
        System.out.println(s1 + s2);
    }

    public static void stringBuilderExample() {
        var sb1 = new StringBuilder("0123456789");
        System.out.println(sb1);

        var sb2 = new StringBuilder("abcghi");
        System.out.println(sb2.insert(3, "def"));

        // StringオブジェクトからStringBuilderオブジェクトに変換
        String s = "abc";
        System.out.println(new StringBuilder(s));

        // StringBuilderオブジェクトからStringオブジェクトに変換
        StringBuilder sb3 = new StringBuilder("abc");
        System.out.println(sb3.toString());
    }

    public static void stringJoinExample() {
        var s1 = "012";
        System.out.println(s1 += "345");

        // String.join()
        var s2 = String.join(",", "abc", "def", "ghi"); // 第一引数は区切り文字
        System.out.println(s2);

        // StringJoinerクラス
        var s3 = new StringJoiner(",", "[", "]");
        s3.add("abc").add("def").add("ghi");
        System.out.println(s3);
    }

    // 非効率になるかもしれない文字列の結合処理
    String concat(String... array) {
        var result = "";
        for (String s : array) {
            result += s; // +=演算で文字列結合
        }
        return result;
    }

    // 上記コードを書き直した例
    String concatCorrect(String... arr) {
        var result = new StringBuilder();
        for (String s : arr) {
            result.append(s);
        }
        return result.toString();
    }

    public static void stringEquals() {
        var s1 = "abc";
        var s2 = new StringBuilder("abc").toString();

        // 文字列の中身が一致していても==演算の結果はfalse（偽）
        System.out.println(s1 == s2);

        // 文字列の中身が一致していればtrue（真）
        System.out.println(s1.equals(s2));
        // 逆も真
        System.out.println(s2.equals(s1));

        var s3 = "abc";
        var s4 = s3;
        // 同一のStringオブジェクトを参照している変数同士の==演算の結果はtrue（真）
        System.out.println(s4 == s3);
    }

    public static void stringCompare() {
        var s1 = "abc";
        var s2 = "abc";
        var s3 = "a" + "b" + "c";

        // 文字列リテラルに限定すると==演算で内容の一致を判定可能
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);

        var s4 = "ab";
        s4 += "c";
        System.out.println(s4);
        // 文字列の中身が一致していても==演算の結果はfalse（偽）
        System.out.println(s1 == s4);

        var sb = new StringBuilder("abc");
        // 文字列の中身が一致していてもequalsの結果はfalse（偽）
        System.out.println(s1.equals(sb));

        // contentEqualsの結果はtrue（真）
        System.out.println(s1.contentEquals(sb));

        // StringBuilder同士の比較
        var sb2 = new StringBuilder("abc");
        System.out.println(sb.toString().equals(sb2.toString()));
    }

    public static void stringCompareTo() {
        System.out.println("abc".compareTo("abc"));
        System.out.println("abc".compareTo("bcd"));
        System.out.println("bcd".compareTo("abc"));
        System.out.println("abc".compareTo("ABC"));
        System.out.println("ABC".compareTo("abc"));
        System.out.println("abc".compareTo("abcd"));

        // CharSequence.compare
        System.out.println(CharSequence.compare("abc", "abc"));
        System.out.println(CharSequence.compare("abc", "bcd"));
        System.out.println(CharSequence.compare("bcd", "abc"));
    }

    public static void stringToString() {
        // StringBuilderオブジェクトに対する暗黙のtoStringメソッド呼び出し
        System.out.println("abc" + (new StringBuilder("def")));

        // 数値もString型変換される
        System.out.println("123" + 456);

        // nullは"null"という文字列に変換されます
        System.out.println("123" + null);
    }

    public static void main(String[] args) {
        stringToString();
    }
}
