package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Chapter8_2 {

    static void setObjectExample() {
        var set = new HashSet<String>();

        // セットへの要素の追加。成功するとtrueを返す。
        boolean result1 = set.add("abc");
        boolean result2 = set.add("xyz");
        System.out.println(result1);
        System.out.println(result2);

        System.out.println(set);

        // すでに存在する要素の追加はfalseを返す
        // 例外は発生しないのでロジック次第では返り値を無視して構わない
        System.out.println(set.add("abc"));

        // 要素の存在チェック：存在すれば真、存在しなければ偽
        System.out.println(set.contains("abc"));

        // 要素の削除に成功するとtrueを返す
        System.out.println(set.remove("abc"));

        // 削除済みなど、存在しない要素の削除はfalseを返す
        // 例外は発生しないのでロジック次第では返り値を無視して構わない
        System.out.println(set.remove("abc"));

        System.out.println(set);
    }

    // 変更不可コレクションへの変更
    static void CollectionUnmodifiable() {
        // 変更可能コレクション
        var list = new ArrayList<String>();
        list.add("abc");
        list.add("def");

        // 変更不可コレクションに変更
        // 変数の型はvaeで省略可能。ここでは明示
        List<String> uList = Collections.unmodifiableList(list);

        // 変更不可コレクションに対する変更は
        // UnsupportedOperationException実行時例外発生
        // uList.add("xyz");

        // 別解
        // List.copyOfメソッドでも変更不可コレクションに変更かのう
        List<String> ulist2 = List.copyOf(list);

        ulist2.add("xyz");
    }

    static void collectionsCopy() {
        // コピー元のListオブジェクト
        var list = new ArrayList<String>();
        list.add("abc");
        list.add("def");

        // コピー先のListオブジェクト
        var list2 = new ArrayList<>(list);

        // コピー先のListオブジェクトへの追加要素
        list2.set(0, "ABC");
        System.out.println(list2);

        // コピー元のListオブジェクトから変更は見えない
        System.out.println(list);

        list.add("xyz");
        System.out.println(list2);
        System.out.println(list);
    }

    static void collectionShallowCopy() {
        // コピー元のListオブジェクト
        var list = new ArrayList<StringBuilder>();
        list.add(new StringBuilder("abc"));
        list.add(new StringBuilder("def"));

        // コピー先のListオブジェクト
        var list2 = new ArrayList<>(list);

        // コピー先のListオブジェクトの要素自体を変更
        list2.get(0).append("XYZ");

        // コピー元のListオブジェクトから変更が見える
        System.out.println(list);
    }

    static void collectionDeepCopy() {
        // コピー元のListオブジェクト
        var list = new ArrayList<StringBuilder>();
        list.add(new StringBuilder("abc"));
        list.add(new StringBuilder("def"));

        // コピー先のListオブジェクトをストリーム処理で生成
        var list2 = list.stream().map(sb -> {
            return new StringBuilder(sb);
        }).toList();

        // コピー先のListオブジェクトの要素自体を変更
        list2.get(0).append("XYZ");

        // コピー元のListオブジェクトからは変更は見えない
        System.out.println(list);
    }

    static void collectionInitial() {
        // 型がわかりやすいように変数の型を明示
        List<String> list = List.of("abc", "def", "ghi");

        // リテラル以外の要素指定も可能です
        var s = "abc";
        var list2 = List.of(s, s, s);
        System.out.println(list2);

        // 要素追加は実行時例外発生（削除と置換も同様）
        list.add("xyz");
    }

    static void arraysInitial() {
        List<String> list = Arrays.asList("abc", "def", "ghi");

        // 要素追加は実行時例外発生（削除も同様）
        // list.add("xyz");

        // 要素置換は可能（インプレースのソート処理可能）
        list.set(0, "xyz");
        System.out.println(list);
    }

    static void changeableListInitial() {
        // もっとも記述が簡潔（内部的には要素をコピー）
        var list = new ArrayList<>(List.of("abc", "def", "ghi"));

        // 匿名クラス
        var list2 = new ArrayList<String>() {
            {
                add("abc");
                add("def");
                add("ghi");
            }
        };

        System.out.println(list);
        System.out.println(list2);
    }

    static void mapSetInitial() {
        // 変更不可Mapオブジェクト
        Map<String, String> map = Map.of("k1", "v2", "k2", "v2");
        System.out.println(map);

        // 変更不可Mapオブジェクト
        Map<String, String> map2 = Map.ofEntries(Map.entry("k1", "v1"), Map.entry("k2", "v2"));
        System.out.println(map2);

        // 変更不可Setオブジェクト
        Set<String> set = Set.of("elem1", "elem2");
        System.out.println(set);
    }

    static void changeableMapSetInitial() {
        // もっとも記述が簡潔（内部的には要素をコピー）
        var map = new HashMap<>(Map.of("k1", "v1", "k2", "v2"));

        // 匿名クラスを活用
        var map2 = new HashMap<String, String>() {
            {
                put("k1", "v1");
                put("k2", "v2");
            }
        };

        // もっとも記述が簡潔（内部的には要素をコピー）
        var set = new HashSet<>(Set.of("elem1", "elem2"));

        // 匿名クラスを活用
        var set2 = new HashSet<String>() {
            {
                add("elem1");
                add("elem2");
            }
        };
    }

    static void collectionsSortError() {
        record Book(String title) implements Comparable<Book> {
            @Override
            public int compareTo(Book o) {
                return this.title.compareTo(o.title);
            }
        }

        var list = new ArrayList<Book>();
        list.add(new Book("xyz"));
        list.add(new Book("abc"));

        // Comparableインターフェースを継承しないとコンパイルエラー
        Collections.sort(list);
    }

    static void iterativeProcessingList() {
        // 前提
        List<Integer> list = List.of(1, 2, 3);
        int sum = 0;

        // 拡張for構文を使うパターン（ループ変数の型はvarで省略可能。下記2例も同様）
        for (Integer n : list) {
            sum += n;
        }

        // イテレーターを使うパターン
        for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
            Integer n = it.next();
            sum += n;
        }

        // インデックスでforループを回すパターン
        for (int i = 0; i < list.size(); i++) {
            Integer n = list.get(i);
            sum += n;
        }

        // コレクションに対するイテレーション
        calcSum(list);
    }

    static void iteratorSet() {
        Set<Integer> set = new HashSet<>(Set.of(1, 2, 3));
        int sum = 0;

        // イテレーターを使うパターン
        for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
            Integer n = it.next();
            sum += n;
        }

        // 拡張for構文を使うパターン
        for (Integer n : set) {
            sum += n;
        }

        // コレクションに対するイテレーション
        calcSum(set);
    }

    static int calcSum(Collection<Integer> coll) { // 引数にListでもSetでも受け入れ可能なメソッド
        int sum = 0;
        for (Integer n : coll) {
            sum += n;
        }
        return sum;
    }

    static void iteratorMap() {
        Map<String, String> map = new HashMap<>(Map.of("k1", "v1", "k2", "v2", "k3", "v3", "k4", "v4"));

        // 拡張for構文による、キーに対する繰り返し処理
        for (String key : map.keySet()) {
            System.out.println(key);
        }

        // イテレーターによる、キーに対する繰り返し処理
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            System.out.println(key);
        }

        // 拡張for構文による、値に対する繰り返し処理
        for (String val : map.values()) {
            System.out.println(val);
        }

        // イテレーターによる、値に対する繰り返し処理
        for (Iterator<String> it = map.values().iterator(); it.hasNext();) {
            String val = it.next();
            System.out.println(val);
        }

        // 拡張for構文による、キーと値に対する繰り返し処理
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // イテレーターによる、キーと値に対する繰り返し処理
        for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    static void hasPreviousList() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        for (ListIterator<Integer> it = list.listIterator(list.size()); it.hasPrevious();) {
            Integer i = it.previous();
            System.out.println(i);
        }
    }

    static void pauseListIterator() {
        List<String> list = List.of("no", "no", "pause", "pauseNext", "pauseNextNext");
        ListIterator<String> saveIt = null;
        for (ListIterator<String> it = list.listIterator(); it.hasNext();) {
            String s = it.next();
            if (s.equals("pause")) {
                saveIt = it; // 途中のイテレーターを代入
                break;
            }
        }

        if (saveIt != null) {
            for (ListIterator<String> it = saveIt; it.hasNext();) {
                String s = it.next();
                System.out.println(s);
            }
        }
    }

    static void collectionIteratorError() {
        List<String> list = new ArrayList<>(List.of("abc", "def", "ghi"));
        // ConcurrentModificationException実行時例外が発生
        // for (String s : list) {
        // list.remove(0);
        // }

        // 例外は起きないが期待通りに動作しない
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        System.out.println(list);
    }

    static void collectionIteratorIncreaseDecrease() {
        // コレクションのイテレーション中にremoveメソッドで要素を削除する
        var list = new ArrayList<>(List.of("abc", "def", "ghi"));

        // 下記コードで全要素を削除可能
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
            it.next();
            it.remove();
        }
        System.out.println(list);

        // 要素の挿入処理が多い場合、ArrayListよりLinkedListのほうが効率的
        var linkList = new LinkedList<>(List.of("abc", "def", "ghi"));
        for (ListIterator<String> it = linkList.listIterator(); it.hasNext();) {
            it.next();
            it.add("XYZ");
        }
        System.out.println(linkList);
    }

    public static void main(String... args) {
        collectionIteratorIncreaseDecrease();
    }
}

