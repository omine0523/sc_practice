package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * カート情報を表すEntityクラス。
 * <p>
 * ユーザーが貸出申請前に一時的に追加した書籍IDなどの情報を保持する。
 * </p>
 */
@Data
public class Cart {

    /** カートID（主キー） */
    private Integer id;

    /** カートに追加したユーザーのID（外部キー） */
    private Integer userId;

    /** カートに追加した書籍のID（外部キー） */
    private Integer bookId;

    /** カートに追加した日時 */
    private LocalDateTime createdAt;
}
