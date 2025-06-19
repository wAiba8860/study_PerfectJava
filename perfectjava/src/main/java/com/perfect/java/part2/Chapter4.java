package com.perfect.java.part2;

public class Chapter4 {

    public static void stringReference() {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append("012"));
    }

    public static void main(String... args) {
        stringReference();
    }
}
