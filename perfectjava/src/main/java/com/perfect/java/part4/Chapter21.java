package com.perfect.java.part4;

import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Chapter21 {

    // フィールド変数の値が不正になりうる場合
    private static int count = 0;

    void increment() { // 複数のスレッドが同時に実行
        this.count++;
    }

    // synchronizedメソッドの例
    synchronized void increment2() {
        count++;
    }

    static synchronized void increment3() {
        count++;
    }

    // synchronized文の例
    void increment4() {
        synchronized (this) { // this参照の参照先オブジェクトのモニタロックを獲得
            count++;
        } // synchronized文を抜けるとモニタロックを開放
    }

    // クラスのモニタロックを使うsynchronized文
    static void increment5() {
        synchronized (Chapter21.class) {
            count++;
        }
    }

    // synchronizedコードがないため不整合が発生するコード
    static void nonSynchronizedCode() throws InterruptedException, ExecutionException {
        // プラットフォームと仮想スレッドのいずれかを有効化
        ThreadFactory thFactory1 = Thread.ofPlatform().factory();
        ThreadFactory thFactory2 = Thread.ofVirtual().factory();
        ExecutorService executor = Executors.newFixedThreadPool(8, thFactory2);
        var counter = new MyCounter(); // 同じカウンターを共有

        Future<?> ret1 = executor.submit(new MyWorker(counter));
        Future<?> ret2 = executor.submit(new MyWorker(counter));

        // Futureのgetメソッドでサブスレッド終了を待機
        ret1.get();
        ret2.get();
        executor.shutdown();

        // 2つのスレッドがNUM_LOOP回のincrementを呼ぶので、結果は200_000になるはず…
        System.out.println(counter.get());
    }

    // コレクションのsynchronizedコードの例
    static void synchronizedCollection() {
        // 対象オブジェクトをモニタロックに使う書き方
        // List<String> listを複数スレッドで変更する場合
        List<String> list = new ArrayList<>();

        // List<String> listを排他制御対応オブジェクトに変換
        List<String> slist = Collections.synchronizedList(list);

        synchronized (list) {
            list.add("foo");
        }

        // 更新処理が同時に走る可能性があれば、読み取りにもsynchronizedコードが必要
        synchronized (list) {
            String s = list.get(0);
        }

        // 排他制御対応コレクションオブジェクトでも外部に排他制御が必要な例
        String s = "";
        if (!list.contains(s)) { // リストに要素がなければ
            list.add(s); // リストに要素を追加
        }
    }

    public static void main(String... args) {
        try {
            nonSynchronizedCode();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}

// スレッドのエントリポイント
class MyWorker implements Runnable {
    private static final int NUM_LOOP = 100_000; // 増やし過ぎによるint値のオーバーフローに注意
    private final MyCounter counter;

    MyWorker(MyCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        range(0, MyWorker.NUM_LOOP).forEach(n -> {
            counter.increment();
        });
    }

    // MyWorkerのrunメソッドにsynchronized文を追加
    // @Override
    // public void run(){
    // synchronized(counter){
    // range(0, MyWorker.NUM_LOOP).forEach(n -> {
    // counter.increment();
    // });
    // }
    // }

    // MyWorkerのrunメソッドをsynchronizedメソッドに変更（致命的なバグ）
    // public synchronized void run() {
    // range(0, MyWorker.NUM_LOOP).forEach(n -> {
    // counter.increment();
    // });
    // }
}

// 共有データ
class MyCounter {
    private int count = 0;

    // モニタ専用オブジェクト
    private final Object lock = new Object();

    // 実行速度はsynchronizedメソッドを変わらない
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    // クラスのモニタロックを使うsynchronized文（この場合は適切な選択ではない）
    // public void increment() {
    // synchronized (MyCounter.class) {
    // count++;
    // }
    // }

    // もっとも素直な修正
    // MyCounterのincrementメソッドをsynchronizedメソッドにする
    // public synchronized void increment() { // 2つのサブスレッドが同時実行
    // this.count++;
    // }

    public int get() {
        return this.count++;
    }
}

// バグ（Integerが不変クラスであることに注意）
class MyCounterBug {
    private Integer count = 0;

    public void increment() { // 複数スレッドが同時実行
        synchronized (this.count) {
            this.count++; // 新しいIntegerオブジェクトを生成 -> 毎回異なるモニタロックを使う
        }
    }

    public int get() {
        return this.count;
    }
}

// atomicパッケージで書き換え
class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() { // 複数スレッドが同時実行しても不正値にならない
        count.getAndIncrement();
    }

    public int get() {
        return count.get();
    }
}

// デッドロックの例
class MyDeadLock {
    private static final int NUM_LOOP = 100_000; // デッドロックが起きない場合は数値を大きくする

    public static void main(String... args) throws InterruptedException, ExecutionException {
        // 共有データ（ソートのためにArrays.asList利用）
        List<String> list1 = Arrays.asList("one", "two", "three");
        List<String> list2 = Arrays.asList("ONE", "TWO", "THREE");

        // プラットフォームスレッドと仮想スレッドのいずれかを有効化
        ThreadFactory thFactory1 = Thread.ofPlatform().factory();
        ThreadFactory thFactory2 = Thread.ofVirtual().factory();
        ExecutorService executor = Executors.newFixedThreadPool(8, thFactory2);

        Future<?> t1 = executor.submit(() -> { // list1,list2の順序でモニタロックを獲得
            for (int i = 0; i < NUM_LOOP; i++) {
                synchronized (list1) {
                    Collections.sort(list1);
                    synchronized (list2) {
                        Collections.sort(list2);
                    }
                }
            }
        });

        Future<?> t2 = executor.submit(() -> { // list2,list1の順序でモニタロックを獲得
            for (int i = 0; i < NUM_LOOP; i++) {
                synchronized (list2) {
                    Collections.sort(list2);
                    synchronized (list1) {
                        Collections.sort(list1);
                    }
                }
            }
        });

        t1.get();
        t2.get();
        executor.shutdown();

        System.out.println("finished");
    }
}