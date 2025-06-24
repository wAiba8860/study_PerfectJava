package com.perfect.java.part2.ClassFile;

public enum GenderDatabase {
    // コンストラクタを使うenum定数
    MAN("Man", 0),
    WOMAN("Woman", 1),
    OTHER("Other", 2); // 後続がある場合はセミコロン文字で終端する

    // 任意のフィールド変数を追加可能。アクセス制御修飾子は通常のクラスフィールドと同じ意味
    private final String nameForDisplay; // 表示用文字列
    private final int valueForDb; // データベース保存用数値

    // コンストラクタ（暗黙的にprivate）
    GenderDatabase(String nameForDisplay, int valueForDb) {
        this.nameForDisplay = nameForDisplay;
        this.valueForDb = valueForDb;
    }

    // 必要に応じてtoStringメソッドをオーバーライドするのはenum型の定石
    @Override
    public String toString() {
        return this.nameForDisplay;
    }

    // 任意のメソッドを追加可能
    public int toDatabaseValue() {
        return this.valueForDb;
    }

    // サンプルコード以外ではあまりしませんがmainメソッドも普通に持てます
    public static void main(String... args) {
        System.out.println(MAN); // 暗黙のtoStringメソッド呼び出し
        System.out.println(MAN.name()); // 常にenum定数と同名の文字列
        System.out.println(MAN.toDatabaseValue()); // toDatabaseValueの結果は数値の0
    }
}
