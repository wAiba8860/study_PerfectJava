package com.perfect.java.part3.Chapter17Class;

public abstract sealed class BaseSealed permits My1, My2, My3 {
    // 本体省略
}

// My1,My2,My3はBaseSealedクラスを継承可能
final class My1 extends BaseSealed {
    // 本体省略
}

non-sealed class My2 extends BaseSealed {
    // 本体省略
}

sealed class My3 extends BaseSealed permits My4 {
    // 本体省略
}

// permits節にないOtherクラスはBaseSealedクラスを継承不可（コンパイルエラー）
// final class Other extends BaseSealed {}

// non-sealed修飾子のMy2クラスの拡張継承は可能
final class My5 extends My2 {
    // 本体省略
}

// sealed修飾子のMy3クラスの拡張継承はpermitsで許可したMy4クラスのみ可能
final class My4 extends My3 {
    // 本体省略
}