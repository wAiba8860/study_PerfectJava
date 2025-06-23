package com.perfect.java.part2.ClassFile;

public class InitializeBlock {
    // 初期化ブロック内のみに唯一の初期化があるフィールド変数はfinal指定が可能
    private final int i;

    // 初期化ブロック
    {
        this.i = 100; // thisは省略可能
        System.out.println(this.i); // 通常のJavaコードを記述可能
    }
}
