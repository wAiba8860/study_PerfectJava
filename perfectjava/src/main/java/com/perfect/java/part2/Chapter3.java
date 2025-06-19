package com.perfect.java.part2;

public class Chapter3 {
    public static void main(String[] args) {
        textBlockWhiteSpace();
    }

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

        var sJo = "0123" +
                "456";
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
}
