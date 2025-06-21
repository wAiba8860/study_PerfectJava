package com.perfect.java.part2.ClassFile;

import java.util.ArrayList;
import java.util.List;

// 書籍を管理する買い物カゴクラス
public class ShoppingCart {
    public String customerName = ""; // 買い物カゴの所有者（初期値は空文字列）
    public List<String> books = new ArrayList<>(); // 買い物カゴに入れた書籍名を模倣

    public void addBook(String book) {
        this.books.add(book); // this.は省略可能
    }

    public void showBooks() {
        System.out.println(this.books); // this.は省略可能
    }
}
