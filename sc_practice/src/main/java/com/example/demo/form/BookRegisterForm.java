package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
/**
 * 書籍登録画面で入力された値をバインドするformクラス。
 */
@Data
public class BookRegisterForm {
	
	/** 書籍ID（主キー） */
	@Pattern(regexp = "^[0-9０-９]*$", message = "{error.book.id.number.only}")
	private String bookId;
	
	/** 書籍名 */
	@NotBlank(message = "{error.book.name.required}")
	@Size(max = 100, message = "{error.book.name.max}")
	@Pattern(regexp = "^$|.*[^\\s　].*", message = "{error.book.name.not.empty}")
	private String bookName;
	
	/** ジャンルID */
	@NotNull(message = "{error.book.genre.required}")
	private Integer genreId;
	
	/** 置き場所ID */
	@NotNull(message = "{error.book.storageLocation.required}")
	private Integer storageLocationId;
	
	/** ステータス（未貸出 / 貸出中） デフォルトで未貸出を設定 */
	private String status = "未貸出"; 
	
	/** 書籍内容 */
	@Size(max = 250, message = "{error.book.summary.max}")
	@Pattern(regexp = "^$|.*[^\\s　].*", message = "{error.book.summary.not.empty}")
	private String summary;
}
