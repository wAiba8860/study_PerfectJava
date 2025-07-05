package com.perfect.java.part2;

public class Chapter13_1 {
    public static void main(String... args) {

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
