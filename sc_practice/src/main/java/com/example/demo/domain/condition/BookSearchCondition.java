package com.example.demo.domain.condition;

import com.example.demo.domain.value.BookIdKey;
import com.example.demo.domain.value.BookNameKey;

/**
 * 業務ロジック・DB検索用に使用する変更不可能な書籍検索条件を保持するレコード。
 * 　※ 未変更項目も含む
 * <p>
 * 生成には Builder を使用し、
 * すべての検索条件が未指定の状態では生成できないよう制御済み。
 * </p>
 */
public record BookSearchCondition(
		/** 書籍ID（主キー）（正規化＋数値変換後） */
		BookIdKey bookId,
		/** 書籍名 （正規化＋ワイルドカード対策後）*/
		BookNameKey bookName,
		/** ジャンルID（外部キー） */
		Integer fkGenreId,
		/** 置き場所ID（外部キー） */
		Integer fkStorageLocationId) {

	/**
	 * 検索条件用オブジェクト BookSearchCondition を生成するための Builder を返す。
	 * @return 検索条件生成用 Builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
     * 検索条件用オブジェクト BookSearchCondition を生成するための Builder クラス。
     * <p>
     * 各検索条件の指定は任意とし、
     * build() 実行時に「すべて未指定」でないことを検証する。（controller内でもチェック処理を実装済み）
     * </p>
     */
	public static class Builder {
		private BookIdKey bookId;
		private BookNameKey bookName;
		private Integer fkGenreId;
		private Integer fkStorageLocationId;

		/**
         * 書籍ID検索条件を設定する。
         * @param bookId 正規化・数値変換後の書籍ID
         * @return Builder（メソッドチェーン用）
         */
		public Builder bookId(BookIdKey bookId) {
			this.bookId = bookId;
			return this;
		}
		
		/**
         * 書籍名検索条件を設定する。
         * @param bookName 正規化・数値変換後の書籍ID
         * @return Builder（メソッドチェーン用）
         */
		public Builder bookName(BookNameKey bookName) {
			this.bookName = bookName;
			return this;
		}
		
		/**
         * ジャンル検索条件を設定する。
         * @param fkGenreId 数値変換後のジャンルID
         * @return Builder（メソッドチェーン用）
         */
		public Builder fkGenreId(Integer fkGenreId) {
			this.fkGenreId = fkGenreId;
			return this;
		}

		/**
		 * 置き場所検索条件を設定する。
		 * @param fkStorageLocationId 数値変換後の置き場所ID
		 * @return Builder（メソッドチェーン用）
		 */
		public Builder fkStorageLocationId(Integer fkStorageLocationId) {
			this.fkStorageLocationId = fkStorageLocationId;
			return this;
		}

		/**
         * 設定された検索条件をもとに BookSearchCondition を生成する。
         * <p>
         * すべての検索条件が未指定の場合は、例外をスローする。
         * controllerでも二重チェックを行うためキャッチは作成しない。
         * </p>
         *
         * @return 検索条件オブジェクト
         * @throws IllegalStateException 検索条件がすべて未指定の場合
         */
		public BookSearchCondition build() {
			if (bookId == null && bookName == null && fkGenreId == null && fkStorageLocationId == null) {
				throw new IllegalStateException("検索条件が未指定です");
			}
			return new BookSearchCondition(
					bookId,
					bookName,
					fkGenreId,
					fkStorageLocationId);
		}
	}
}