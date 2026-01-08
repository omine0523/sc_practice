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
	/** ジャンル */
	private String genre;
	/** 置き場所 */
	private String storageLocation;
	/** ステータス（未貸出 / 貸出中） */
	private String status;

}
