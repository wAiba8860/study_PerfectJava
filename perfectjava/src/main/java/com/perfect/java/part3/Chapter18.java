package com.perfect.java.part3; //パッケージ宣言文

//インポート宣言の例
//import java.util.List;
import java.util.ArrayList;

//オンデマンドインポート
import java.util.*;

//1
//同じ単純名を単一型インポートとするとコンパイルエラー
// import java.util.List;
// import java.awt.List;

//2
//オンデマンドインポートで同じ単純名になる型名を使うとコンパイルエラー
//（オンデマンドインポートだけではエラーになりません）
// import java.util.*;
// import java.awt.*;

//3
//単一型インポートした単純名のクラスと同名クラスを宣言するとコンパイルエラー
// class List{

// }

//このクラスの完全修飾名がcom.perfect.java.part3.Chapter18になる
public class Chapter18 {
    List list2;
    // 完全修飾名
    java.util.List<java.lang.String> Oldlist = new java.util.ArrayList<>();
    // 単純名
    List<String> list = new ArrayList<>();
}
