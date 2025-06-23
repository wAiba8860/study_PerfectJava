package com.perfect.java.part2.ClassFile;

import java.util.Iterator;
import java.util.List;

public class MyRecursive {
    public void dump(Iterator<String> itr) {
        if (itr != null && itr.hasNext()) {
            String s = itr.next();
            System.out.println(s);
            dump(itr);
        }
    }

    public void method() {
        var list = List.of("abc", "def", "ghi", "jkl");
        dump(list.iterator());
    }
}
