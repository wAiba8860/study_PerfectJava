//先頭にパッケージ宣言文
package com.perfect.java.part3; //パッケージ宣言文

//インポート宣言が続く
//インポート宣言の例
//import java.util.List;
//インポート文の重複は問題なし
import java.util.ArrayList;
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

import java.util.Locale;
import java.util.*;
import static java.util.Locale.JAPANESE;
import static java.util.Locale.*;

//クラスフィールドの参照方法
class StaticField {
    // 完全修飾名
    String s1 = java.util.Locale.JAPANESE.getDisplayLanguage();

    // 型を単一型インポート
    String s2 = Locale.JAPANESE.getDisplayLanguage();

    // 型をオンデマンドインポート
    String s3 = Locale.JAPANESE.getDisplayLanguage();

    // クラスフィールドをstatic単一型インポート
    String s4 = JAPANESE.getDisplayLanguage();

    // クラスフィールドをstaticオンデマンドインポート
    String s5 = JAPANESE.getDisplayLanguage();
}

// 自作クラスなどの宣言文が続く
// このクラスの完全修飾名がcom.perfect.java.part3.Chapter18になる
public class Chapter18 {
    List list2;
    // 完全修飾名
    java.util.List<java.lang.String> Oldlist = new java.util.ArrayList<>();
    // 単純名
    List<String> list = new ArrayList<>();
}
