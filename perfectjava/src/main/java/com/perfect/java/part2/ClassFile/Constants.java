package com.perfect.java.part2.ClassFile;

public interface Constants {
    static final int DEFAULT_VALUE = 16; // 定数
}

// 定数の使用例
class My implements Constants {
    void method() {
        System.out.println(DEFAULT_VALUE);
    }
}
