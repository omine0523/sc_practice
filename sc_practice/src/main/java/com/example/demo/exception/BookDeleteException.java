package com.example.demo.exception;

/**
 * 書籍の論理削除を実行できない場合に発生する業務例外。
 */
public class BookDeleteException extends RuntimeException {
	public BookDeleteException(String message) {
		super(message);
	}
}
