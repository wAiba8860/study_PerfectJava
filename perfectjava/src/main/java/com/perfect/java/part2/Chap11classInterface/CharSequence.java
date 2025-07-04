package com.perfect.java.part2.Chap11classInterface;

public interface CharSequence {
    int length();

    char charAt(int index);

    CharSequence subSequence(int start, int end);
}
