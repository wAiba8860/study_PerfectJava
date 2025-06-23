package com.perfect.java.part2.ClassFile;

public class StaticInitial {
    // クラスの初期化時は、下記の数字の順に実行します
    static final int I = 1;

    static {
        System.out.println("2");
        // this.I = 2; エラー
    }

    static final int j = 3;
}
