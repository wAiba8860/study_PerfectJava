package com.perfect.java.part4;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.stream.IntStream.range;

import java.time.Duration;

public class Chapter20 {

    // プラットフォームスレッドがラムダ式（実態はRunnableオブジェクトのrunメソッド）を実行
    static void threadLambdaExample() {
        var thread = new Thread(() -> {
            System.out.println("新規スレッドから呼ばれる処理");
        });
        thread.start();
    }

    // 仮想スレッドの生成と開始
    static void threadVirtualExample() {
        Thread vThread = Thread.ofVirtual().unstarted(() -> {
            System.out.println("仮想スレッドから呼ばれる処理");
        });
        vThread.start();

        // 生成と開始を下記一行で表記可能
        Thread vThread2 = Thread.ofVirtual().start(() -> {
            System.out.println("仮想スレッドから呼ばれる処理");
        });

        // 仮想スレッドのスタイルに合わせたプラットフォームスレッドの生成と開始
        Thread pThread = Thread.ofPlatform().start(() -> {
            System.out.println("プラットフォームスレッドから呼ばれる処理");
        });
    }

    // 3つの仮想スレッドを生成し実行させる例
    static void threadFactoryExample() {
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread[] vThreads = new Thread[3]; // スレッドを保持する配列

        for (int i = 0; i < 3; i++) {
            Thread vThread = factory.newThread(() -> {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    // TODO: handle exception
                }

                System.out.println("仮想スレッドが呼び出される処理");
            });
            vThreads[i] = vThread; // スレッドを配列に格納
            vThread.start();
        }

        // すべての仮想スレッドが完了するのを待つ
        for (Thread vThread : vThreads) {
            try {
                vThread.join();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    // 仮想スレッドの指定（スレッドプール）
    static void virtualThreadPoolBasic() {
        ThreadFactory factory = Thread.ofVirtual().factory();
        ExecutorService executor = Executors.newFixedThreadPool(8, factory);
    }

    public static void main(String[] args) {
        threadFactoryExample();
    }
}

// 複数スレッドの実例
class ThreadMain {
    // インデント省略のためにmainメソッドにthrows節を記述
    public static void main(String... args) throws InterruptedException {
        // プラットフォームスレッドと仮想スレッドのいずれかを有効化
        // プラットフォームスレッド
        ThreadFactory thFactory1 = Thread.ofPlatform().name("worker", 0).factory();

        // 仮想スレッド
        ThreadFactory thFactory2 = Thread.ofVirtual().name("worker", 0).factory();

        var thread0 = thFactory1.newThread(() -> {
            range(0, 1000).forEach(n -> {
                System.out.printf("%s, %d\n", Thread.currentThread().getName(), n);
            });
        });

        var thread1 = thFactory1.newThread(() -> {
            range(0, 1000).forEach(n -> {
                System.out.printf("%s, %d\n", Thread.currentThread().getName(), n);
            });
        });

        // サブスレッドの開始
        thread0.start();
        thread1.start();

        range(0, 1000).forEach(n -> {
            System.out.printf("メインスレッド, %d\n", n);
        });

        // サブスレッド終了を待機（引数でタイムアウト値を指定(10秒間)）
        thread0.join(Duration.ofSeconds(10));
        thread1.join(Duration.ofSeconds(10));
    }
}

// スレッドプールの例
class ThreadPoolMain {
    public static void main(String... args) throws InterruptedException {
        // プラットフォームスレッドと仮想スレッドのいずれかを有効化
        ThreadFactory thFactory1 = Thread.ofPlatform().factory();
        ThreadFactory thFactory2 = Thread.ofVirtual().factory();

        // スレッド数は任意です。1でも可
        ExecutorService executor = Executors.newFixedThreadPool(8, thFactory2);

        Future<?> ret1 = executor.submit(new Worker("スレッド1"));
        Future<?> ret2 = executor.submit(new Worker("スレッド2"));

        range(0, 1000).forEach(n -> {
            System.out.printf("メインスレッド, %d\n", n);
        });

        executor.shutdown();
        // サブスレッド終了を待機（引数でタイムアウト値を設定（10秒間））
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

// スレッドのタスク
class Worker implements Runnable {
    // サブスレッド識別用の文字列
    private final String name;

    Worker(String name) {
        this.name = name;
    }

    // スレッドのエントリポイント
    @Override
    public void run() {
        range(0, 1000).forEach(n -> {
            System.out.printf("%s, %d\n", this.name, n);
        });
    }
}

class ThreadPoolMainCallable {
    public static void main(String... args) throws InterruptedException, ExecutionException, TimeoutException {
        // プラットフォームスレッドと仮想スレッドのいずれかを有効化
        ThreadFactory factory1 = Thread.ofPlatform().factory();
        ThreadFactory factory2 = Thread.ofVirtual().factory();

        // スレッド数は任意です。１でも動きます。
        ExecutorService executor = Executors.newFixedThreadPool(8, factory2);

        Future<Integer> ret1 = executor.submit(new WorkerCallable("スレッド１"));
        Future<Integer> ret2 = executor.submit(new WorkerCallable("スレッド２"));

        // Futureオブジェクトのgetメソッドでサブスレッドの返り値を取得
        // サブスレッド実行中、getメソッドは待機（引数でタイムアウト値を指定（10秒間））
        System.out.println(ret1.get(10, TimeUnit.SECONDS));
        System.out.println(ret2.get(10, TimeUnit.SECONDS));

        executor.shutdown();
        // サブスレッド終了を待機（引数でタイムアウト値を指定（10秒間））
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

// スレッドのタスク
class WorkerCallable implements Callable<Integer> {
    // サブスレッド識別用の文字列
    private final String name;

    WorkerCallable(String name) {
        this.name = name;
    }

    // スレッドのエントリポイント
    @Override
    public Integer call() throws Exception {
        return range(0, 1000).peek(n -> {
            System.out.printf("%s, %d\n", this.name, n);
        }).sum();
    }
}

class ThreadSleepVirtual {
    public static void main(String... args) throws InterruptedException {
        int NUM_THREADS = 100_000;
        ThreadFactory pfFactory = Thread.ofPlatform().factory();
        ThreadFactory vrtFactory = Thread.ofVirtual().factory();

        // プラットフォームスレッド
        var pfThreads = new ArrayList<Thread>(NUM_THREADS);
        long pfStart = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = pfFactory.newThread(() -> {
                emulateLongTask(Duration.ofSeconds(5));
            });
            pfThreads.add(thread);
            thread.start();
        }

        for (Thread thread : pfThreads) {
            thread.join();
        }

        System.out.println("Platform Thread: " + (System.currentTimeMillis() - pfStart) + "(ms)");

        // 仮想スレッド
        var vrtThreads = new ArrayList<Thread>(NUM_THREADS);
        long vrtStart = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = vrtFactory.newThread(() -> {
                emulateLongTask(Duration.ofSeconds(5));
            });
            vrtThreads.add(thread);
            thread.start();
        }

        for (Thread thread : vrtThreads) {
            thread.join();
        }

        System.out.println("Virtual Thread: " + (System.currentTimeMillis() - vrtStart) + "(ms)");
    }

    // Thread.sleepで待機処理を模倣
    private static void emulateLongTask(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            // TODO: handle exception
            // 無視
        }
    }
}
