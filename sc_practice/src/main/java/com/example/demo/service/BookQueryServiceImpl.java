package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.condition.BookQueryCondition;
import com.example.demo.dto.view.BookListViewDto;
import com.example.demo.dto.view.PageResult;
import com.example.demo.mapper.BookInfoMapper;

import lombok.extern.slf4j.Slf4j;

/** 
 * Controllerから受け取った検索条件を用いて一覧に表示する書籍情報を検索する 実装クラス
 * 
 */
@Slf4j
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class BookQueryServiceImpl implements BookQueryService {

	@Autowired
	private BookInfoMapper bookInfoMapper;
	/**
	 * ページ番号によって表示する書籍情報制限し取得する
	 * @param page 現在表示のページ番号
	 * @return 書籍情報を10件分との取得結果を返却する
	 */
	@Override
	@Transactional(readOnly = true)
	public PageResult<BookListViewDto> findAllBook(int page) {
		
		int pageSize = 10; // 1ページあたりに表示する最大件数
	    int offset = (page - 1) * pageSize; // データ取得開始位置を算出する（例：2ページ目の場合は10件目から取得）
		
	    // データ取得開始位置から10件の書籍情報を取得する。
		List<BookListViewDto> list = bookInfoMapper.selectAllBooks(pageSize, offset);
	    // ページングの総件数表示で使用するため、登録されている全書籍の総件数を取得する。
		int totalCount = bookInfoMapper.countAllBooks();
	    // 総件数 ÷ 1ページあたりの件数の結果を切り上げして、総ページ数を求める。
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
	    // 一覧取得結果（list）とページ情報をまとめて
	    // PageResult オブジェクトとして生成し、Controller に返却する。
		return new PageResult<>(list, page, totalPages, totalCount);
	}
	
	/**
	 * 検索条件とページ番号によって表示する書籍情報制限し取得する
	 * 
	 * @param condition 業務ロジック用に検索条件の値が格納されたオブジェクト
	 * @param page 現在表示のページ番号
	 * @return 検索条件と一致した10件分の書籍情報を返却する
	 */
	@Override
	@Transactional(readOnly = true)
	public PageResult<BookListViewDto> findBookByConditions(BookQueryCondition condition, int page) {
		
		int pageSize = 10; // 1ページあたりに表示する最大件数
	    int offset = (page - 1) * pageSize; // データ取得開始位置を算出する（例：2ページ目の場合は10件目から取得）
		
		// mapperクラスに受け渡すために空の変数を生成する。
		Integer bookId = null;
		String bookName = null;
		
		// 検索条件に書籍IDが入力されてた場合にのみ、オブジェクトから変換後の書籍IDの値を参照する。
		if (condition.bookId() != null) {
			bookId = condition.bookId().getValue();
		}
		// 検索条件に書籍名が入力されてた場合にのみ、オブジェクトから加工後の書籍名の値を参照する。
		if (condition.bookName() != null) {
			bookName = condition.bookName().getValue();
		}
		
		// 指定した全ての検索条件と一致したの書籍情報をデータ取得開始位置から10件分取得する。
		List<BookListViewDto> list = bookInfoMapper.selectBookByConditions(
	    		bookId, // 書籍ID
				bookName, // 書籍名
				condition.fkGenreId(), // ジャンルID
				condition.fkStorageLocationId(), // 置き場所ID
				pageSize,
				offset);

	    // 指定した全ての検索条件をもとに、ページングの総件数表示で使用するため書籍の総件数を取得する。
		int totalCount = bookInfoMapper.countBookByConditions(
	    		bookId, // 書籍ID
				bookName, // 書籍名
				condition.fkGenreId(), // ジャンルID
				condition.fkStorageLocationId()); // 置き場所ID
	    // 総件数 ÷ 1ページあたりの件数の結果を切り上げして、総ページ数を求める
		int totalPages = (int) Math.ceil((double) totalCount / pageSize);
	    // 一覧取得結果（list）とページ情報をまとめて
	    // PageResult オブジェクトとして生成し、Controller に返却する（型推論により<>の中身は省略）
		return new PageResult<>(list, page, totalPages, totalCount);
	}
}
