package com.perfect.java.part2;

import com.perfect.java.part2.ClassFile.ShoppingCart;

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

    public static void main(String[] args) {
        shoppingCartMethod();
    }
}
