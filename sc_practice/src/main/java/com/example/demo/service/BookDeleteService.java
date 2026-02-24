package com.example.demo.service;


/**
 * 削除ボタン横の書籍IDに紐づいた書籍情報をDBから削除する インターフェース
 */
public interface BookDeleteService {
	
	/**
	 * 書籍IDをもとに対象の書籍情報を論理削除する。
	 * 
	 * @param bookId 削除対象の書籍ID
	 */
	void logicalDeleteBook(Integer bookId);

}
