package com.example.demo.entity;

import java.time.LocalDate;

import lombok.Data;

/**
 * 書籍の貸出履歴を表すEntityクラス。
 * <p>
 * ユーザーが借りた書籍や貸出日、返却期限などの貸出情報を保持する。
 * </p>
 */
@Data
public class LoanHistory {

    /** 貸出履歴ID（主キー） */
    private Integer id;

    /** 書籍を借りたユーザーのID（外部キー） */
    private Integer userId;

    /** 借りた書籍のID（外部キー） */
    private Integer bookId;

    /** 書籍を貸し出した日付 */
    private LocalDate borrowDate;

    /** 書籍の返却期限日 */
    private LocalDate returnDueDate;

    /** 貸出状況（貸出中、返却済みなど） */
    private String status;
}