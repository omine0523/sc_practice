package com.example.demo.dto.view;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * カート一覧画面に表示するためのDTOクラス。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartListViewDto {

    /** カートID（主キー） */
    private Integer cartId;

    /** カートに追加した書籍のID（外部キー） */
    private Integer bookId;

    /** 書籍名 */
    private String bookName;

    /** ジャンル名 */
    private String genreName;

    /** 置き場所名 */
    private String storageLocationName;

    /** ステータス */
    private String status;
    
    /** カートに追加した日時 */
    private LocalDateTime createdAt;
}