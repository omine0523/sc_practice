package com.example.demo.domain.condition;

import com.example.demo.domain.value.BookId;

/**
 * 業務ロジック仕様に変更された検索条件を保持するレコード
 * 　※ 未変更項目も含む
 */
public record BookSearchCondition(
		/** 書籍ID（主キー）（正規化＋数値変換後） */
		BookId bookId,
		/** ジャンル */
	    String genre,
	    /** 置き場所 */
	    String storageLocation
	    ) {}
