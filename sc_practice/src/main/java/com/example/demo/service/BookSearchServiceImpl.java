package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.condition.BookSearchCondition;
import com.example.demo.entity.BookInfo;
import com.example.demo.mapper.BookInfoMapper;

/** 
 * Controllerから受け取った検索条件を用いて書籍情報を検索する 実装クラス
 *
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class BookSearchServiceImpl implements BookSearchService {

	@Autowired
	private BookInfoMapper bookInfoMapper;

	/**
	 * 検索条件を引数にセットして書籍情報を検索を行う。
	 * 
	 * @param condition 業務ロジック用に検索条件の値が格納されたオブジェクト
	 * @return 検索条件と一致した書籍情報を返却する
	 */
	@Override
	@Transactional(readOnly = true)
	public List<BookInfo> searchBookByConditions(BookSearchCondition condition) {
		// mapperクラスに受け渡すために空の変数を生成する。
		Integer bookId = null;

		// 検索条件に書籍IDが入力されてた場合にのみ、オブジェクトから変換後の書籍IDの値を参照する。
		if (condition.bookId() != null) {
			bookId = condition.bookId().getValue();
		}
		// 全ての検索条件をもとに、書籍情報を検索する
		return bookInfoMapper.findBookByConditions(
				bookId,
				condition.genre(),
				condition.storageLocation());
	}
}
