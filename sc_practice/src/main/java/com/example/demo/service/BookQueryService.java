package com.example.demo.service;

import com.example.demo.domain.condition.BookQueryCondition;
import com.example.demo.dto.view.BookListViewDto;
import com.example.demo.dto.view.PageResult;

/** 
 * Controllerから受け取った検索条件を用いて書籍情報を検索する インターフェース
 * 
 */
public interface BookQueryService {
	
	/**
	 * 画面遷移時に初期情報を全件取得する
	 * @return 全件取得結果を返却する
	 */
	PageResult<BookListViewDto> findAllBook(int page);
	/**
	 * 検索条件をもとに該当書籍を検索する
	 * 
	 * @param condition 業務ロジック用に検索条件の値が格納されたオブジェクト
	 * @return 検索条件と一致した書籍情報を返却する
	 */
	PageResult<BookListViewDto> findBookByConditions(BookQueryCondition condition, int page);

}
