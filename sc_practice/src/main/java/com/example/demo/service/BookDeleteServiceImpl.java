package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.BookAlreadyDeletedException;
import com.example.demo.mapper.BookInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 削除ボタン横の書籍IDに紐づいた書籍情報をDBから削除する 実装クラス
 */
@Slf4j
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
@RequiredArgsConstructor
public class BookDeleteServiceImpl implements BookDeleteService {

	private final BookInfoMapper bookInfoMapper;
	
	/**
	 * 書籍IDをもとに対象の書籍情報を論理削除する。
	 *
	 * @param bookId 削除対象の書籍ID
	 * @throws BookAlreadyDeletedException すでに削除済みの書籍
	 */
	@Override
	public void logicalDeleteBook(Integer bookId) {
		// 削除フラグをtrueに更新して（true：論理削除済み）論理削除を実行する、実行結果（更新行数）を代入する。
		int count = bookInfoMapper.logicalDeleteBook(bookId);
				// 更新処理実行結果（更新行数）が0行だった場合、例外とメッセージを投げる。
				if(count == 0) {
					throw new BookAlreadyDeletedException("すでに削除されています");
				}
	}
}
