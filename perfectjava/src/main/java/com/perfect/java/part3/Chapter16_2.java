package com.perfect.java.part3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.BitSet;

public class Chapter16_2 {

    static void bitCalculate() {
        byte b = 0b010 + 0b111; // 2進数表記
        int i = 2 + 7; // 上記と同じ値を10進数表記
        System.out.println(b + " : " + i);
    }

    // ビット演算を使うビットフラグ
    static void bitFlagExample() {
        // 全てのフラグが落ちた初期値
        int bitFlag = 0;

        // 3ビット目（右から3番目のビット。0始まり。以後同様）のフラグを立てる
        // 下記の（1<<3）は0b1000のリテラル表記でも記述可能（以後も同様）
        bitFlag |= (1 << 3);

        // 3ビット目のフラグチェック（立っている）
        System.out.println((bitFlag & (1 << 3)) != 0);

        // 2ビット目のフラグを立てる
        bitFlag |= (1 << 2);

        // 2ビット目のフラグチェック（立っている）
        System.out.println((bitFlag & (1 << 2)) != 0);

        // 2ビット目のフラグを降ろす
        // 2ビット目のみ0の数値は、2ビット目のみ1にした数値の~演算で作成
        bitFlag &= ~(1 << 2);

        // 2ビット目のフラグチェック（降りている）
        System.out.println((bitFlag & (1 << 2)) != 0);

        // 3ビット目のフラグ値の反転
        bitFlag ^= (1 << 3);

        // 3ビット目のフラグチェック（降りている）
        System.out.println((bitFlag & (1 << 3)) != 0);
    }

    static void bitSetBitFlag() {
        // 4ビットのビットフラグとしてBitSetオブジェクトを生成
        var bitFlag = new BitSet(4);

        // 3ビット目のフラグを立てる
        bitFlag.set(3);

        // 3ビット目のフラグチェック（立っている）
        System.out.println(bitFlag.get(3));

        // 2ビット目のフラグを立てる
        bitFlag.set(2);

        // 2ビット目のフラグチェック（立っている）
        System.out.println(bitFlag.get(2));

        // 2ビット目のフラグを降ろす
        bitFlag.clear(2);

        // 2ビット目のフラグチェック（降りている）
        System.out.println(bitFlag.get(2));

        // 3ビット目のフラグ値の反転
        bitFlag.flip(3);

        // 3ビット目のフラグチェック
        System.out.println(bitFlag.get(3));
    }

    static void intToLongTypeConversion() {
        // ビット長を拡張する変換
        final long LONG_MASK = 0xffffffffL;

        int x = 9;

        // 変数xの型はint
        // intからlongへの型変換
        long lx = x & LONG_MASK;
    }

    static void bigIntegerCalculateExample() {
        var num1 = BigInteger.valueOf(7);
        var num2 = BigInteger.valueOf(10);

        // 加算
        System.out.println(num1.add(num2));

        // BigIntegerオブジェクトの除算
        // 10を3で割った結果は3
        BigInteger result = BigInteger.valueOf(10).divide(BigInteger.valueOf(3));
        System.out.println(result);

        // results[0]の値は3。results[1]の値は1
        BigInteger results[] = BigInteger.valueOf(10).divideAndRemainder(BigInteger.valueOf(3));
        System.out.println(Arrays.toString(results));

        // ゼロ除算はArithmeticException実行時例外
        BigInteger result2 = BigInteger.valueOf(10).divide(BigInteger.valueOf(0));
    }

    static void bigDecimalObjectGenerate() {
        // 内部的に小数点以下2桁
        var num1 = new BigDecimal("1.00");
        System.out.println(num1);

        // 内部的に小数点以下1桁
        var num2 = BigDecimal.valueOf(1.00);
        System.out.println(num2);

        // num1とnum2のequals判定は偽
        System.out.println(num1.equals(num2));
    }

    static void bigDecimalCalculate() {
        // BigDecimalの演算
        var num1 = new BigDecimal("1.024");
        var num2 = new BigDecimal("10.24");
        System.out.println(num1.add(num2));

        // BigDecimalの減算と乗算
        // 内部的には精度1、スケール0
        var num3 = new BigDecimal("1");
        // 内部的には精度1、スケール8
        var num4 = new BigDecimal("0.00000001");

        // 減算
        BigDecimal result = num3.subtract(num4);
        System.out.println(result);

        // resultは内部的に精度8、スケール8
        int precision = result.precision();
        System.out.println(precision);
        int scale = result.scale();
        System.out.println(scale);

        // 乗算
        BigDecimal result2 = result.multiply(result);
        System.out.println(result2);

        // result2は内部的に精度16、スケール16
        int precision2 = result2.precision();
        System.out.println(precision2);
        int scale2 = result2.scale();
        System.out.println(scale2);
    }

