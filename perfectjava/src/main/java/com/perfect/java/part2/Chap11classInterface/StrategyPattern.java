package com.perfect.java.part2.Chap11classInterface;

public class StrategyPattern {
    enum CustomerRank {
        NORMAL, GOLD, PLATINUM
    }

    public static void main(String[] args) {
        // priceとCustomerRank.GOLDを決め打ち。実コードでは他コード箇所で決まると想像してください
        int price = 1000;
        PointCalculator pointCalculator = getPointCalculator(CustomerRank.GOLD);

        // 条件分岐コードから多態コードへの書き換え
        int point = pointCalculator.calculate(price);
        System.out.println("price and point = %d and %d".formatted(price, point));
    }

    static PointCalculator getPointCalculator(CustomerRank customerRank) {
        // オブジェクト生成コードに条件分岐コードが移動
        return switch (customerRank) {
            case NORMAL -> new NormalRankStrategy();
            case GOLD -> new GoldRankStrategy();
            case PLATINUM -> new PlatinumRankStrategy();
        };
    }
}

// ストラテジパターンのインターフェース
interface PointCalculator {
    int calculate(int price);
}

class NormalRankStrategy implements PointCalculator {
    @Override
    public int calculate(int price) {
        return (int) (price * 0.01);
    }
}

class GoldRankStrategy implements PointCalculator {
    @Override
    public int calculate(int price) {
        return (int) (price * 0.02);
    }
}

class PlatinumRankStrategy implements PointCalculator {
    @Override
    public int calculate(int price) {
        return (int) (price * 0.03);
    }
}
