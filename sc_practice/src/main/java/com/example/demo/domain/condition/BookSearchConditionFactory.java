package com.example.demo.domain.condition;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.domain.value.BookId;
import com.example.demo.form.BookSearchForm;

/**
 * 画面で入力された検索条件を業務ロジック用・テーブル検索等で使用できるよう変換する Factory クラス
 */
@Component
public class BookSearchConditionFactory {

	/**
	 * 各条件で入力値・選択がある場合にのみ文字列型から数値型に変換を行う
	 * 
	 * @param form 書籍検索画面で指定した検索条件を受け取る
	 * @return 変換後の検索条件オブジェクト
	 */
	public BookSearchCondition createCondition(BookSearchForm form) {

		// バリューオブジェクトで正規化・数値化した書籍IDのオブジェクトを取得する
		BookId bookId = null;
		if (StringUtils.hasText(form.getBookId())) {
			bookId = new BookId(form.getBookId());
		}

		// フォームで文字列として受け取ったジャンルIDを数値型に変換する。
		Integer genreId = null;
		if (StringUtils.hasText(form.getGenreId())) {
			genreId = Integer.valueOf(form.getGenreId());
		}

		// フォームで文字列として受け取った置き場所IDを数値型に変換する。
		Integer storageLocationId = null;
		if (StringUtils.hasText(form.getStorageLocationId())) {
			storageLocationId = Integer.valueOf(form.getStorageLocationId());
		}

		// 画面で入力された検索条件をもとに、
		// 業務ロジック・DB検索で使用できる検索条件オブジェクトを Builder で生成する。
		// ・入力がある項目のみを Condition に設定する
		// ・すべて未指定の場合は build() 内で例外が発生する
		return BookSearchCondition.builder()
				.bookId(bookId) // 正規化・数値変換済みの書籍ID
				.fkGenreId(genreId) // 選択されたジャンルID（未選択なら null）
				.fkStorageLocationId(storageLocationId) // 選択された置き場所ID（未選択なら null）
				.build(); // 検索条件オブジェクトを生成
	}
}