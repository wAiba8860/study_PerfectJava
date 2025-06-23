package com.perfect.java.part2.ClassFile;

// レコードクラスをトップレベルで宣言
public record RecordBook(String title, String author, int publishedYear) {
}


// レコードクラス宣言に対応するクラス宣言の疑似コード
final class RecordBookExample {
    // コンポネントフィールド
    private final String title;
    private final String author;
    private final int publishedYear;

    // コンストラクタ
    RecordBookExample(String title, String author, int publishedYear) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    // アクセサメソッド
    public String title() {
        return this.title;
    }

    public String author() {
        return this.author;
    }

    public int publishedYear() {
        return this.publishedYear;
    }

    // public final String toString() {/* 本体は省略 */}

    // public final int hashCode() {/* 本体は省略 */}

    // public final boolean equals(Object anObject) {/* 本体は省略 */}
}
