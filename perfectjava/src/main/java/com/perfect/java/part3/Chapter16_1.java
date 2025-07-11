package com.perfect.java.part3;

import java.util.ArrayList;

public class Chapter16_1 {

    static void doubleExample() {
        // double型変数numに値1.23を代入
        // ローカル変数であればvarも使用可能。ここでは変数の型を明示
        double num = 1.23;
        System.out.println(num);

        // 変数numの値を2倍にして変数num2に代入
        double num2 = num * 2;
        System.out.println(num2);
    }

    static void maxMinValue() {
        // floatの取り扱える値の最大値と最小値
        final float MAX_VALUE = 0x1.fffffeP+127f;
        System.out.println(MAX_VALUE);
        final float MIN_VALUE = 0x0.000002P-126f;
        System.out.println(MIN_VALUE);

        // doubleの取り扱える値の最大値と最小値
        final double MAX_VALUE_DOUBLE = 0x1.fffffffffffffP+1023;
        System.out.println(MAX_VALUE_DOUBLE);
        final double MIN_VALUE_DOUBLE = 0x0.0000000000001P-1022;
        System.out.println(MIN_VALUE_DOUBLE);
    }

    static void approximateValue() {
        double num = 0.1d + 0.2d;
        System.out.println(num);
    }

    static void floatingPointNumbersEqual() {
        double num1 = 0.1;
        float num2 = 0.1f;

        // 見た目はどちらも0.1だが等価ではない
        System.out.println(num1 == num2);
    }

    static void doubleNaN() {
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.err.println(Double.NaN);
    }

    static void overflowingDigitsDouble() {
        // MAX_VALUEに数値を加算しても普通はMAX_VALUEのまま
        double n = Double.MAX_VALUE + 9999999;
        System.out.println(n == Double.MAX_VALUE);

        // MAX_VALUEを超えてそれ以上の演算の意味を失う場合、POSITIVE_INFINITYになる
        double n2 = Double.MAX_VALUE + 1e292;
        System.out.println(n2 == Double.POSITIVE_INFINITY);
    }

    static void calculationInfinityDouble() {
        // ゼロ除算の結果は無限大。除数の0を0.0と記載しても同じ結果（以後も同様）
        double infi = 1.0 / 0;
        System.out.println(infi);

        // 無限大に0以外の数値で四則演算しても結果は無限大。下記MAX_VALUEの代わりに任意の非ゼロの数値でも同じ結果
        double infi2 = infi * Double.MAX_VALUE;
        System.out.println(infi2);

        // 無限大と0の乗算はNaN
        double infi3 = infi * 0;
        System.out.println(infi3);

        // 無限大同士の演算結果は真
        System.out.println(infi == infi2);

        // 無限大同士の加算の結果は無限大（乗算も同様）
        double infi4 = infi + infi;
        System.out.println(infi4);

        // 無限大同士の減算の結果はNaN（除算も同様）
        double nan1 = infi - infi;
        System.out.println(nan1);
    }

    static void doubleNaNEquals() {
        // NaNの演算結果は常にNaN

        double nan = Double.NaN;

        // NaN同士の等値演算は偽
        System.out.println(nan == nan);

        // NaN同士の非等値演算は真
        System.out.println(nan != nan);

        // Double.isNaNメソッドでNaN判定可能
        System.out.println(Double.isNaN(nan));
    }

    static void castDrop() {
        // 浮動小数点数から整数への変換（切り捨て）
        double num = 3.9999;
        System.out.println((int) num);

        // 浮動小数点数から整数への変換（数値が変わる）
        double di = Integer.MAX_VALUE * 2d;
        System.out.println(di);

        // 数値が変わる。符号は維持
        int i = (int) di;
        System.out.println(i);

        // 精度の損失の例（整数から浮動小数点数への変換）
        // 16777217 = 2の24乗 + 1
        int i2 = 16777217;
        float num2 = i2;
        System.out.println(num2);
    }

    static void numericalPromotion() {
        // 数値昇格しない例
        int i = Integer.MAX_VALUE;

        // int同士の演算扱い（数値昇格していない）
        long li = i * 2;
        System.out.println(li);

        // 片方のオペランドをlong型にすると数値昇格する
        long li2 = i * 2L;
        System.out.println(li2);
    }

    static void numberObjectGenerate() {
        Integer i = 7; // ボクシング変換。通常はこれを使う。
        Integer i2 = Integer.valueOf(7); // 問題のないオブジェクト生成方法
        Integer i3 = new Integer(7); // 推奨しないオブジェクト生成方法（動作に支障はない）
    }

    static void numberObjectCalculate() {
        Integer i1 = 100;
        Integer i2 = 210;
        System.out.println(i1 + i2);

        // 小数点以下は切り捨て
        Integer div = i2 / i1;
        System.out.println(div);

        Integer div2 = i1 / i2;
        System.out.println(div2);

        // 数値0（ゼロ）による除算はArithmeticException実行時例外
        // Integer zero = 0;
        // Integer div3 = i1 / zero;

        // オペランドがnullの場合、NullPointerException実行時例外
        Integer iNull = null;
        Integer sum = i1 + iNull;
    }

    static void boxingConversion() {
        // Integer型要素のArrayList
        var list = new ArrayList<Integer>();

        // コード上、基本型の数値をそのままArrayListに追加できる
        // ArrayListの内部ではボクシング変換で数値オブジェクトを使用
        list.add(1);
        list.add(Integer.valueOf(2));
        list.add(3);
        System.out.println(list);

        // 数値オブジェクトのまま要素取り出し
        // 代入先の変数の型をvar指定（アンボクシング未発生）
        var i = list.get(0);

        // 変数iの型はInteger
        var clazz = i.getClass();
        System.out.println(clazz);
    }

    static void nullValueBoolean(Boolean b) {
        if (b == null) {
            System.out.println("null");
        } else if (b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    static void numberObjectEquals() {
        // たまたま==演算で数値オブジェクトを同値判定できる例
        Integer i0 = 1;
        Integer i1 = 1;

        // 結果は常に真だが使用は危険（i0.equals(i1)のほうが良い）
        System.out.println(i0 == i1); // true

        // ==演算で数値オブジェクトを同値判定できない例
        Integer i2 = 1000;
        Integer i3 = 1000;

        System.out.println(i2 == i3); // false

        // 基本的には問題ない判定
        int i4 = 1000;
        System.out.println(i3 == i4);

        // equalsメソッドのほうが防衛的
        System.out.println(i3.equals(i4));
    }

    static void boxingConversionEquals() {
        // レコードクラスではなくクラスでも同様です
        record MyRecord(int val) {
        }

        var my1 = new MyRecord(1234);
        var my2 = new MyRecord(1234);

        System.out.println(my1.val() == my2.val());

        // 変更後のコード
        // レコードコンポーネントの型をintからInteger型に変更
        record MyRecordInteger(Integer val) {

        }

        var myInteger1 = new MyRecordInteger(1234);
        var myInteger2 = new MyRecordInteger(1234);

        // 意図せず結果が変わってしまう
        // equalsメソッドを使うように書き換えなければならない
        System.out.println(myInteger1 == myInteger2);
        System.out.println(myInteger1.equals(myInteger2));
    }

    static void numberObjectCompare() {
        Integer i0 = 100;
        Integer i1 = 99;

        System.out.println(i0 > i1);

        // 返り値の意味は「3-4-2 文字列の大小比較とソート処理」参照
        int result = i0.compareTo(i1);
        System.out.println(result);
    }

    public static void main(String[] args) {
        numberObjectCompare();
    }
}
