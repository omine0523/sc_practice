package com.example.demo.service;

import com.example.demo.dto.request.BookRegisterRequestDto;

/**
 * 入力された書籍情報の登録処理を実行する インターフェース
 */
public interface BookRegisterService {
	
	/**
	 * 入力された書籍情報を元に登録処理を実行する
	 * 
	 * @param bookRegisterForm 登録画面で入力された書籍情報
	 */
	void registerBook(BookRegisterRequestDto requestDto) ;

}
