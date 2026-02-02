package com.example.demo.dto.view;

import lombok.Data;

/**
 * 書籍検索結果を画面表示するための DTO
 * （JOIN した名称系データを保持する）
 */
@Data
public class BookSearchResultDto {
	
	/** 書籍ID（主キー） */
    private Integer bookId;
    /** 書籍名 */
    private String bookName;
    /** ジャンル名（BookInfoテーブルのジャンルIDをキーとして内部結合後のGenreテーブルのジャンル名の値を格納） */
    private String genreName;
    /** 置き場所（BookInfoテーブルの置き場所IDをキーとして内部結合後のStorageLocationテーブルの置き場所名の値を格納） */
    private String storageLocationName;
    /** ステータス（未貸出 / 貸出中） */
    private String status;
}
