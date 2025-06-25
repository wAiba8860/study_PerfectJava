package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Chapter8_1 {

    static void collectionExample() {
        // 変数の型を省略（以後のサンプルコードはこの形式を使用）
        var list1 = new ArrayList<String>();

        // 変数の型がインターフェース型。new式の<>内を省略
        List<String> list2 = new ArrayList<>();

        // 変数の型がインターフェース型。new式の<>内を省略しない
        List<String> list3 = new ArrayList<String>();

        // 変数の型がクラス型。new式の<>内を省略。
        ArrayList<String> list4 = new ArrayList<>();

        // 変数の型がクラス型。new式の<>内を省略。
        ArrayList<String> list5 = new ArrayList<String>();

        var list6 = new ArrayList<>();

        // 要素が配列
        var list7 = new ArrayList<int[]>();

        // 要素がコレクションオブジェクト
        var list8 = new ArrayList<ArrayList<String>>();

        // 要素の型に基本形は指定できない（コンパイルエラー）
        // Syntax error, insert "Dimensions" to complete ReferenceType
        // var list9 = new ArrayList<int>();

        // 要素の型に数値クラスを指定可能
        var list10 = new ArrayList<Integer>();
        list10.add(1000);
    }

    static void arraylistExample() {
        var list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        System.out.println(list);
    }

    static void listObjectExample() {
        var list = new ArrayList<String>();

        // 要素のみのaddはリストの末尾に追加
        // 返り値は常に真。チェック不要
        boolean result1 = list.add("abc");
        System.out.println(result1);

        boolean result2 = list.add("xyz");
        System.out.println(list);

        // インデックス値を指定したaddは要素の挿入
        // インデックス1を指定すると0番目と1番目の間に新要素を挿入
        // 返り値はない
        list.add(1, "def");
        System.out.println(list);

        // setで指定したインデックス値の要素を置換
        // 返り値は置換された要素
        String oldVal = list.set(1, "DEF");
        System.out.println(oldVal);

        System.out.println(list);

        // インデックス値の要素を取得
        System.out.println(list.get(1));

        // 要素の検索。返り値はインデックス値
        System.out.println(list.indexOf("xyz"));

        // インデックス値を指定した削除
        // 返り値は削除された要素
        String removeVal = list.remove(0);
        System.out.println(removeVal);
        System.out.println(list);

        // 要素を指定した削除。削除に成功すると真を返し失敗すると偽を返す。
        System.out.println(list.remove("abc"));
    }

    static void listEqualsExample() {
        // 要素による同値判定なのでリストの具象クラスは問わない
        var list1 = new ArrayList<String>();
        var list2 = new LinkedList<String>();

        // 先頭からすべての要素が同値の場合に限り、Listオブジェクトは同値
        list1.add("abc");
        list1.add("xyz");

        list2.add("abc");
        list2.add("xyz");

        System.out.println(list1.equals(list2));
    }

    static void listExceptionExample() {
        var list = new ArrayList<String>();
        if (list.isEmpty()) {

            // NoSuchElementException
            var list2 = new ArrayList<String>();
            list2.getFirst();

        } else {
            // IndexOutOfBoundsException
            list.get(0);
        }
    }

    static void mapObjectExample() {
        // 変数の型を省略（以後のサンプルコードはこの形式を使用）
        // キーの型がString、値の型がStringのHashMapオブジェクトを生成
        var map1 = new HashMap<String, String>();

        // 変数の型をインタフェース型。new式の<>内を省略
        Map<String, String> map2 = new HashMap<>();

        // 変数の型を省略
        // キーの型がInteger型、値の方がList<String>のTreeMapオブジェクト生成
        var map3 = new TreeMap<Integer, List<String>>();

        // 変数の型をインタフェース型。new式の<>内を省略。
        Map<Integer, List<String>> map4 = new TreeMap<>();
    }

    static void hashMapExample() {
        // HashMapオブジェクトの生成
        var map = new HashMap<String, String>();

        // （別解）トータルの要素数を概算可能な場合、引数に要素数を渡せる次のファクトリメソッドが効率的
        Map<String, String> map2 = HashMap.newHashMap(100);

        // putで要素（キーと値）を追加
        // 返り値は追加で置き換えられた古い要素。古い要素がない場合はnull
        String oldVal = map.put("k1", "v1");
        System.out.println(oldVal);
        String oldVal2 = map.put("k1", "V1");
        System.out.println(oldVal2);
        System.out.println(map);
        String oldVal3 = map.put("k2", "V2");
        System.out.println(oldVal3);

        // キーを指定して値を取得
        String val1 = map.get("k1");
        System.out.println(val1);

        // キーに対応する要素が存在しない場合、nullが返る
        String val2 = map.get("k3");
        System.out.println(val2);

        // getOrDefaultを使うと要素が存在しない場合に値を返せる
        String val3 = map.getOrDefault("k3", "v3");
        System.out.println(val3);

        // キーを指定して要素を削除。返り値は削除された要素の値
        String oldVal4 = map.remove("k2");
        System.out.println(oldVal4);
        System.out.println(map);

        // putIfAbsentでキーに対応する要素がない場合に要素を追加
        // 要素を追加した場合の返り値はnull
        String oldVal5 = map.putIfAbsent("k2", "V2");
        System.out.println(oldVal5);
        System.out.println(map);

        // キーに対応する要素がすでに存在する場合、putIfAbsentは要素を追加しない
        // 返り値は現在の要素の値
        String oldVal6 = map.putIfAbsent("k2", "VV2");
        System.out.println(oldVal6);
        System.out.println(map);
    }

    static void navigableMapExample() {
        NavigableMap<String, String> map = new TreeMap<>();
        map.put("ichi", "one");
        map.put("ni", "two");
        map.put("san", "three");

        // icに近いキーを検索
        Map.Entry<String, String> entry = map.ceilingEntry("ic");
        System.out.println(entry);
    }

    static void mapObjectEqualsExample() {
        // 要素による同値判定なのでマップの具象クラスは問わない
        var map1 = new HashMap<String, String>();
        var map2 = new TreeMap<String, String>();

        // 同値のキーを持ち、かつすべてのキーに対する値が同値の場合に限り、Mapオブジェクトは同値
        map1.put("k1", "v1");
        map1.put("k2", "v2");

        map2.put("k1", "v1");
        map2.put("k2", "v2");

        System.out.println(map1.equals(map2));
    }

    static void mapNullExample() {
        var map = new HashMap<String, String>();

        // nullの値の要素を追加
        map.put("key1", null);

        // getすると要素の値がnullとして返る
        String result = map.get("key1");
        System.out.println(result);

        // 存在しない要素の値もnull。値nullと区別がつかない
        String result2 = map.get("no-exist-key");
        System.out.println(result2);

        // containsKeyは真
        System.out.println(map.containsKey("key1"));

        // マップのサイズは1
        System.out.println(map.size());
    }

    public static void main(String[] args) {
        mapNullExample();
    }
}