    // BigDecimalオブジェクトの比較
    static void bigDecimalCompare() {
        var num1 = new BigDecimal("1.0");
        var num2 = new BigDecimal("1.00");

        // 1.0と1.00は桁が異なるのでequalsは偽
        System.out.println(num1.equals(num2));

        // 数値の同値判定比較のみであればcompareToを使う
        if (num1.compareTo(num2) == 0) {
            System.out.println("num1とnum2は同値");
        }
    }

    static void bigDecimalRoundingOperation() {
        // BigDecimalの丸め操作（四捨五入）
        var num = new BigDecimal("12.3456");

        // 四捨五入して小数点以下1桁まで求める。小数点以下2桁目の数値4を四捨五入
        var result1 = num.setScale(1, RoundingMode.HALF_UP);
        System.out.println(result1);

        // 四捨五入して小数点以下2桁まで求める。小数点以下3桁目の数値5を四捨五入
        var result2 = num.setScale(2, RoundingMode.HALF_UP);
        System.out.println(result2);

        // 負数の四捨五入
        var minusNum = new BigDecimal("-12.3456");
        var resultMinus1 = minusNum.setScale(1, RoundingMode.HALF_UP);
        System.out.println(resultMinus1);
        var resultMinus2 = minusNum.setScale(2, RoundingMode.HALF_UP);
        System.out.println(resultMinus2);

        // BigDecimalの丸め操作（五捨四入相当）
        // 一般的な「小数点以下3桁目の五捨四入」と同じ結果
        var result3 = new BigDecimal("12.345").setScale(2, RoundingMode.HALF_DOWN);
        System.out.println(result3);

        // 一般的な「小数点以下3桁目の五捨四入」同じ結果
        var result4 = new BigDecimal("12.346").setScale(2, RoundingMode.HALF_DOWN);
        System.out.println(result4);

        // 一般的な五捨四入とは異なる結果。内部的に小数点以下3桁目より後ろを含めた判定をするため
        // 下記例の場合：12.3451は12.34より12.35に近いので12.35に丸める
        var result5 = new BigDecimal("12.3451").setScale(2, RoundingMode.HALF_DOWN);
        System.out.println(result5);

        // BigDecimalの丸め操作（偶数丸め相当）
        // 小数点以下3桁目の5の判定動作：12.33と12.34から等距離。小数点以下2桁目が偶数の12.34に丸める
        var result6 = new BigDecimal("12.335").setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(result6);

        // 小数点以下3桁目の5の判定動作：12.33と12.34から等距離。小数点以下2桁目が偶数の12.34に丸める
        var result7 = new BigDecimal("12.345").setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(result7);

        // HALF_EVEN同様、判定は小数点以下3桁目より後ろを含めた判定
        // 下記例の場合：12.3451は12.34より12.35に近いので12.35に丸める
        var result8 = new BigDecimal("12.3451").setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(result8);
    }

    static void bigDecimalRoundHalf() {
        // 有効桁数を指定した四捨五入
        var num = new BigDecimal("12.3456");

        // 四捨五入して有効桁数3桁にする。小数点以下2桁目の数値4を四捨五入
        var result = num.round(new MathContext(3, RoundingMode.HALF_EVEN));
        System.out.println(result);

        // 四捨五入して有効桁数4桁にする。小数点以下3桁目の数値5を四捨五入
        var result2 = num.round(new MathContext(4, RoundingMode.HALF_EVEN));
        System.out.println(result2);
    }

    static void bigDecimalDivide() {
        // 除算結果が無限小数（ArithmeticException）
        var num1 = new BigDecimal("10");
        var num2 = new BigDecimal("3");

        BigDecimal result = num1.divide(num2);
    }

    static void bigDecimalDivideTermination() {
        // 除算結果の打ち切り結果
        // 四捨五入して小数点以下3桁まで求める。小数点以下4桁目の数値3を四捨五入
        var num1 = new BigDecimal("10");
        var num2 = new BigDecimal("3");

        BigDecimal result1 = num1.divide(num2, 3, RoundingMode.HALF_EVEN);
        System.out.println(result1);

        // 四捨五入して有効桁数3桁にする。小数点以下3桁目の数値3を四捨五入
        BigDecimal result2 = num1.divide(num2, new MathContext(3, RoundingMode.HALF_UP));
        System.out.println(result2);
    }

    public static void main(String... args) {
        bigDecimalDivideTermination();
    }
}
