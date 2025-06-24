package com.perfect.java.part2;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import com.perfect.java.part2.ClassFile.RecordBook;

public class Chapter7_1 {

    // Bookレコードクラスの宣言
    record BookRecord(String title, String author, int publishedYear) {
        // 全てのコンポーネントを引数に受け取るコンストラクタ（＝標準コンスタラクタの上書き）
        BookRecord(String title, String author, int publishedYear) {
            Objects.requireNonNull(title);
            Objects.requireNonNull(author);
            if (publishedYear < 0) {
                throw new IllegalArgumentException("Invalid publishYear: %d".formatted(publishedYear));
            }
            this.title = title;
            this.author = author;
            this.publishedYear = publishedYear;
        }

        // コンパクトコンストラクタ
        // BookRecord {
        // Objects.requireNonNull(title);
        // Objects.requireNonNull(author);
        // if (publishedYear < 0) {
        // throw new IllegalArgumentException("Invalid publishedYear:
        // %d".formatted(publishedYear));
        // }
        // }
    }

    record BookRecord2(String title, String author, int publishedYear) {
        BookRecord2(String title) {
            // this呼び出しで標準コンストラクタ呼び出し
            // authorとpublishedYearの2つのコンポーネントにデフォルト値を使用
            this(title, "unknown", 2000);

            // this呼び出し後にもコード記述は可能。通常、書く必要はない
        }
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

    static void recordEquals() {
        var book1 = new BookRecord("abc", "ABC", 2000);
        var book2 = new BookRecord("abc", "ABC", 2000);
        var book3 = new BookRecord("abc", "ABC", 2001);

        // book1レコードとbook2レコードは同値
        System.out.println(book1.equals(book2));

        // 同値のレコードであれば順序を変えても同値
        System.out.println(book2.equals(book1));

        // book1レコードとbook3レコードは同値ではない
        System.out.println(book1.equals(book3));

        // ==演算は参照先のレコードの内容比較ではなく、同一のレコードの実体かを比較
        System.out.println(book1 == book2);
    }

    // レコードのequalsメソッドが偽になる例
    record MyRecord(StringBuilder sb) {
    }

    static void stringBuilderEquals() {
        var record1 = new MyRecord(new StringBuilder("abc"));
        var record2 = new MyRecord(new StringBuilder("abc"));

        System.out.println(record1.equals(record2));
    }

    static void recordUserBasic() {
        // 使用例
        var user = new User("KH", LocalDate.of(1987, 2, 2));
        System.out.println(user.age());

        var user2 = new User("rms", LocalDate.of(1953, 3, 16));
        System.out.println(User.calcAge(user2));
    }

    static void recordDefaultConstruct() {
        // 使用例
        // titleコンポーネントだけでレコード生成可能
        var book = new BookRecord2("Java Book");
        System.out.println(book);

        // 従来どおり、すべてのコンポーネントを渡すレコード生成も可能
        var book2 = new BookRecord2("Peopleware", "Tom Demarco", 1999);
        System.out.println(book2);
    }

    public static void main(String[] args) {
        recordDefaultConstruct();
    }
}

// レコードの可変長コンポーネント列
record BookComments(String title, String... comments) {
}

// レコードクラスにメソッドを追加
record User(String name, LocalDate birthDate) {
    int age() {
        LocalDate today = LocalDate.now();
        return Period.between(this.birthDate, today).getYears();
    }

    static int calcAge(User user) {
        LocalDate today = LocalDate.now();
        return Period.between(user.birthDate, today).getYears();
    }
}
