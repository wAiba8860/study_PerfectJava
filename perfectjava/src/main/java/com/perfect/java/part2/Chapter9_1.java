package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
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
        // Lambda expression's parameter book is expected to be of type
        // StringJava(553648785)

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

    static void lambdaScopeError() {
        int n = 0;
        String s = "";

        // ラムダ式から外側の変数（ローカル変数のnとs）が見える（詳細は後述）
        // Lambda expression's parameter s cannot redeclare another local variable
        // defined in an enclosing scope. Java(536871009)

        // Consumer<String> consumer1 = s -> {
        // int n = 1;
        // System.out.println(s + n);
        // };
        // Consumer<String> consumer2 = s -> {
        // int n = 2;
        // System.out.println(s + n);
        // };

        // consumer1.accept("foo");
        // consumer2.accept("bar");
    }

    static void lambdaScopeFinal(String mparam) {
        // ラムダ式から見た外側の変数（mparamとlocal）はどちらも実質的final
        // ラムダ式内で使用可能
        // local,mparamに再代入コードがあるとコンパイルエラーになる
        String local = "local";
        Consumer<String> consumer = lparam -> {
            System.out.println(String.join(",", lparam, local, mparam));
        };
        consumer.accept("lparam");
    }

    static void outOfFieldBasic() {
        // 使用例
        var my = new My();
        my.outOfFieldVariable();
    }

    static void returnLambdaBasic() {
        var obj = new My();

        obj.field = "first-field";
        Consumer<String> consumer1 = obj.methodLambda("mparam");
        obj.field = "second-field";
        Consumer<String> consumer2 = obj.methodLambda("mparam123");
        consumer1.accept("lparam");
        consumer2.accept("lparam");
    }

    static void returnLambdaCount() {
        IntSupplier counter = (new My()).makeCounter(1);
        IntSupplier counter100 = (new My()).makeCounter(100);
        System.out.println(counter.getAsInt());
        System.out.println(counter.getAsInt());
        System.out.println(counter100.getAsInt());
        System.out.println(counter100.getAsInt());
    }

    // 関数合成を使わない例
    static void functionCompositionExample() {
        Function<Book, String> bookToTitle = book -> book.title();
        Function<String, String> capitalize = s -> s.toUpperCase();

        // 関数合成で書いた例
        Function<Book, String> bookToCapitalizedTitle = bookToTitle.andThen(capitalize);

        // composeを使う関数合成
        Function<Book, String> bookToCapitalizedCompose = capitalize.compose(bookToTitle);
        // 使用例
        // bookToTitle,capitalizeの順で呼ぶ
        var capitalizeCompose = bookToCapitalizedCompose.apply(new Book("PeopleWare"));

        // ラムダ式の関数合成
        Function<Book, String> bookToCapitalizedTitleLambda = ((Function<Book, String>) book -> book.title())
                .andThen(s -> s.toUpperCase());

        // 多段の関数合成の例
        Function<Book, String> bookToCapitalizedAndTrimedTitle = bookToTitle.andThen(String::toUpperCase)
                .andThen(String::trim);
        // 使用例
        var s = bookToCapitalizedAndTrimedTitle.apply(new Book(" PeopleWare"));

        // capitalizeは下記の記載でも等価
        Function<String, String> capitalize2 = String::toUpperCase;

        // 一時変数をなくす
        // bookToTitle,capitalizeの順で呼ぶ
        var capitalizeTitleNew = capitalize.apply(bookToTitle.apply(new Book("PeopleWare")));

        // 使用例
        // bookToTitle,capitalizeの順で呼ぶ
        var capitalizeTitleNewNew = bookToCapitalizedTitle.apply(new Book("PeopleWare"));

        // 使用例
        // 一時変数を使って2つのFunctionを順に呼ぶ
        var title = bookToTitle.apply(new Book("PeopleWare"));
        System.out.println(title);
        var capitalizeTitle = capitalize.apply(title);
        System.out.println(capitalizeTitle);
        System.out.println(capitalizeTitleNew);
        System.out.println(capitalizeTitleNewNew);
        System.out.println(capitalizeCompose);
        System.out.println(s);
    }

    static void functionCompositionConsumer() {
        Consumer<String> consumer1 = s -> System.out.print("parameter1 is \"%s\"".formatted(s));
        Consumer<String> consumer2 = s -> System.out.print(" and ");
        Consumer<String> consumer3 = s -> System.out.print("parameter3 is \"%s\"".formatted(s));

        // 使用例
        // consumer1,consumer2,consumer3を同一引数で順に呼ぶ
        Consumer<String> consumers = consumer1.andThen(consumer2).andThen(consumer3);
        consumers.accept("foobar");
    }

    static void functionCompositionPredicate() {
        // 数値が正かを判定する処理
        IntPredicate isPositive = n -> n > 0;

        // 偶数かを判定する処理
        IntPredicate isEven = n -> n % 2 == 0;

        // 使用例
        // negateで論理反転（0または負数で真になる判定）
        IntPredicate isNegativeOrZero = isPositive.negate();

        // 正数かつ偶数を判定
        IntPredicate isPositiveEven = isPositive.and(isEven);

        System.out.println(isNegativeOrZero.test(0));
        System.out.println(isPositiveEven.test(10));
        System.out.println(isNegativeOrZero.test(10));
        System.out.println(isPositiveEven.test(10));
    }

    static void functionTwoArgument() {
        // 2引数aとbを受取 a + b の結果を返す関数型インターフェース
        // (a,b) -> a + b
        Function<Integer, Function<Integer, Integer>> fnPlus = a -> b -> a + b;

        // 参考：fnPlusを愚直に書き直したコード
        Function<Integer, Function<Integer, Integer>> fnPlus2 = a -> {
            Function<Integer, Integer> plus = b -> a + b;
            return plus;
        };

        // fnPlusの呼び出しコード
        // 1 + 2 = 3
        System.out.println(fnPlus.apply(1).apply(2));
        System.out.println(fnPlus2.apply(1).apply(2));
    }

    static void functionThreeArgument() {
        // (a,b,c) -> (a+b) * cの3引数の処理
        Function<Integer, Function<Integer, Function<Integer, Integer>>> fn3arg = a -> b -> c -> (a + b) * c;

        // fn3argの呼び出しコード
        // (3+2) * 100 = 500
        System.out.println(fn3arg.apply(3).apply(2).apply(100));

        // (a,b) -> a*bの2引数の処理
        Function<Integer, Function<Integer, Integer>> fnMulti = a -> b -> a * b;

        // TwoArgumentのfnPlus
        Function<Integer, Function<Integer, Integer>> fnPlus = a -> b -> a + b;

        // 上記2つのコードを使ってfn3argを書き換えたコード
        Function<Integer, Function<Integer, Function<Integer, Integer>>> fn3arg2 = a -> fnPlus.apply(a)
                .andThen(fnMulti);

        System.out.println(fn3arg2.apply(3).apply(2).apply(100));
    }

    static void methodReferenceBasic() {
        // sortメソッドは引数の配列を変更するので可変リストを使用
        var list = new ArrayList<>(List.of("abc", "xyz", "za", "defghi"));

        // 文字列長でソートするラムダ式を引数で渡す
        Collections.sort(list, (s1, s2) -> {
            return s2.length() - s1.length();
        });
        System.out.println(list);

        // 文字列長の逆順ソート
        Collections.sort(list, (s1, s2) -> {
            return s1.length() - s2.length();
        });
        System.out.println(list);
    }

    // 遅延処理にならない例
    static String generateValue() {
        System.out.println("generateValue is called");
        return "GENERATED-VALUE";
    }

    static void putIfAbsentBasic() {
        // 使用例
        var map = new HashMap<String, String>(Map.of("KEY", "VALUE"));

        // "KEY"要素が存在しているのでgenerateValueメソッドの結果は使われない
        // しかしgenerateValueメソッドの呼び出しは発生する
        String value = map.putIfAbsent("KEY", generateValue());
        System.out.println(value);
    }

    static void computeIfAbsentBasic() {
        var map = new HashMap<String, String>(Map.of("KEY", "VALUE"));

        // 要素がすでに存在している場合、generateValueメソッドの呼び出し自体が発生しない
        String value = map.computeIfAbsent("KEY", (key) -> {
            return generateValue();
        });
        System.out.println(value);

        // 要素が存在しない場合に限り、generateValueメソッドの呼び出しが発生
        String value2 = map.computeIfAbsent("KEY2", (key) -> {
            return generateValue();
        });
        System.out.println(value2);
    }

    static void collectionForEachBasic() {
        List<String> list = new ArrayList<String>(List.of("abc", "efg"));
        // void forEach(Consumer<String> action){
        // for(String s :list){
        // action.accept(s);
        // }
        // }
    }

    static void enumerationProcessForeach() {
        List<String> list = new ArrayList<String>(List.of("abc", "efg"));

        // Collection<String> listを仮定すると下記4行はすべて同じ結果
        // ラムダ式
        list.forEach(s -> System.out.println(s));
        // ラムダ式（非省略版）
        list.forEach((String s) -> {
            System.out.println(s);
        });
        // メソッド参照
        list.forEach(System.out::println);
        // 拡張for構文
        for (String s : list) {
            System.out.println(s);
        }
    }

    static void mapForeachExample() {
        var map = Map.of("key1", "value1", "key2", "value2");
        map.forEach((k, v) -> System.out.println("key is %s and value is %s".formatted(k, v)));
    }

    public static void main(String[] args) {
        mapForeachExample();
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

    void outOfFieldVariable() {
        Consumer<String> consumer = lparam -> {
            String field = "local-field";
            System.out.println(String.join(",", "パラメータ変数", lparam));
            System.out.println(String.join(",", "ローカル変数", field));
            System.out.println(String.join(",", "フィールド変数", this.field));
        };
        consumer.accept("lparam");

        // フィールド変数に再代入
        this.field = "update-filed";
        // ラムダ式の中では再代入後のフィールド変数が見える
        consumer.accept("lparam2");
    }

    Consumer<String> methodLambda(String mparam) {
        int mparamLen = mparam.length();
        // ラムダ式をreturn。ラムダ式の評価値を返す、と解釈してください
        return lparam -> {
            System.out.println(String.join(",", lparam, mparam, String.valueOf(mparamLen), this.field));
        };
    }

    // ラムダ式を返すメソッド
    IntSupplier makeCounter(int start) {
        this.count = start; // count変数は実質的finalではないため
        // Local variable count defined in an enclosing scope must be final or
        // effectively final
        // return () -> count++; // このラムダ式はコンパイルエラー
        return () -> this.count++;
    }

    // 参照先の配列を使うパターン
    IntSupplier makeCounter2(int start) {
        int[] count = { start };
        return () -> count[0]++;
    }

    // 参照先のオブジェクトを使うパターン（AtomicIntegerは一例）
    IntSupplier makeCounter3(int start) {
        AtomicInteger count = new AtomicInteger(start);
        return () -> {
            return count.getAndIncrement();
        };
    }

    My() {
        System.out.println("コンストラクタの呼び出し");
    }

    String field = "init-field";
    private int count;
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
