package com.perfect.java.part2.Chap11classInterface;

//CharSequenceインターフェースを継承した自作クラス
public class MyString implements CharSequence {
    public int length() {
        return 5;
    }

    public char charAt(int index) {
        return 5;
    }

    public CharSequence subSequence(int start, int end) {
        CharSequence ch = null;
        return ch;
    }
}
