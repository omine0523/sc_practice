package com.example.demo.dto.converter;

import com.example.demo.dto.request.BookRegisterRequestDto;
import com.example.demo.form.BookRegisterForm;

/**
 * formクラスとserviceクラスで責務分離するために変換処理を行うConverterクラス
 */
public class BookRegisterConverter {
	/**
	 * 「Form → DTO」に入力値を変換する。
	 * 
	 * @param bookRegisterForm 画面の登録項目に入力された値を受け取るフォーム
	 * @return 「Form → DTO」に変換後のオブジェクト
	 */
	public static BookRegisterRequestDto toRequestDto(BookRegisterForm bookRegisterForm) {
		return BookRegisterRequestDto.builder()
				.bookId(bookRegisterForm.getBookId())
				.bookName(bookRegisterForm.getBookName())
				.fkGenreId(bookRegisterForm.getGenreId())
				.fkStorageLocationId(bookRegisterForm.getStorageLocationId())
				.status(bookRegisterForm.getStatus())
				.summary(bookRegisterForm.getSummary())
				.build();
	}
}
