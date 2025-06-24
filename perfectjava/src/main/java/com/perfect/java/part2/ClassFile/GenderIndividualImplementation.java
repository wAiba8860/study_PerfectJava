package com.perfect.java.part2.ClassFile;

public enum GenderIndividualImplementation {
    MAN("Man") {
        @Override // 個別実装をオーバーライドして実装
        public void method() {
            System.out.println("otoko");
        }
    },
    WOMAN("Woman") {
        @Override
        public void method() {
            System.out.println("onna");
        }
    },
    OTHER("Other") {
        @Override
        public void method() {
            System.out.println("fumei");
        }
    };

    // 個別実装したいメソッドを抽象メソッドで用意する
    public abstract void method();

    // コンストラクタの引数をvalueフィールドでうけてtoStringで返すのはenum型の定石
    private final String value;

    GenderIndividualImplementation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
