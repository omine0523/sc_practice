package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Data;
/**
 * 書籍登録画面で入力された値を受け取るリクエストDTO。
 * Controllerでフォームの入力値を受け取り、Serviceへ渡す際に使用する。
 */
@Data
@Builder
public class BookRegisterRequestDto {

	/** 書籍ID（主キー） */
	private String bookId;

	/** 書籍名 */
	private String bookName;

	/** ジャンルID */
	private Integer fkGenreId;

	/** 置き場所ID */
	private Integer fkStorageLocationId;

	/** ステータス（未貸出 / 貸出中）*/
	private String status;
	
	/** 書籍内容 */
	private String summary;
}
