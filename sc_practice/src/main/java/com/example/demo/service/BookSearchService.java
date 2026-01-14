package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.condition.BookSearchCondition;
import com.example.demo.entity.BookInfo;

/** 
 * Controllerから受け取った検索条件を用いて書籍情報を検索する インターフェース
 * 
 */
public interface BookSearchService {
	/**
	 * 検索条件をもとに該当書籍を検索する
	 * 
	 * @param condition 業務ロジック用に検索条件の値が格納されたオブジェクト
	 * @return 検索条件と一致した書籍情報を返却する
	 */
	List<BookInfo> searchBookByConditions(BookSearchCondition condition);

}
