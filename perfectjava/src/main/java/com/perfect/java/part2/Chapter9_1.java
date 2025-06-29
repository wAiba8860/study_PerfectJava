package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter9_1 {

    static void methodVarError() {
        // メソッド参照を変数methに代入したいがコンパイルエラー
        // My::methodの意味は本文で説明します
        // 意図はMyクラスのmethodという名前のメソッドの参照の取得です
        // var meth = My::method;
    }

    static void methodRunnable() {
        // 代入
        Runnable meth = My::method;
        // 参照を持つ変数を通じてメソッド呼び出し
        meth.run();
    }

    static void comparatorSort() {
        // sortメソッドは引数の配列を変更するので可変リストを使用
        var list = new ArrayList<>(List.of("abc", "xyz", "za", "defghi"));

        // 下記第二引数のMyComparator::myCompareがメソッド参照
        Collections.sort(list, MyComparator::myCompare);
        System.out.println(list);
    }

    static void consumerMethod() {
        // 使用例
        Consumer<String> meth = My::method;
        meth.accept("abc");
    }

    static void runnableMethod() {
        // 使用例
        var my = new My();
        Runnable meth = my::myMethod;
        meth.run();

        Consumer<My> meth2 = My::myMethod;
        meth2.accept(new My());
    }

    static void biConsumer() {
        // 使用例
        BiConsumer<My, String> meth = My::myMethod;
        meth.accept(new My(), "abc");
    }

    static void constructorInstance() {
        // 使用例
        Supplier<My> cons = My::new;
        My my = cons.get();
    }

    static void bookUtils() {
        Function<Book, String> func1 = BookUtils::getTitle;

        Function<Book, String> func2 = (Book book) -> {
            return book.title();
        };

        // 型を省略
        Function<Book, String> func3 = book -> book.title();

        // 使用例
        // 内部的には下記がgetTitleを呼び出す
        String title = func1.apply(new Book("Peopleware"));
        System.out.println(title);

        // ラムダ式
        String title2 = func2.apply(new Book("PeopleWare"));
        System.out.println(title2);
    }

    void lambdaError() {
        // 変数の型：引数がString、返り値がString
        // ラムダ式の型：引数がBook、返り値がString
        // Lambda expression's parameter book is expected to be of type StringJava(553648785)

        // Function<String, String> func = (Book book) -> {
        // return book.title();
        // };
    }

    static void lambdaExample() {
        Consumer<String> consumer = s -> {
            System.out.println("引数は: " + s);
        };

        // 使用例
        consumer.accept("foo");
    }

    static void lambdaScope() {
        // ラムダ式のパラメータ変数sおよび式内で宣言したローカル変数nは、ラムダ式に閉じたスコープ
        Consumer<String> consumer1 = s -> {
            int n = 1;
            System.out.println(s + n);
        };
        Consumer<String> consumer2 = s -> {
            int n = 2;
            System.out.println(s + n);
        };

        // ここはラムダ式から見えないスコープ
        int n = 0;
        String s = "";

        consumer1.accept("foo");
        consumer2.accept("bar");
    }

    public static void main(String[] args) {
        lambdaScope();
    }
}


class My {
    static void method() {
        System.out.println("メソッド呼び出し");
    }

    static void method(String arg) {
        System.out.println("引数ありクラスメソッド呼び出し" + arg);
    }

    void myMethod() {
        System.out.println("インスタンスメソッド呼び出し");
    }

    void myMethod(String arg) {
        System.out.println("引数ありインスタンスメソッド呼び出し: " + arg);
    }

    My() {
        System.out.println("コンストラクタの呼び出し");
    }
}


class MyComparator {
    static int myCompare(String s1, String s2) {
        return s2.length() - s1.length();
    }
}


class Adder {
    private int score = 0;

    int addScore(int delta) {
        // フィールド変数の更新は処理の外側に影響を与えている
        this.score += delta;
        return this.score;
    }
}


record Book(String title) {
}


class BookUtils {
    // 引数にBookレコードを受取title文字列を返す
    static String getTitle(Book book) {
        return book.title();
    }
}
