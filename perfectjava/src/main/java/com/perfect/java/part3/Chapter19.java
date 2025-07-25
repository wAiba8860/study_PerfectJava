package com.perfect.java.part3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Chapter19 {

    static void genericErrorExample() {
        List<String> slist = new ArrayList<>();
        slist.add("abc"); // OK
        // slist.add(0); // コンパイルエラー

        List<Integer> ilist = new ArrayList<>();
        ilist.add(0); // OK
        // ilist.add("abc"); //コンパイルエラー
    }

    // ListObjectClassの使用
    static void ListObjectBasic() {
        var oList = new ListObjectClass();

        // 要素追加時にはキャスト不要
        oList.add("abc");

        // 要素取得時にダウンキャストが必要
        String s = (String) oList.get(0);
        // varでローカル変数の型記述を省略するとキャスト不要にできる（非推奨）
        var s2 = oList.get(0);
        System.out.println(s);

        // どんな要素の型でも追加可能
        oList.add(Integer.valueOf(123));

        // Integer値をString型で取り出そうとするとClassCastException実行時例外が発生
        String s3 = (String) oList.get(1);
    }

    // ジェネリック型クラスの使用例
    static void genericClassBasic() {
        Owner<String> os = new Owner<>();
        // または
        var os2 = new Owner<String>();

        os.put("abc");
        String s = os.get();
        System.out.println(s);

        // 変数の型を明示するとnew式の型引数を省略可能
        // ダイヤモンドに見えることから<>をダイヤモンド形式と呼ぶ
        Owner<String> os3 = new Owner<>();

        // 変数の型をvarにするとnew式の型引数を省略できない
        var os4 = new Owner<String>();

        // 変数の型の型引数を?で省略可能
        Owner<?> os5 = new Owner<String>();

        // 何も省略しない記述。冗長なので非推奨
        Owner<String> os6 = new Owner<String>();
    }

    // ジェネリック型インターフェースとレコードクラスの使用例
    static void genericInterfaceRecord() {
        var myRecord = new MyRecord<String, String>("key1", "value1");
        var k = myRecord.key();
        System.out.println(k);
        var v = myRecord.value();
        System.out.println(v);
    }

    // OwnerBoundをパラメータ化
    static void genericParameterBasic() {
        // 有効（StringクラスはCharSequenceをインターフェース継承しているので）
        OwnerBound<String> owner1;

        // コンパイルエラー（IntegerクラスはCharSequenceをインターフェース継承していないので）
        // OwnerBound<Integer> owner2;
    }

    // ジェネリックメソッドの例
    <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>();
        for (T elem : array) {
            list.add(elem);
        }
        return list;
    }

    // 型推論依存のジェネリックメソッドの呼び出し
    static void myGenericCallBasic() {
        // 使用例
        // 実引数の型でジェネリックメソッドの型引数が決まる
        String s = MyGenericCall.method("abc");
        System.out.println(s);
        Integer i = MyGenericCall.method(123);
        System.out.println(i);

        // コンパイルエラー
        // String sE = MyGenericCall.method(123);

        // 型推論を使わない明示的な指定も可能。多くの場合は不要
        String s2 = MyGenericCall.<String>method(null);
        System.out.println(s2);
    }

    static void typeWildCard(List<? extends CharSequence> list) {
        // コレクションに継承関係があるとき、基底型の変数に派生型オブジェクトを代入可能
        List<String> list1 = new ArrayList<String>();

        // コンパイルエラー（CharsequenceインターフェースはStringクラスの基底型）
        // List<CharSequence> list2 = new ArrayList<String>();

        // 下記はコンパイルが通る
        // ? extendsはワイルドカード
        List<? extends CharSequence> list3 = new ArrayList<String>();

        // 下記のようにCharSequence型の要素取得は可能
        CharSequence cs = list.get(0);
        System.out.println("リストの中身: %s".formatted(cs));

        // 下記4行は全てコンパイルエラーになる
        // list.add(0,"abc");
        // list.add(0,new StringBuilder("abc"));
        // String s = list.get(0);
        // StringBuilder sb = list.get(0);
    }

    // 異なる型の要素を追加可能なメソッド
    static void addDifferenceTypeMethod(List<? super CharSequence> list) {
        list.add("abcdef");
        list.add(new StringBuilder("ghi"));
    }

    // 上記メソッドの使用例
    static void addDifferenceBasic() {
        List<CharSequence> list = new ArrayList<>();
        addDifferenceTypeMethod(list);
        CharSequence cs1 = list.get(0);
        System.out.println(cs1);
        CharSequence cs2 = list.get(1);
        System.out.println(cs2);
    }

    static Object max(MyComparable[] arr) {
        MyComparable ret = null;
        for (MyComparable e : arr) {
            if (ret == null) {
                ret = e;
                continue;
            }
            if (e.compareTo(ret) > 0) {
                ret = e;
            }
        }
        return ret;
    }

    // maxメソッドを使う例
    static void maxComparableBasic() {
        MyString[] sarr = { new MyString("a"), new MyString("bbbb"), new MyString("cc") };
        MyString m = (MyString) max(sarr);
        System.out.println(m.toString());

        // maxメソッドを使う例。コンパイルは通るが、実行時例外が発生
        MyComparable[] oarr = { new MyString("a"), new MyInteger(0) };
        Object o = max(oarr);
    }

    public static void main(String... args) {
        maxComparableBasic();
    }
}

