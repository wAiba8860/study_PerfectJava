package com.perfect.java.part2;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;

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

    public static void main(String[] args) {
        setObjectExample();
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

    public static void main(String... args) {
        executeByDay(DayOfWeek.FRIDAY);
        executeByDayMap(DayOfWeek.SATURDAY);
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
