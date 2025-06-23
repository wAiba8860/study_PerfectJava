package com.perfect.java.part2.ClassFile;

import java.util.ArrayList;
import java.util.List;

// 書籍を管理する買い物カゴクラス
public class ShoppingCart {

    // finalにしておくとコンストラクタでの初期化以降、フィールド値の書き換えがないことを明示化
    private final String customerName; // = ""; // 買い物カゴの所有者（初期値は空文字列）
    private final List<String> books; // = new ArrayList<>(); // 買い物カゴに入れた書籍名を模倣

    // フィールド変数booksのデフォルト引数を模倣
    public ShoppingCart(String customerName) {
        // this.customerName = customerName;
        // this.books = new ArrayList<>(); // デフォルト値

        // this呼び出しで引数2つのコンストラクタ呼び出し
        this(customerName, new ArrayList<>());
    }

    public ShoppingCart(String customerName, List<String> books) {
        this.customerName = customerName;
        this.books = books;
    }

    public void addBook(String book) {
        this.books.add(book); // this.は省略可能
    }

    public void showBooks() {
        System.out.println(this.customerName);
        System.out.println(this.books); // this.は省略可能
    }
}
