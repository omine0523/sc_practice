package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.BookInfo;

/** 
 * 入力した書籍IDの書籍を検索する インターフェース
 * 
 * @param bookId 書籍ID
 * @return 入力情報で一致した書籍情報を返却する
 */
public interface BookSearchService {

	/**
	 * 入力した書籍IDで該当書籍を検索する(検索条件なし)
	 */
	List<BookInfo> searchBookById(int bookId);

}
