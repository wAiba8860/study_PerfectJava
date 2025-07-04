package com.perfect.java.part2.Chap11classInterface;

public class CustomerRankPoint {
    enum CustomerRank {
        NORMAL, GOLD, PLATINUM
    }

    public static void main(String... args) {
        // priceとCustomerRank.GOLDを決め打ち。実コードでは他のコード箇所で決まると想像してください
        int price = 100;
        int point = calculatePoint(price, CustomerRank.GOLD);
        System.out.println("price and point = %d and %d".formatted(price, point));
    }

    static int calculatePoint(int price, CustomerRank customerRank) {
        // 条件分岐コード
        return switch (customerRank) {
            case NORMAL -> calcNormalRankPoint(price);
            case GOLD -> calcGoldRankPoint(price);
            case PLATINUM -> calcPlatinumRankPoint(price);
        };
    }

    static int calcNormalRankPoint(int price) {
        return (int) (price * 0.01);
    }

    static int calcGoldRankPoint(int price) {
        return (int) (price * 0.02);
    }

    static int calcPlatinumRankPoint(int price) {
        return (int) (price * 0.03);
    }
}
