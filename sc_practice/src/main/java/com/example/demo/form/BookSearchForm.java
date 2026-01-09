package com.example.demo.form;

import jakarta.validation.constraints.Pattern;

import lombok.Data;
/**
 * 書籍検索画面で入力された条件をバインドするformクラス。
 */
@Data
public class BookSearchForm {
	
	/** 書籍ID（主キー） */
	@Pattern(regexp = "^[0-9０-９]*$", message = "書籍IDは数字のみで入力してください")
	private String bookId;
	/** 書籍名 */
	private String bookName;
	/** ジャンル */
	private String genre;
	/** 置き場所 */
	private String storageLocation;
	/** ステータス（未貸出 / 貸出中） */
	private String status;

}
