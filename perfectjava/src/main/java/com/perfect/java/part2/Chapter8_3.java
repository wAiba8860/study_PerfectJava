package com.perfect.java.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Chapter8_3 {

    static void arrayExample() {
        int[] arr = new int[3];
        arr[0] = 10;
        arr[1] = 11;
        arr[2] = 12;
        System.out.println(Arrays.toString(arr));

        // 下記の記述も可能
        // C言語の気泡との親和性のための記法です。使用は非推奨。
        int array[] = new int[3];

        // 配列の書き換えは可能
        arr[0] = 123;
        System.out.println(arr);

        // 要素数の増減はコンパイルエラー
        // The final field array.length cannot be assigned
        // arr.length = 4;

        // 参照型要素の配列
        var arrSt = new String[3];
        arrSt[0] = "foo";
        arrSt[1] = "bar";
        arrSt[2] = "baz";
    }

    static void stringBuilderArray() {
        var arr = new StringBuilder[2];

        // arr[0]とarr[1]が同じオブジェクトを参照
        arr[0] = new StringBuilder("abc");
        arr[1] = arr[0];

        // arr[0]の参照先オブジェクトを変更
        arr[0].append("def");

        // arr[1]から変更が見える
        System.out.println(Arrays.toString(arr));
    }

    static void assignmentArray() {
        int[] arr = new int[3];
        arr[0] = 0;
        // 下記はコンパイルエラー (int n = "abc"と同種のエラー)
        // arr[1] = "abc";

        // 参照型要素の配列
        CharSequence[] arrCh = new CharSequence[3];

        // 下記はCharSequence s = "abc"と同様に問題なし
        arrCh[0] = "abc";

        // 下記はCharSequence s = new StringBuilder("def")と同様に問題なし
        arrCh[1] = new StringBuilder("def");
    }

    static void arrayInitial() {
        int[] arr = {0, 1, 2}; // 配列初期化子

        // 下記はコンパイルエラー
        // Array initializer needs an explicit target-type
        // var arrE = { 0, 1, 2 };

        // 要素がStringオブジェクト
        String[] arrSt = {"abc", "def"};

        // 要素がStringBuilderオブジェクト
        StringBuilder[] arrSb = {new StringBuilder("abc"), new StringBuilder("def")};

        // 余計なカンマは無視
        int[] arrCom = {0, 1, 2,};
        System.out.println(arrCom.length);

        // 宣言時以外の配列の生成
        // 配列を引数で受け取るメソッド（本体は省略）
        // void method(int[] arr){}

        // 上記メソッドの引数に初期化した配列を渡す構文
        arrayMethod(new int[] {0, 1, 2});
    }

    static void arrayMethod(int[] arr) {}

    static void enumerationArray() {
        int[] arr = {10, 20, 30};

        // 拡張for構文
        for (var value : arr) {
            System.out.println(value);
        }

        // インデックス使用
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    static void arraySort() {
        // 要素が基本型の配列は昇順でソート
        int[] arr = {10, 2, 3, 1, 5};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        // 要素が参照型の配列は比較処理用メソッドを渡せます
        // 比較処理用メソッドを渡さない場合、要素のcompareToメソッドを使います
        // 下記コードは降順のソートです
        // ボクシング変換とアンボクシング変換が起きるので効率的ではありません
        Integer[] arrIntegers = {10, 2, 3, 1, 5};

        // 第2引数はラムダ式
        Arrays.sort(arrIntegers, (n1, n2) -> {
            return n2 - n1;
        });
        System.out.println(Arrays.toString(arrIntegers));
    }

    static void equivalenceArray() {
        int[] arr1 = {0, 1, 2};
        int[] arr2 = {0, 1, 2};

        // 等値演算で要素の同値判定はできない
        System.out.println(arr1 == arr2);

        // equalsメソッドで要素の同値判定はできない
        System.out.println(arr1.equals(arr2));

        // Arrays.equalsは要素の同値判定ができる
        System.out.println(Arrays.equals(arr1, arr2));

        // Arrays.compareは要素の大小比較ができる（結果が0であれば同値）
        System.out.println(Arrays.compare(arr1, arr2));
    }

    static void threeDimensionalArrayExample() {
        int[][][] arr = new int[2][][];
        arr[0] = new int[2][];
        arr[1] = new int[2][];
        arr[0][0] = new int[2];
        arr[0][1] = new int[2];
        arr[1][0] = new int[2];
        arr[1][1] = new int[2];
        arr[0][0][0] = 0b000;
        arr[0][0][1] = 0b001;
        arr[0][1][0] = 0b010;
        arr[0][1][1] = 0b011;
        arr[1][0][0] = 0b100;
        arr[1][0][1] = 0b101;
        arr[1][1][0] = 0b110;
        arr[1][1][1] = 0b111;

        // 上記を簡易化
        int[][][] arr2 = new int[2][2][2];
        arr2[0][0][0] = 0b000;
        arr2[0][0][1] = 0b001;
        arr2[0][1][0] = 0b010;
        arr2[0][1][1] = 0b011;
        arr2[1][0][0] = 0b100;
        arr2[1][0][1] = 0b101;
        arr2[1][1][0] = 0b110;
        arr2[1][1][1] = 0b111;

        int[][][] arr3 = {{{0b000, 0b001}, {0b010, 0b011},}, {{0b000, 0b001}, {0b010, 0b011},},};

        // 3次元配列のすべての要素の列挙コード
        // 拡張for構文
        for (int[][] first : arr) {
            for (int[] second : first) {
                for (int n : second) {
                    System.out.println(n);
                }
            }
        }

        // インデックス使用
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                for (int k = 0; k < arr[i][j].length; k++) {
                    System.out.println(arr[i][j][k]);
                }
            }
        }
    }

    static void arraysCopyOf() {
        int[] src = {1, 2, 3};
        // 配列srcの要素を配列destにコピー
        int[] dest = Arrays.copyOf(src, src.length);
        System.out.println(Arrays.toString(dest));
    }

    static void arrayAsList() {
        // 配列からオブジェクトに変換
        String[] src = {"abc", "def", "ghi"};
        List<String> dest = Arrays.asList(src);
        System.out.println(dest);

        // Listオブジェクトに対する追加と削除は実行時例外
        dest.add("xyz");
    }

    static void arrayToListShallow() {
        String[] src = {"abc", "def", "ghi"};
        List<String> dest = new ArrayList<>();
        Collections.addAll(dest, src);
        System.out.println(dest);
    }

    static void listToArray() {
        List<String> src = List.of("abc", "def", "ghi");
        String[] dest = src.toArray(String[]::new); // String[]::newはメソッド参照
        System.out.println(Arrays.toString(dest));

        // 少し古い書き方
        String[] dest2 = src.toArray(new String[src.size()]);
    }


    public static void main(String[] args) {
        listToArray();
    }
}
