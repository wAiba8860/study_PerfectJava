package com.perfect.java.part2.ClassFile;

public class ApplicationContext {
    static final int version = 1;
    static final String appName = "sample";

    static void start() {
    }

    public static void main(String... args) {
        System.out.println("application version is " + ApplicationContext.version);
        ApplicationContext.start();
    }
}

class ApplicationContextObject {
    final int version = 1;
    final String appName = "sample";

    void start() {
    }

    public static void main(String[] args) {
        ApplicationContextObject app = new ApplicationContextObject();
        System.out.println("application version is " + app.version);
        app.start();
    }
}
