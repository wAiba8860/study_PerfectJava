package com.perfect.java.part2;

import com.perfect.java.part2.ClassFile.GenderIndividualImplementation;

//enum型宣言
enum Gender {
    MAN, WOMEN, OTHER,
}

public class Chapter7_2 {
    // クラス内の定数（クラス内でのみ使用）の例
    private static final int DEFAULT_VALUE = 16; // 定数

    static void method() {
        System.out.println(Chapter7_2.DEFAULT_VALUE); // Chapter7_2.は省略可。
    }

    static void constantNumberExample() {
        final int DEFAULT_VALUE = 16; // 定数
        System.out.println(DEFAULT_VALUE);
    }

    static void nonEnum() {
        final int MAN = 0;
        final int WOMEN = 1;
        final int OTHER = 2;

        // 使用例
        int gender = MAN; // 意図した使用

        // どこかで誰かが書くかもしれないコード
        // 使用目的から外れた使い方だがコンパイルできてしまう
        gender = -1;
    }

    static void useEnum() {
        // 使用例
        // Gender型の変数にGender.MANを代入可能
        Gender gender1 = Gender.MAN;
        System.out.println(gender1);

        // Gender型の変数に数値を代入しようとするとコンパイルエラー
        // Gender gender2 = -1;

        // Gender型の変数に文字列を代入しようとするとコンパイルエラー
        // Gender gender3 = "MAN";
    }

    static void enumMethod(Gender gender) {
        // enum型の使用例
        if (gender == Gender.MAN) {
            System.out.println("he is the man");
        }
    }

    static void getEnumValue() {
        Gender gender = Gender.MAN;

        // nameメソッドでenum定数から文字列"MAN"を取得
        System.out.println(gender.name());

        // Gender.valueOfで文字列"MAN"からenum定数を取得
        Gender man = Gender.valueOf("MAN");
        System.out.println(man);

        // 変数manの参照先はenum定数
        System.out.println(man == gender);

        // 相互変換ができるだけでenum定数と文字列は別物です
        // boolean result = man == "MAN";

        System.out.println(man.equals("MAN"));

        for (Gender gender2 : Gender.values()) {
            System.out.println(gender2);
        }
    }

    // 個別実装の使用例
    static void enumGenderBasic() {
        var man = GenderIndividualImplementation.MAN;
        man.method();
    }

    public static void main(String[] args) {
        enumGenderBasic();
    }
}
