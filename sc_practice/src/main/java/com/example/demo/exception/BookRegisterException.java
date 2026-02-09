package com.example.demo.exception;

/**
 * 書籍情報を登録処理中にRuntimeExceptionが起きた際に設定したメッセージや例外情報を
 * 共通の例外ハンドラークラスに渡すための例外クラス
 */
public class BookRegisterException extends RuntimeException {
	
	/**
	 * 共通例外ハンドラーに設定した登録失敗メッセージと例外情報を渡す
	 * 
	 * @param message 登録失敗メッセージ
	 * @param cause DB操作中に起きた例外情報
	 */	
	public BookRegisterException(String message, Throwable cause) {
		// RuntimeExceptionクラスのコンストラクタを呼んで
		// 各フィールド変数にエラーメッセージと元例外：PersistenceExceptionををcauseにセットしている
		super(message, cause);
	}
}
