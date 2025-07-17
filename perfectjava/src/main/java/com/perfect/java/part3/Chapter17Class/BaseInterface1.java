package com.perfect.java.part3.Chapter17Class;

public interface BaseInterface1 {
    public void method1();
}

interface BaseInterface2 {
    public void method2();
}

interface MyInterface extends BaseInterface1, BaseInterface2 {
    // 概念的には void method1()と void method2()がここにある
}

class My implements MyInterface {
    @Override
    public void method1() {
        // 省略
    }

    @Override
    public void method2() {
        // 省略
    }

    static void interfaceBasic() {
        // 使用例
        // 下記のどちらもok
        BaseInterface1 my = new My();
        MyInterface my2 = new My();
    }
}