interface MyComparable {
    int compareTo(Object o);
}

class ListObjectClass {
    private final Object[] array = new Object[32];
    private int size = 0;

    boolean add(Object o) {
        array[size++] = o; // サイズチェック省略
        return true;
    }

    Object get(int index) {
        return array[index];
    }
    // 他は省略
}

// Ownerがジェネリック型クラス
class Owner<E> {
    private E element;

    E get() {
        return element;
    }

    void put(E element) {
        this.element = element;
    }

    // ジェネリック型クラス内で型変数Eを使いnewするのは禁止
    // E createObject() {
    // // Cannot instantiate the type EJava(16777373)
    // return new E(); // コンパイルエラー
    // }
}

// ジェネリック型インターフェースの宣言例
interface MAP<K, V> {
    V get(Object key);

    V put(K key, V value);
    // getとput以外は省略
}

// ジェネリック型レコードクラスの宣言例
record MyRecord<K, V>(K key, V value) {
}

// 概念上、Owner<String>で創出されるクラス
class OwnerString {
    private String element;

    String get() {
        return element;
    }

    void put(String element) {
        this.element = element;
    }
}

// 境界のある型変数
class OwnerBound<E extends CharSequence> { // 境界型がCharSequence
    private E element;
    // 省略

    // 下記は有効（lengthメソッドはCharSequenceインターフェースのメソッドだから）
    int getLength() {
        return element.length();
    }
}

// ３箇所の型変数Tはすべて別物
class MyType<T> { // このTと
    T method1(T t) { // このTは同じ
        return t;
    }

    <T> T method2(T t) { // このTは上記Tと別物
        return t;
    }

    static <T> T method3(T t) { // このTは上記２つのどちらのTとも別物
        return t;
    }

    // 型変数を型引数に指定
    List<T> method(T t) {
        List<T> list = new ArrayList<>(); // 型変数Tを型引数に指定
        // 上記は var list = new ArrayList<T>(); にも書き換え可能
        list.add(t);
        return list;
    }
}

class MyGenericCall {
    static <T> T method(T t) {
        return t;
    }
}

// 複雑なパラメータ化された型の回避
// 要素方を決め打ちして、あたらしい型にする例
class MultiValueMap extends HashMap<String, List<String>> {
}

// ジェネリック型のまま新しい型にする例
class MultiValueMap2<K, V> extends HashMap<K, List<V>> {
}

class UseMultiValue {
    public static void main(String[] args) {
        var mmap = new MultiValueMap();
        MultiValueMap2<String, String> mmap2 = new MultiValueMap2<>();
    }
}

// MyComparableインターフェースの実装クラス（数値比較用）
class MyInteger implements MyComparable {
    private final int i;

    MyInteger(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof MyInteger mi) {
            return this.i - mi.i;
        } else {
            throw new IllegalArgumentException();
        }
    }
}

// MyComparableインターフェースの実装クラス（文字列長比較用）
class MyString implements MyComparable {
    private final String s;

    MyString(String s) {
        this.s = s;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof MyString ms) {
            return this.s.length() - ms.s.length();
        } else {
            throw new IllegalArgumentException();
        }
    }
}