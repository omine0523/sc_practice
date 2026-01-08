package com.example.demo.form;

import lombok.Data;
/**
 * 書籍検索画面で入力された条件をバインドするformクラス。
 */
@Data
public class BookSearchForm {
	
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
