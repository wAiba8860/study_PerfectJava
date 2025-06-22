package com.perfect.java.part2.ClassFile;

public class ThisMethodExample {
    public void callMethod() {
        String s = this.method();
        System.out.println(s);
    }

    private String method() {
        return "メソッドの返り値";
    }
}
