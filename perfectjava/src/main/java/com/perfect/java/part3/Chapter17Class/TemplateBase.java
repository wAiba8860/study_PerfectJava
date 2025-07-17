package com.perfect.java.part3.Chapter17Class;

//抽象基底クラス
public abstract class TemplateBase {
    // 派生クラスがオーバーライドすべき抽象メソッド
    // protectedメソッドにするのが定石
    protected abstract void doTask();

    // 骨格実装
    public void templateMethod() {
        System.out.println("共通前処理");
        doTask(); // 派生クラス固有の処理を呼ぶ
        System.out.println("共通後処理");
    }
}

class TemplateMy1 extends TemplateBase {
    @Override
    protected void doTask() {
        System.out.println("TemplateMy1のメソッド");
    }
}

class TemplateMy2 extends TemplateBase {
    @Override
    protected void doTask() {
        System.out.println("TemplateMy2のメソッド");
    }
}

class TemplateBasic {
    public static void main(String[] args) {
        // 使用例
        var my1 = new TemplateMy1();
        my1.templateMethod();

        var my2 = new TemplateMy2();
        my2.templateMethod();
    }
}
