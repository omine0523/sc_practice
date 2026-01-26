package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.condition.BookSearchCondition;
import com.example.demo.dto.BookSearchResultDto;

/** 
 * Controllerから受け取った検索条件を用いて書籍情報を検索する インターフェース
 * 
 */
public interface BookSearchService {
	
	/**
	 * 画面遷移時に初期情報を全件取得する
	 * @return 全件取得結果を返却する
	 */
	List<BookSearchResultDto> fetchAllBook();
	/**
	 * 検索条件をもとに該当書籍を検索する
	 * 
	 * @param condition 業務ロジック用に検索条件の値が格納されたオブジェクト
	 * @return 検索条件と一致した書籍情報を返却する
	 */
	List<BookSearchResultDto> searchBookByConditions(BookSearchCondition condition);

}
