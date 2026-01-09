package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.BookInfo;
import com.example.demo.form.BookSearchForm;

/** 
 * 入力した書籍IDの書籍を検索する インターフェース
 * 
 */
public interface BookSearchService {
	/**
	 * 入力した検索条件で該当書籍を検索する
	 * 
	 * @param bookSearchForm 画面の検索条件の値が格納されたフォーム
	 * @return 画面で入力・選択した検索条件と一致した書籍情報を返却する
	 */
	List<BookInfo> searchBookById(BookSearchForm bookSearchForm);

}
