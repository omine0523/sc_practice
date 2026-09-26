package com.example.demo.dto.param;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * カートに追加する書籍情報を表すDTOクラス。
 */
@Data
@AllArgsConstructor
public class CartInsertParam {

   /** カートID（主キー） */
    private Integer id;

    /** カートに追加したユーザーのID（外部キー） */
    private Integer userId;

    /** カートに追加した書籍のID（外部キー） */
    private Integer bookId;

    /** カートに追加した日時 */
    private LocalDateTime createdAt;

    /**
     * コンストラクタ
     * @param userId ユーザーID
     * @param bookId 書籍ID
     */
    public CartInsertParam(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