// COLUMN
// EnumSetとEnumMap
class EnumMain {
    // DayOfWeek
    private static final EnumSet<DayOfWeek> holidays = EnumSet.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY);

    // EnumMapを利用して各曜日が休日かどうかをマッピング
    private static final Map<DayOfWeek, Boolean> holidaysMap = new EnumMap<>(DayOfWeek.class);

    static {
        // static初期化ブロックで初期化
        for (var day : DayOfWeek.values()) {
            // デフォルトは平日(false)
            holidaysMap.put(day, false);
        }

        // 休日を設定
        holidaysMap.put(DayOfWeek.SUNDAY, true);
        holidaysMap.put(DayOfWeek.SATURDAY, true);
    }

    static void executeByDay(DayOfWeek dow) {
        if (holidays.contains(dow)) { // 条件分岐を簡易化できる
            System.out.println("day off");
        } else {
            System.out.println("workday");
        }
    }

    static void executeByDayMap(DayOfWeek dow) {
        if (dow == null) {
            throw new NullPointerException(); // nullチェックの追加
        }

        // EnumMapから値を取得して条件分岐
        // getOrDefault（指定したキーで値を検索、存在しない場合はデフォルト値を返す）
        // 今回は値はBoolean型なのでキー探索がそのまま条件分岐となる
        if (holidaysMap.getOrDefault(dow, false)) {
            System.out.println("day off");
        } else {
            System.out.println("workday");
        }
    }
}
