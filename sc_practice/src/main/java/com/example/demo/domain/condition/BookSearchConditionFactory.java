package com.example.demo.domain.condition;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.domain.value.BookIdKey;
import com.example.demo.domain.value.BookNameKey;
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
		BookIdKey bookId = null;
		if (StringUtils.hasText(form.getBookId())) {
			bookId = new BookIdKey(form.getBookId());
		}

		// 入力された書籍名の先頭・末尾に空白（半角or全角）がある場合、バグ防止のため削除する。
		// ひらがなとカタカナ、全角・半角、大文字・小文字 の同一扱いはDB側の設定で制御する
		BookNameKey bookName = null;
		if (StringUtils.hasText(form.getBookName())) {
			bookName = new BookNameKey(form.getBookName());
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
				.bookId(bookId) // 正規化・数値変換済みの書籍ID（未入力なら null）
				.bookName(bookName) // 先頭・末尾の空白を削除した後の書籍名（未入力なら null）
				.fkGenreId(genreId) // 数値化したジャンルID（未選択なら null）
				.fkStorageLocationId(storageLocationId) // 数値化した置き場所ID（未選択なら null）
				.build(); // 検索条件オブジェクトを生成
	}
}