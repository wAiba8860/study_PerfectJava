package com.perfect.java.part2.ClassFile;

public class FieldVariableAccess {
    private final String variable1 = "フィールド値1";
    private final String variable2 = "フィールド値2";

    public void method(String variable1) {
        String variable2 = "bar";
        System.out.println(variable1); // パラメータ変数
        System.out.println(variable2); // ローカル変数
        System.out.println(this.variable1); // フィールド変数
        System.out.println(this.variable2); // フィールド変数
    }
}
