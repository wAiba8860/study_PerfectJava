package com.perfect.java.part2.ClassFile;

public class TopLevelClass { // TopLevelClassファイル内で宣言
    final public String field = "フィールド値"; // privateにするとOtherクラスはコンパイルエラー

    public void method() {
        System.out.println("メソッド呼び出し");
    }

    public String outsideMethod() { // privateにするとOtherクラスがコンパイルエラー
        return "メソッドの返り値";
    }
}
