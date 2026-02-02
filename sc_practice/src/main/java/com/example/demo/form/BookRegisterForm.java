package com.example.demo.form;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
/**
 * 書籍登録画面で入力された値をバインドするformクラス。
 */
@Data
public class BookRegisterForm {
	
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
	/** ステータス（未貸出 / 貸出中） デフォルトで未貸出を設定 */
	private String status = "未貸出"; 
	/** 書籍内容 */
	private String summary;
}
