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

    public static void main(String[] args) {
        useShoppingCartExample();
    }
}
