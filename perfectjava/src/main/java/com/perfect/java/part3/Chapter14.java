package com.perfect.java.part3;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Chapter14 {

    void exceptionPseudocode() {
        String s = "";
        try {
            // 正常処理
            if (s.isEmpty()) { // エラー発生条件
                throw new IOException(); // ①例外発生 ＝＞ try節の処理を中断
            }

            // ①の例外発生時は実行されない処理
        } catch (ArithmeticException e) { // ＜＝チェック（不一致）
            // } catch (ArithmeticException | Exception e){
            // ExceptionはArithmeticExceptionの継承元でコンパイルエラー

            // ①の例外発生時は実行されない例外処理
            // } catch (IOException e) { // ＜＝チェック 代入可能な型を発見
        } catch (Exception e) {
            // 例外処理 //ここへジャンプ
        }
    }

    // finally節は必ず実行される
    static void finallyExample() {
        for (int i = 0; i < 2; i++) {
            try {
                continue;
            } finally {
                // TODO: handle exception
                System.out.println("finally");
            }
        }
    }

    static void tryNest() {
        try { // 外側のtry文

            try { // 内側のtry文
                throw new IOException(); // １例外発生
                // } catch (NullPointerException e) {
            } catch (IOException e) {
                System.out.println("NullPointerException catched");
                throw e; // この行を有効にするとコンパイルが通る
            } finally {
                System.out.println("finally1"); // ２
            }

        } catch (IOException e) { // throw eで例外を伝播させないと このcatch節が使われないのでコンパイルエラーになる
            System.out.println("IOException catched"); // ３
        } finally {
            System.out.println("finally2"); // ４
        }
    }

    static void tryWithResourceExample() {
        try (var MyRes1 = new MyResource(); var MyRes2 = new MyResource();) {

            // リソース利用処理
        } catch (Exception e) {
            // エラー処理（スコープ外なのでリソース変数myRes1とmyRes2を使えない）
        } finally {
            // 全体の終了処理（スコープ外なのでリソース変数myRes1とmyRes2を使えない）
        }
    }

    // リソース変数をtry節でのみ使用する場合、上記コードを推奨
    static void tryWithResourceOutOfResource() {
        var myRes = new MyResource();
        try (myRes) {
            // リソース利用処理
        } catch (Exception e) {
            // エラー処理
            // リソース変数myResを使える。close呼び出し後である点に注意
        } finally {
            // 全体の終了処理
            // リソース変数myResを使える。close呼び出し後である点に注意
        }
        // リソース変数myResを使える。close呼び出し後である点に注意
    }

    static void calculationException() {
        // 0による整数除算
        int i = 1 / 0; // ArithmeticException送出

        // 配列の範囲を超えたアクセス
        int[] arr = new int[0];
        arr[0] = 0; // ArrayIndexOutOfBoundsException送出

        // 配列の要素型の違反
        Object[] arr2 = new String[1];
        arr2[0] = 0; // ArrayStoreException送出

        // 不正な型キャスト
        Object obj = "";
        Integer i2 = (Integer) obj; // ClassCastException送出

        // nullにドット文字を続けたアクセス
        Object obj2 = null;
        String s = obj.toString(); // NullPointerException送出
    }

    static String methodRunPause(boolean arg) {
        if (arg) {
            System.out.println("arg is true");
        } else {
            System.out.println("arg is false");
            throw new RuntimeException();
            // System.out.println("決して実行されない行"); // コンパイルエラー
        }
        System.out.println("メソッドの最後");
        return "done";
    }

    void throwMethodException() throws MyException {
        // 他のコードは省略
        throw new MyException();
    }

    void throwMethodException2() throws Exception { // 送出する例外の基底型でも可
        // 他のコードは省略
        throw new MyException();
    }

    // 複数の例外型をthrows節に記述。実行時例外を記述可能です。
    void throwMethodException3() throws MyException, Exception {
        // 省略
    }

    // 例外の伝播の例
    // 例外を補足せずに伝播する
    void callMethod() throws MyException {
        // 他のコードは省略
        throwMethodException(); // MyException例外を送出
    }

    void method(MyInterface my) {
        try {
            my.method();
        } catch (ParentException e) {
            // 省略
        }
    }

    // ラムダ式内の検査例外を実行時例外に例外翻訳
    static void lambdaException() {
        Consumer<String> meth = s -> {
            try {
                Thread.sleep(1000);
                System.out.println(s);
            } catch (InterruptedException e) {
                // TODO: handle exception
                // InterruptedException検査例外をRuntimeException実行時例外に例外翻訳
                throw new RuntimeException(e);
            }
        };

        meth.accept("abc"); // 1秒の待機後に文字列表示

        // 使用例
        // ラムダ式内の検査例外の補足は不要
        MyConsumer<String> meth2 = s -> {
            Thread.sleep(1000);
            System.out.println(s);
        };

        try {
            meth2.accept("abc");
        } catch (InterruptedException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }

    static void methodThrowsBasic() {
        // 使用例
        // throws節のあるメソッドのメソッド参照をストリーム処理に使えない
        // Unhandled exception type InterruptedExceptionJava(16777384)
        // Stream.of("abc").forEach(ThrowsNotThrows::method);

        // 使用例
        // throws節のないメソッドのメソッド参照はストリーム処理に使える
        Stream.of("abc").forEach(ThrowsNotThrows::method2);
    }

    static void assertBasic(int age) {
        assert age >= 0 : "ageは0以上";
        // ageを使う処理
    }

    // AppException例外に例外翻訳するコード
    static void exceptionTranslation() throws AppException { // throws節にはアプリケーション例外だけを書く
        try {
            int c = System.in.read(); // 検査例外が発生するライブラリ呼び出し処理
        } catch (IOException e) {
            // ライブラリが投げた検査例外をアプリケーション例外に翻訳して再送
            throw new AppException(e);
        }
    }

    // 原因例外を知る
    static void getCauseBasic() {
        var appException = new AppException(new IOException("CAUSE"));
        var cause = appException.getCause();
        System.out.println(cause);
    }

    // 抑制例外の例
    static void suppressedException() {
        try (var myRes = new MyResource();) {
            throw new Exception("main exception"); // 主例外
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // フレームワーク側のコード
    void frameworkExceptionBasic() {
        try {
            // アプリケーションコードを呼び出す
        } catch (FrameworkException e) {
            // TODO: handle exception
            // アプリケーションコード内でおきた異常を一括して処理
        }
    }

    public static void main(String[] args) {
        suppressedException();
    }
}

// コンパイルエラーの安易な回避策
class ExceptionExample1 {
    public static void main(String[] args) {
        try { // 例外が起こり得る処理をtryで囲む
            int c;
            while ((c = System.in.read()) != -1) {
                System.out.println(c);
            }
        } catch (IOException e) { // 例外を補足して対応処理を書く
            // TODO: handle exception
            e.printStackTrace(); // 例外を無視する場合の定石コード
        }

    }
}

// 間違った例外の抑制方法
class ExceptionExample2 {
    public static void main(String[] args) {
        String s = "012";
        System.out.println("start"); // 出力される
        try {
            System.out.println(s.charAt(3)); // 文字列長を超えたアクセス
        } catch (StringIndexOutOfBoundsException e) { // catchして無視。例外を握りつぶすと表現します。
            // TODO: handle exception

        }
        System.out.println("end"); // 出力される
    }
}

// AutoCloseableインターフェースを継承するリソースクラス
class MyResource implements AutoCloseable {
    MyResource() {
        // リソース開始処理（オープン処理）
    }

    // 他のメソッドは省略

    @Override
    public void close() throws IOException {
        // リソース開放処理（クローズ処理）
        // closeメソッド内で発生する例外の模倣
        // try-with-resources文を使うと抑制例外として扱われる
        throw new IOException("close exception");
    }
}

class MyException extends Exception {

    // private final int errorCode;

    // public MyException(int errorCode) {
    // this.errorCode = errorCode;
    // }

    public MyException() { // 引数なしのコンストラクタ
        super();
    }

    public MyException(String message) { // 原因文字列を受け取るコンストラクタ
        super(message);
    }

    public MyException(Throwable cause) { // 原因例外オブジェクトを受け取るコンストラクタ
        super(cause); // 後述する「原因例外」を参照
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    // void showErrorCode() {
    // System.out.println(this.errorCode);
    // }
}

// 階層化された例外クラス
class ParentException extends Exception { // 継承元例外

}

class ChildException extends ParentException { // 継承元例外の派生型

}

class OtherException extends Exception { // 無関係な検査例外

}

class OtherRuntimeException extends RuntimeException { // 無関係な実行時例外

}

// 継承元インターフェース
interface MyInterface {
    void method() throws ParentException;
}

// methodをオーバーライドする実装クラス
class My implements MyInterface {
    public void method() throws ParentException {
    } // 継承元例外はOK

    // public void method() throws ChildException { // 継承元例外の派生型はOK
    // }

    // public void method() { // throws節自身あるいはthrows節からの例外削除はOK
    // }

    // public void method() throws OtherRuntimeException { // 実行時例外の追加はOK
    // }

    // Exception Exception is not compatible with throws clause in
    // MyInterface.method()Java(67109266)
    // public void method() throws Exception { // 継承元例外の基底型はNG
    // }

    // Exception OtherException is not compatible with throws clause in
    // MyInterface.method()Java(67109266)
    // public void method() throws OtherException { // 継承元例外と継承関係のない検査例外はNG
    // }
}

@FunctionalInterface
interface MyConsumer<T> {
    void accept(T t) throws InterruptedException;
}

class ThrowsNotThrows {
    // throws節のあるメソッド
    public static void method(String s) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(s);
    }

    // 内部で検査例外を補足してthrows節をなくしたメソッド
    public static void method2(String s) {
        try {
            Thread.sleep(1000);
            System.out.println(s);
        } catch (InterruptedException e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
}

// アプリケーション例外の定義
class AppException extends Exception {
    public AppException(Throwable cause) {
        super(cause); // 原因例外をセットするため、この行が必須
    }
}

// 大きなくくりで異常を補足するフレームワーク例外
class FrameworkException extends RuntimeException {
    public FrameworkException(Throwable cause) {
        super(cause);
    }
}