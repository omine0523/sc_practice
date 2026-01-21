package com.example.demo.entity;

import lombok.Data;

/**
 * 書籍情報を表す Entity クラス
 */
@Data
public class BookInfo {
	
	/** 書籍ID（主キー） */
	private Integer bookId;
	/** 書籍名 */
	private String bookName;
	/** ジャンルID（外部キー） */
	private Integer fkGenreId;
	/** 置き場所ID（外部キー） */
	private Integer fkStorageLocationId;
	/** ステータス（未貸出 / 貸出中） */
	private String status;

}
