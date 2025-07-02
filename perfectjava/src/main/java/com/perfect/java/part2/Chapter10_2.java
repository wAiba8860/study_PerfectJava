package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.random.RandomGenerator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chapter10_2 {

    static void collectExample() {
        List<String> result = Stream.of("abc", "xyz", "za", "defghi").collect(() -> {
            return new ArrayList<String>();
        }, (acc, cur) -> {
            acc.add(cur);
        }, (acc1, acc2) -> {
            acc1.addAll(acc2);
        });
        System.out.println(result);

        // メソッド参照
        List<String> result2 = Stream.of("abc", "xyz", "za", "defghi").collect((ArrayList::new),
                List::add, List::addAll);
        System.out.println(result2);
    }

    static void collectorFinisher() {
        List<String> result = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collector.of(ArrayList<String>::new, (acc, cur) -> {
                    acc.add(cur);
                }, (acc1, acc2) -> {
                    acc1.addAll(acc2);
                    return acc1;
                }, (acc) -> {
                    return Collections.unmodifiableList(acc);
                }));
        System.out.println(result);

        // メソッド参照で書き換え
        List<String> result2 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collector.of(ArrayList<String>::new, List::add, (acc1, acc2) -> {
                    acc1.addAll(acc2);
                    return acc1;
                }, Collections::unmodifiableList));
        System.out.println(result2);
    }

    static void collectorsToMap() {
        // 要素の文字列をそのままキー、要素を大文字化した文字列を値に持つMapオブジェクトを生成
        Map<String, String> result = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.toMap((String s) -> {
                    return s;
                }, (String s) -> {
                    return s.toUpperCase();
                }));
        System.out.println(result);

        // s->sのように入力をそのまま出力にするラムダ式は Function.identity() で書き換え可能
        Map<String, String> result2 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.toMap(Function.identity(), String::toUpperCase));
        System.out.println(result2);
    }

    static void collectorsFactoryInt() {
        // 最小値の検索
        Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.minBy(String::compareTo))
                .ifPresent(System.out::println);

        // 要素の文字列長の合計
        int result = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.summingInt(String::length));
        System.out.println(result);

        // 要素の文字列長の統計値
        IntSummaryStatistics result2 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.summarizingInt(String::length));
        System.out.println(result2);
    }

    static void collectorsJoining() {
        // 文字列連結
        String result1 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.joining(";", "<", ">"));
        System.out.println(result1);

        // map中間処理とcollect処理を同時に記載
        List<String> result2 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.mapping(String::toUpperCase, Collectors.toList()));
        System.out.println(result2);

        // flatMap中間処理とcollect処理を同時に記載
        List<String> result3 = Stream.of(List.of("val11", "val12"), List.of("val21", "val22"))
                .collect(Collectors.flatMapping(List::stream, Collectors.toList()));
        System.out.println(result3);

        // filter中間処理とcollects処理を同時に記載
        List<String> result4 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.filtering((String s) -> {
                    return s.contains("a");
                }, Collectors.toList()));
        System.out.println(result4);

        // collect処理後に後処理を追加
        int joinedStringLength = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.collectingAndThen(Collectors.joining(";", "<", ">"), String::length));
        System.out.println(joinedStringLength);
    }

    static void collectorsGroupingBy() {
        // 結果のMap型のキーを定義
        enum Key {
            ContainsA, NotContainA,
        }

        // groupingByの第2引数のdownstream関数が、Mapの値のListに対するcollect処理
        Map<Key, String> result = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.groupingBy((String s) -> {
                    return s.contains("a") ? Key.ContainsA : Key.NotContainA;
                }, Collectors.joining(";", "<", ">")));
        System.out.println(result);

        // partitioningByメソッドの使用
        Map<Boolean, List<String>> result2 = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.partitioningBy((String s) -> {
                    return s.contains("a");
                }));
        System.out.println(result2);
    }

    static void collectorsTeeing() {
        // 2つのCollectors.joining処理にストリームを送り込み、その後、2つの結果をマージ
        String result = Stream.of("abc", "xyz", "za", "defghi")
                .collect(Collectors.teeing(Collectors.joining(";", "<", ">"), Collectors.joining("$", "(", ")"),
                        (String s1, String s2) -> {
                            return s1 + s2;
                        }));
        System.out.println(result);
    }

    static void collectorsIteratorBasic() {
        // 使用例
        List<Pair<String, String>> result = zip(List.of("k1", "k2", "k3"), List.of("v1", "v2", "v3"));
        result.stream().forEach(System.out::println);
    }

    /*
     * 入力：要素型Tのコレクションと要素型Sのコレクション
     * 出力：TとUの要素のPairレコードのList
     * 注意：parallelStreamにすると動きません
     */
    static <T, U> List<Pair<T, U>> zip(Collection<T> c1, Collection<U> c2) {
        Iterator<U> it = c2.iterator();
        return c1.stream().filter(x -> it.hasNext())
                .collect(() -> {
                    return new ArrayList<Pair<T, U>>();
                },
                        (acc, cur) -> {
                            acc.add(new Pair<T, U>(cur, it.next()));
                        },
                        (left, right) -> {
                            left.addAll(right);
                        });
    }

    static void numberStream() {
        // 偶数のみ抽出
        int[] result1 = IntStream.of(1, 2, 3, 4).filter((int n) -> {
            return n % 2 == 0;
        }).toArray();
        System.out.println(Arrays.toString(result1));

        // 数値を2倍に変換
        int[] result2 = IntStream.of(1, 2, 3, 4).map((int n) -> {
            return n * 2;
        }).toArray();
        System.out.println(Arrays.toString(result2));

        // ソート処理
        int[] result3 = IntStream.of(10, 2, 7, 4).sorted().toArray();
        System.out.println(Arrays.toString(result3));

        // reduce処理を使う合計
        int sum = IntStream.of(1, 2, 3, 4).reduce(0, (acc, cur) -> {
            return acc + cur;
        });
        System.out.println(sum);

        // 合計
        int sum2 = IntStream.of(1, 2, 3, 4).sum();
        System.out.println(sum2);
    }

    static void arraysStream() {
        // 配列からの数値ストリーム生成
        int[] arr = { 1, 2, 3, 4, 5, 6 };

        // 下記どちらも配列から数値ストリーム生成可能
        IntStream stream1 = Arrays.stream(arr);
        IntStream stream2 = IntStream.of(arr);

        stream1.forEach(System.out::print);
        System.out.println();
        stream2.forEach(System.out::print);
    }

    static void collectionToIntStream() {
        // コレクションから非数値ストリーム生成（非効率）
        List<Integer> list1 = List.of(1, 2, 3, 4);
        List<Integer> result = list1.stream().map(n -> n * 2).toList();
        System.out.println(result);

        // コレクションから数値ストリーム生成
        // 生成元のListオブジェクト。要素は数値オブジェクト
        List<Integer> list2 = List.of(1, 2, 3, 4);

        // mapToIntで変換
        // 型を明示
        IntStream stream1 = list2.stream().mapToInt((Integer n) -> {
            return (int) n;
        });

        // メソッド参照
        IntStream stream2 = list2.stream().mapToInt(Integer::intValue);

        // メソッド参照（別解）
        IntStream stream3 = list2.stream().flatMapToInt(IntStream::of);
    }

    static void intStreamRangeExample() {
        // 0から9までの要素を持つ配列を生成
        int[] arr = IntStream.range(0, 10).toArray();
        System.out.println(Arrays.toString(arr));

        // 0から10までの要素を持つ配列を生成
        int[] arr2 = IntStream.rangeClosed(0, 10).toArray();
        System.out.println(Arrays.toString(arr2));
    }

    static void randomGeneratorStream() {
        // 3つの乱数を整数で生成
        int[] result = RandomGenerator.getDefault().ints(3).toArray();
        System.out.println(Arrays.toString(result));

        // 0以上10未満の乱数を整数で生成
        // この乱数生成は無限ストリームを生成。limit中間処理などの打ち切り処理が必須
        int[] result2 = RandomGenerator.getDefault().ints(0, 10).limit(3).toArray();
        System.out.println(Arrays.toString(result2));

        // doublesで乱数を実数で生成
        double[] result3 = RandomGenerator.getDefault().doubles(0, 10).limit(3).toArray();
        System.out.println(Arrays.toString(result3));
    }

    static void numberStreamToNotNumberStream() {
        // boxed中間処理
        Stream<Integer> stream1 = IntStream.of(1, 2, 3, 4).boxed();

        // ラムダ式
        // 型を明示
        Stream<Integer> stream2 = IntStream.of(1, 2, 3, 4).mapToObj((int n) -> {
            return (Integer) n;
        });

        // メソッド参照
        Stream<Integer> stream3 = IntStream.of(1, 2, 3, 4).mapToObj(Integer::valueOf);

        // mapToObjメソッドを使うと基本型数値から任意オブジェクトへの変換も可能
        Stream<String> stream4 = IntStream.of(1, 2, 3, 4).mapToObj(String::valueOf);

        System.out.println(
                IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                        .map(n -> n * 2).summaryStatistics());
    }

    public static void main(String... args) {
        numberStreamToNotNumberStream();
    }
}

// 処理で使うためのレコードクラス宣言
record Pair<T, U>(T first, U second) {
}
