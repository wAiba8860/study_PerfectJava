package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter10_1 {

    static void streamExampleRedundant() {
        // データソースを準備
        List<String> list = List.of("abc", "xyz", "za", "defghi");

        // データソースから初期ストリーム生成
        Stream<String> stream1 = list.stream();

        // ストリームの中間処理:filter処理（"a"を含む文字列として"abc"と"za"だけ通過）
        Stream<String> stream2 = stream1.filter(s -> s.contains("a"));

        // ストリーム中間処理:map処理（大文字変換で"ABC"と"ZA"になる）
        Stream<String> stream3 = stream2.map(s -> s.toUpperCase());

        // ストリーム終端処理:画面出力
        stream3.forEach(s -> System.out.println(s));
    }

    static void streamExample() {
        // データソースを準備
        List<String> list = List.of("abc", "xyz", "za", "defghi");

        list.stream().filter((String s) -> {
            return s.contains("a");
        }).map((String s) -> {
            return s.toUpperCase();
        }).forEach(System.out::println);

        List.of("abc", "xyz", "za", "defghi").stream().filter(s -> s.contains("a"))
                .map(String::toUpperCase).forEach(System.out::println);
    }

    static void streamErrorPeek() {
        List.of("abc", "xyz", "za", "defghi").stream().filter(s -> s.contains("a"))
                // .forEach(System.out::println) //終端処理の後続処理はエラー
                .peek(System.out::println).map(String::toUpperCase).forEach(System.out::println);
    }

    static void mapObjStreamExample() {
        var map = Map.of("key1", "val1", "key2", "val2");

        // MAPからストリームを生成してキーと値を表示
        Stream<Map.Entry<String, String>> stream = map.entrySet().stream();

        stream.forEach(e -> {
            System.out.println(e.getKey() + "," + e.getValue());
        });

        // マップ要素の値だけを表示
        map.entrySet().stream().map(Map.Entry::getValue).forEach(System.out::println);
        map.entrySet().stream().map(e -> e.getValue()).forEach(System.out::println);
    }

    // ファクトリメソッドでのストリーム生成
    static void factoryMethodStream() {
        // 可変長引数のStream.ofメソッド（簡易なテストコードで便利）
        Stream<String> stream1 = Stream.of("abc", "xyz");

        // 無限に文字列を生成するストリーム（無限ストリーム）
        Stream<String> stream2 = Stream.generate(() -> "foo");

        // "a"から始まり文字列を倍々に増やした文字列を要素とする無限ストリーム
        Stream<String> stream3 = Stream.iterate("a", (String s) -> s + s);

        // 上記に終了条件（文字列が5未満）をつけたストリーム
        Stream<String> stream4 = Stream.iterate("a", s -> s.length() < 5, s -> s + s);
    }

    static void streamBuilderExample() {
        // Stream.Builderオブジェクトを生成
        Stream.Builder<String> builder = Stream.builder();

        // ストリーム処理の対象要素を追加
        builder.add("abc").add("xyz"); // addの返り値はStream.Builder。メソッドチェーン可

        // ストリーム生成
        Stream<String> stream = builder.build();
        stream.forEach(System.out::println);
    }

    static void infiniteStream() {
        // 無限ストリームには途中で打ち切るための中間処理（limitなど）が必須
        Stream.iterate("a", s -> s + s).limit(3).forEach(System.out::println);
    }

    static void flatMapExample() {
        // 入れ子のList（データソース）
        List<List<String>> listOfList = List.of(List.of("abc", "def"), List.of("ghi", "jkl", "mno"));

        // 使用例
        var list = listOfList.stream()
                .flatMap((List<String> valList) -> {
                    return valList.stream(); // flatMap(List::stream)の記載も可能
                })
                .toList(); // ストリーム処理の出力列を要素に持つListオブジェクト生成

        System.out.println(list);

        var list2 = Stream.of("abCd")
                .flatMap(s -> Stream.of(s.toUpperCase(), s.toLowerCase()))
                .toList();

        System.out.println(list2);
    }

    static void flatMapOptionalExample() {
        // 使用例
        // KVSWrapperクラスメソッドを使うとストリーム処理の要素がOptionalオブジェクトになる
        List<Optional<String>> ret1 = Stream.of("key1").map(KVSWrapper::get).toList();
        System.out.println(ret1);

        // OptionalオブジェクトのstreamメソッドとflatMapを組み合わせると要素がOptinalの中身になる
        List<String> ret2 = Stream.of("key1").map(KVSWrapper::get).flatMap(Optional::stream).toList();
        System.out.println(ret2);

        // 上記コードの書き換え版
        List<String> ret3 = Stream.of("key1").flatMap(s -> KVSWrapper.get(s).stream()).toList();
        System.out.println(ret3);

        // 空Optionalオブジェクトの場合は後続ストリーム処理の要素がなくなる
        List<String> ret4 = Stream.of("key2").flatMap(s -> KVSWrapper.get(s).stream()).toList();
        System.out.println(ret4);
    }

    static void mapMultiExample() {
        // 入れ子のList（データソース）
        List<List<String>> listOfList = List.of(List.of("abc", "def"), List.of("ghi", "jkl", "mno"));

        // 使用例
        var list = listOfList.stream()
                .mapMulti((List<String> valList, Consumer<String> consumer) -> {
                    valList.forEach(val -> consumer.accept(val));
                })
                .toList();
        System.out.println(list);
    }

    static void terminationExample() {
        // streamオブジェクトからListオブジェクトを生成
        List<String> list1 = Stream.of("abc", "xyz").toList();
        System.out.println(list1);

        // streamオブジェクトから配列を生成
        String[] array = Stream.of("abc", "xyz").toArray(String[]::new);
        System.out.println(Arrays.toString(array));

        // streamオブジェクトからイテレーターを生成
        for (Iterator<String> itr = Stream.of("abc", "xyz").iterator(); itr.hasNext();) {
            System.out.println(itr.next());
        }

        // collectメソッドの使用例
        // StreamオブジェクトからListオブジェクトを生成（変更可能リスト）
        List<String> list2 = Stream.of("abc", "xyz").collect(Collectors.toList());
        System.out.println(list2);

        // StreamオブジェクトからListオブジェクトを生成（変更不可リスト）
        List<String> list3 = Stream.of("abc", "xyz").collect(Collectors.toUnmodifiableList());
        System.out.println(list3);

        // StreamオブジェクトからSetオブジェクトを生成（変更可能セット）
        Set<String> set = Stream.of("abc", "xyz").collect(Collectors.toSet());
        System.out.println(set);

        // StreamオブジェクトからMapオブジェクトを生成（変更可能マップ）
        // マップのキーと値への変換関数を指定。下記例のFunction.identity()は入力値をそのまま出力値にする
        Map<String, String> map = Stream.of("abc", "xyz")
                .collect(Collectors.toMap(Function.identity(), Function.identity()));
        System.out.println(map);

        // StreamオブジェクトからListオブジェクトを生成（変更可能リスト）
        List<String> list4 = Stream.of("abc", "xyz").collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list4);

        // StreamオブジェクトからSetオブジェクトを生成（変更可能セット）
        Set<String> set2 = Stream.of("abc", "xyz").collect(Collectors.toCollection(HashSet::new));
        System.out.println(set2);

        // StreamオブジェクトからMapオブジェクトを生成（変更可能マップ）
        Map<String, String> map2 = Stream.of("abc", "xyz")
                .collect(Collectors.toMap(Function.identity(), Function.identity(), (String s1, String s2) -> {
                    return s1;
                }, () -> {
                    return new HashMap<String, String>();
                }));
        System.out.println(map2);
    }

    static void terminationSingleResult() {
        // 要素数を出力
        long result = Stream.of("abc", "xyz", "za", "defghi").count();
        System.out.println(result);

        // 文字列の大小関係（アルファベット順）で比較して最小値を出力
        Stream.of("abc", "xyz", "za", "defghi", "OPQ").min((String s1, String s2) -> {
            return s1.compareTo(s2);
        }).ifPresent(System.out::println);

        // 文字列長で比較して最小値を出力
        Stream.of("abc", "xyz", "za", "defghi")
                .min((String s1, String s2) -> {
                    return s1.length() - s2.length();
                }).ifPresent(System.out::println);
    }

    static void reduceStreamExample() {
        // ifPresentはreduceの返り値の型がOptionalのため必要
        Stream.of("abc", "xyz", "za", "defghi")
                .reduce((String acc, String cur) -> {
                    return acc + cur;
                }).ifPresent(System.out::println);

        // 空ストリームに対するreduce処理
        Optional<String> result = Stream.<String>empty().reduce((String acc, String cur) -> {
            return acc + cur;
        });
        System.out.println(result);

        // accの初期値を与えるreduce処理
        // 初期値が空文字列
        String result2 = Stream.of("abc", "xyz", "za", "defghi")
                .reduce("", (String acc, String cur) -> {
                    return acc + cur;
                });
        System.out.println(result2);

        // 初期値が#
        // ifPresentはreduceの返り値の型がOptionalのため必要
        String result3 = Stream.of("abc", "xyz", "za", "defghi")
                .reduce("#", (String acc, String cur) -> {
                    return acc + cur;
                });
        System.out.println(result3);

        List<String> result4 = Stream.of("abc", "xyz", "za", "defghi")
                .reduce(new ArrayList<String>(),
                        (acc, cur) -> {
                            if (cur.contains("a")) {
                                acc.add(cur.toUpperCase());
                            }
                            return acc;
                        },
                        (acc1, acc2) -> {
                            acc1.addAll(acc2);
                            return acc1;
                        });
        System.out.println(result4);
    }

    static void reduceGeneralPurpose() {
        ArrayList<String> result = Stream.of("abc", "xyz", "za", "defghi")
                .reduce(new ArrayList<String>(),
                        (ArrayList<String> acc, String cur) -> {
                            acc.add(cur);
                            return acc;
                        },
                        (ArrayList<String> acc1, ArrayList<String> acc2) -> {
                            acc1.addAll(acc2);
                            return acc1;
                        });
        System.out.println(result);

        // 省略形
        List<String> result2 = Stream.of("abc", "xyz", "za", "defghi")
                .reduce(new ArrayList<String>(), (acc, cur) -> {
                    acc.add(cur);
                    return acc;
                },
                        (acc1, acc2) -> {
                            acc1.addAll(acc2);
                            return acc1;
                        });
        System.out.println(result2);
    }

    public static void main(String[] args) {
        reduceStreamExample();
    }
}

class KVSWrapper { // key-valueストアの模倣
    // 引数のキーに対する対応する値を返す
    // 存在しない場合、空Optionalオブジェクトを返す
    static Optional<String> get(String key) {
        var kvs = Map.of("key1", "val1");
        return Optional.ofNullable(kvs.get(key));
    }

}