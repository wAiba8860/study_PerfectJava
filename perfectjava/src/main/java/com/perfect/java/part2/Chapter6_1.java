package com.perfect.java.part2;

import java.util.List;
import com.perfect.java.part2.ClassFile.FactoryMethodExample;
import com.perfect.java.part2.ClassFile.FieldVariableAccess;
import com.perfect.java.part2.ClassFile.Other;
import com.perfect.java.part2.ClassFile.ShoppingCart;
import com.perfect.java.part2.ClassFile.ThisMethodExample;
import com.perfect.java.part2.ClassFile.TopLevelClass;

public class Chapter6_1 {

    static void useShoppingCartExample() {
        var cart1 = new ShoppingCart();
        cart1.customerName = "suzuki";

        var cart2 = new ShoppingCart();
        cart2.customerName = "tanaka";

        // 2つのShoppingCartオブジェクトはそれぞれ独立して存在
        System.out
                .println("cart1: %s , cart2: %s".formatted(cart1.customerName, cart2.customerName));
    }

    static void shoppingCartMethod() {
        var cart1 = new ShoppingCart();
        var cart2 = new ShoppingCart();

        // var book = "Peopleware";

        // メソッド呼び出し
        cart1.addBook("PeopleWare");
        cart2.addBook("ShowStopper!");

        cart1.showBooks();
        cart2.showBooks();
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

    public static void main(String[] args) {
        outsideClass();
    }
}
