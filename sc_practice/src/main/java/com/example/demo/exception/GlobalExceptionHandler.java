package com.example.demo.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 例外が発生した際に処理を一元化して画面遷移やエラーメッセージ表示をまとめて管理するグローバル例外ハンドラ
 * 全ての対象として該当のエラーが発生した際にメッセージつきで共通エラー画面を表示する。
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 予期せぬ RuntimeException が発生した際に共通のメッセージと共通の画面を表示させる（共通）
	 * @param e アプリ全体で予期せぬ業務例外
	 * @param model　画面に渡す情報 
	 * @return エラー画面を表示する
	 */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeError(RuntimeException e, Model model) {
    	// 画面にエラーメッセージを表示する。
        model.addAttribute("message", messageSource.getMessage("error.system", null , Locale.JAPAN));
        // 共通のエラー画面を表示する。
        return "common/error";
    }
	
	/**
	 * 書籍情報を登録する処理中で例外が発生した際に
	 * メッセージを表示して専用のエラー画面に遷移させる
	 * 
	 * @param e 書籍情報の登録処理でDB操作中に起きた例外情報
	 * @param model 画面に渡す情報
	 * @return エラー画面を表示する
	 */
	@ExceptionHandler(BookRegisterException.class)
	public String handleError(BookRegisterException e, Model model) {
		// 画面に「書籍登録に失敗しました」メッセージを表示する。
	    model.addAttribute("message", e.getMessage());
	    // 共通のエラー画面を表示する。
	    return "common/error";
	}
}
