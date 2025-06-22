package com.perfect.java.part2.ClassFile;

public class Other {
    public void callMethod(TopLevelClass my) { // Other.javaファイルから使用
        my.method();
        System.out.println(my.field);
        String s = my.outsideMethod();
        System.out.println(s);
    }
}
