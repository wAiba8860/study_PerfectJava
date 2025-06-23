package com.perfect.java.part2.ClassFile;

public class Adder {
    private int score = 0; // 値を変えるので非finalにします

    // メソッド定義
    public void addScore(int delta) { // deltaは仮引数
        this.score += delta; // thisは省略可能
        System.out.println("score is %d".formatted(this.score)); // thisは省略可能
    }

    public int addScoreReturn(int delta) {
        this.score += delta;
        // return this.score;

        // 複数のreturn文
        if (this.score > 1000) {
            return this.score;
        } else {
            return 0;
        }
        // ここに文を書くとコンパイルエラー 上記どちらかのreturn文が必ず使われるため
        // System.out.println("error");
    }

    public StringBuilder getStringBuilder() {
        var sb = new StringBuilder("abc");
        sb.append("def");
        return sb;
    }

    // コンパイルエラー
    // void method(String s1){}
    // void method(String s2){}

    // メソッドのオーバーロード
    public void method(String s) {
        System.out.println("String parameter is %s".formatted(s));
    }

    public void method(StringBuilder sb) {
        System.out.println("StringBuilder parameter is %s".formatted(sb));
    }
}
