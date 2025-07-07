package com.perfect.java.part2;

public class Chapter13_1 {

    static void printStackTrace() {
        StackTraceElement frames[] = Thread.currentThread().getStackTrace();
        for (StackTraceElement frame : frames) {
            System.out.println(frame.getClassName() + "#" + frame.getMethodName() + ":"
                    + frame.getLineNumber());
        }
    }

    static void method3() {
        printStackTrace();
    }

    static void method2() {
        method3();
    }

    static void method1() {
        method2();
    }

    public static void main(String... args) {
        method1();
    }
}


class Main {
    static {
        System.out.println("1");
    }

    public static void main(String[] args) {
        class My {
            static {
                System.out.println("3");
            }

            My() {
                System.out.println("4");
            }

            void method() {
                System.out.println("5");
            }
        }

        System.out.println("2");
        var obj = new My();
        obj.method();
        System.out.println("6");
    }
}


class PrintStackViaException {
    static void printStackTrace() {
        StackTraceElement frames[] = new Throwable().getStackTrace();
        for (StackTraceElement frame : frames) {
            System.out.println(frame.getClassName() + "#" + frame.getMethodName() + ":"
                    + frame.getLineNumber());
        }
    }

    public static void main(String[] args) {
        // 自作スタックトレース表示処理の呼び出し
        printStackTrace();

        // 下記一行でも表示可能（細かい表示制御はできない）
        new Throwable().printStackTrace();
    }
}
