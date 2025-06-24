package com.perfect.java.part2.ClassFile;

import java.time.LocalDateTime;

// 事前定義した3状態
enum StateTransition {
    SUBMITTED, APPROVED, REJECTED
}

// enum定数をコンポーネントに持つレコードクラス
// approver：承認者
// updateTime：承認日時または拒否日時
// rejectReason：拒否理由


record Application(StateTransition status, String approver, LocalDateTime updateTime,
        String rejectReason) {
}


// シール型を使う状態遷移
// シール型ApplicationStatusの宣言
// 省略しない記法
// sealed interface ApplicationStatus permits Submitted, Approved, Rejected {
// }


// // シール型を継承するレコードクラス（事前定義した3つの状態に対応するレコードクラス）
// record Submitted() implements ApplicationStatus {
// }


// record Approved(String approver, LocalDateTime updateTime) implements ApplicationStatus {
// }


// record Rejected(String rejectReason, LocalDateTime updateTime) implements ApplicationStatus {
// }


// よく見る形式
sealed interface ApplicationStatus {
    record Submitted() implements ApplicationStatus {
    }
    record Approved(String approver, LocalDateTime updateTime) implements ApplicationStatus {
    }
    record Rejected(String rejectReason, LocalDateTime updateTime) implements ApplicationStatus {
    }
}


// 状態遷移の模倣
class StateTransitionClass {
    ApplicationStatus process(ApplicationStatus currentStatus, boolean condition) {
        if (condition) { // 承認状態への遷移を模倣
            // 承認処理の関連コードがここにあると仮定

            // 承認者取得の模倣コード
            // getApproverメソッドの中身は省略。下記使用例では"suzuki"を返したと仮定
            String approver = "suzuki";// getApprover();
            return new ApplicationStatus.Approved(approver, LocalDateTime.now());
        } else { // 拒否状態への遷移を模倣
            // 拒否処理の関連コードがここにあると仮定
            String reason = "Reason"; // getReason(); // 拒否理由を取得する模倣（メソッドの中身は省略）
            return new ApplicationStatus.Rejected(reason, LocalDateTime.now());

        }
    }


    // 使用例
    void method() {
        // 初期状態（申請中）を模倣
        ApplicationStatus firstStatus = new ApplicationStatus.Submitted();

        // 第二引数にtrueを渡して承認状態への状態遷移を模倣
        ApplicationStatus nextStatus = process(firstStatus, true);
        System.out.println(nextStatus);
    }

    public static void main(String[] args) {
        var v = new StateTransitionClass();
        v.method();
    }
}
