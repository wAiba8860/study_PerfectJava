package com.perfect.java.part2;

import java.util.List;

import com.perfect.java.part2.ClassFile.Adder;
import com.perfect.java.part2.ClassFile.ConstructorExample;
import com.perfect.java.part2.ClassFile.FactoryMethodExample;
import com.perfect.java.part2.ClassFile.FieldVariableAccess;
import com.perfect.java.part2.ClassFile.InitializeBlock;
import com.perfect.java.part2.ClassFile.MyRecursive;
import com.perfect.java.part2.ClassFile.Other;
import com.perfect.java.part2.ClassFile.ShoppingCart;
import com.perfect.java.part2.ClassFile.ThisMethodExample;
import com.perfect.java.part2.ClassFile.TopLevelClass;
import com.perfect.java.part2.ClassFile.VariableLengthMethod;
import com.perfect.java.part2.ClassFile.*;

public class Chapter6_1 {

    static void useShoppingCartExample() {
        // var cart1 = new ShoppingCart();
        // cart1.customerName = "suzuki";

        // var cart2 = new ShoppingCart();
        // cart2.customerName = "tanaka";

        // 2つのShoppingCartオブジェクトはそれぞれ独立して存在
        // System.out
        // .println("cart1: %s , cart2: %s".formatted(cart1.customerName,
        // cart2.customerName));
    }

    static void shoppingCartMethod() {
        // var cart1 = new ShoppingCart();
        // var cart2 = new ShoppingCart();

        // var book = "Peopleware";

        // メソッド呼び出し
        // cart1.addBook("PeopleWare");
        // cart2.addBook("ShowStopper!");

        // cart1.showBooks();
        // cart2.showBooks();
    }

    static void FactoryMethodBasic() {
        // ファクトリメソッドの呼び出し例
        var my = FactoryMethodExample.getInstance();
    }

    static void listOfAbstract() {
        // ofメソッドの返り値オブジェクトのクラスは見えない
        var list = List.of("abc", "def", "ghi");

        // 変数の型を指定する場合、インタフェース型にする
        List<String> list2 = List.of("abc", "def", "ghi");
    }

    static void useClassBasic() {
        // クラスフィールドの参照
        System.out.println(Integer.MIN_VALUE);

        // クラスメソッドの呼び出し
        System.out.println(Integer.toHexString(32));
    }

    static void mathExample() {
        final class Math { // finalクラスにして無用な継承を禁止する
            private Math() {} // コンストラクタをprivateにして、オブジェクト生成を禁止する

            public static final double E = 2.7182818284590452354; // すべてのメソッドとフィールドにstatic修飾子をつけてクラスフィールド、クラスメソッドにする
        }
    }

    static void FieldVariableAccessBasic() {
        var f = new FieldVariableAccess();
        f.method("foo");
    }

    static void outsideClass() {
        // TopLevelClassとOtherクラスの使用例
        var my = new TopLevelClass();
        var other = new Other();
        other.callMethod(my);
    }

    static void ThisMethodBasic() {
        // ThisMethodExampleの使用例
        var my = new ThisMethodExample();
        my.callMethod();
    }

    static void adderClassBasic() {
        // Adderクラスの使用例
        var adder = new Adder();
        adder.addScore(1000); // 1000は実引数

        int i = 100;
        int j = 200;
        // 同じオブジェクトの参照なので値は1300
        adder.addScore(i + j);

        // 別のオブジェクトの参照
        var adder2 = new Adder();
        adder2.addScore(i + j);
    }

    static void callVariableLength() {
        // 呼び出し例
        var my = new VariableLengthMethod();
        my.method(); // 実引数なしの呼び出しも可能
        my.method("abc", "def"); // 実引数の数は任意
        my.method(new String[] {"abc", "def"}); // 実引数に配列を渡せる
    }

    static void callArrayMethod() {
        // 呼び出し例
        var my = new VariableLengthMethod();
        // 上記メソッドの実引数なしの呼び出しは、空配列と等価
        my.methodArray(new String[] {});
        my.methodArray(new String[] {"abc", "def"});

        // 下記はコンパイルエラー
        // The method methodArray(String[]) in the type VariableLengthMethod is not
        // applicable for the arguments (String, String)
        // my.methodArray("abc", "def"); //配列型に渡せるのは配列のみ
    }

    static void addScoreExample() {
        // Adderクラスの使用例
        var adder = new Adder();
        int result1 = adder.addScoreReturn(100);
        int result2 = adder.addScoreReturn(1000);
        System.out.println(result1);
        System.out.println(result2);

        // 参照型メソッド
        var sb = new Adder();
        System.out.println(sb.getStringBuilder().toString());

        // オーバーロードしたメソッドの呼び出し例
        var my = new Adder();
        my.method("abc");
        my.method(new StringBuilder("def"));
    }

    static void callRecursive() {
        // MtRecursiveクラスの使用例
        var myRecursive = new MyRecursive();
        myRecursive.method();
    }

    static void callConstruct() {
        // 使用例
        var my = new ConstructorExample();
    }

    static void shoppingCartConstruct() {
        // 使用例
        // new式に渡した実引数がコンストラクタのパラメータ変数に代入される
        var cart = new ShoppingCart("suzuki", List.of("Peopleware"));
        cart.showBooks();
    }

    static void InitializeBlockBasic() {
        var ib = new InitializeBlock();
    }

    static void ClassFieldInstanceFiledDifference() {
        // 使用例
        var my1 = new ClassStaticField();
        var my2 = new ClassStaticField();

        // インスタンスフィールド
        System.out.println(my1.si);

        // オブジェクトmy1（変数my1の参照先オブジェクト）のインスタンスフィールド変数に再代入
        my1.si = "def";
        System.out.println(my1.si);

        // オブジェクトmy2（変数my2の参照先オブジェクト）のインスタンスフィールド変数には影響なし
        System.out.println(my2.si);

        // クラスフィールド変数に再代入
        ClassStaticField.SC = "DEF";

        // クラスフィールド変数の実態は1つなので、どこからアクセスしても再代入した値が見える
        System.out.println(ClassStaticField.SC);
        System.out.println(my1.SC);
        System.out.println(my2.SC);
    }

    public static void main(String[] args) {
        // useRecodeBasic(new RecordBook("abc", "efb", 111));
    }
}


class ClassStaticField {
    static String SC = "ABC";
    String si = "abc";
}
