package com.example.demo.form;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
/**
 * 書籍検索画面で入力された条件をバインドするformクラス。
 */
@Data
public class BookSearchForm {
	
	/** 書籍ID（主キー） */
	@Pattern(regexp = "^[0-9０-９]*$", message = "{book.id.number.only}")
	private String bookId;
	/** 書籍名 */
	@Size(max = 100, message = "{book.name.max}")
	@Pattern(regexp = "^$|.*[^\\s　].*", message = "{book.name.not.empty}")
	private String bookName;
	/** ジャンルID */
	private Integer genreId;
	/** 置き場所ID */
	private Integer storageLocationId;
	/** ステータス（未貸出 / 貸出中） */
	private String status;

}
