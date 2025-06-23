package com.perfect.java.part2;

import com.perfect.java.part2.ClassFile.RecordBook;

public class Chapter7_1 {

    // Bookレコードクラスの宣言
    record BookRecord(String title, String author, int publishedYear) {

    }

    static void bookRecordExample() {
        // Bookレコードの生成
        var book = new BookRecord("Peopleware", "Tom Demarco", 1999);

        // Bookレコードの使用
        System.out.println(book.title());
        System.out.println(book.author());
        System.out.println(book.publishedYear());
        System.out.println(book.toString()); // toString呼び出しは省略可能

        System.out.println(book.title);
    }

    static void useRecodeBasic(RecordBook book) { // RecordBook.javaから使用
        System.out.println(book.title());
    }

    static void recordError() {
        // var book1 = new BookRecord("Peopleware", "Tom Demarco"); // 実引数不足
        // var book2 = new BookRecord("Peopleware", "Tom Demarco", "1999"); // 実引数の型違い
        // var book3 = new BookRecord("Peopleware", 1999, "Tom"); // 実引数の順序違い
        // var book4 = new BookRecord("Peopleware", "Tom", 1999, 1999); // 実引数過剰
    }

    static void variableLengthComponents() {
        var book = new BookComments("Peopleware", "Excellent", "Great");

        // 内部的に可変長コンポーネント列のコンポーネント値は配列
        System.out.println(book.comments().getClass().isArray());

        System.out.println(book.comments()[0]);
        System.out.println(book.comments()[1]);

        // 配列型コンポーネントの同値比較は偽
        var book2 = new BookComments("Peopleware", "Excellent", "Great");
        boolean result = book.equals(book2);
        System.out.println(result);
    }

    public static void main(String[] args) {
        variableLengthComponents();
    }
}


// レコードの可変長コンポーネント列
record BookComments(String title, String... comments) {
}
