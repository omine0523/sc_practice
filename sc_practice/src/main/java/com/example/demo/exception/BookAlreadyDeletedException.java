package com.example.demo.exception;
/**
 * 書籍情報を論理削除する処理中で例外が発生した際に
 * メッセージを表示して専用のエラー画面に遷移させる
 */
public class BookAlreadyDeletedException extends RuntimeException {
	public BookAlreadyDeletedException(String message) {
		super(message);
	}
}
