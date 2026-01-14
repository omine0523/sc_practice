package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.domain.condition.BookSearchCondition;
import com.example.demo.domain.value.BookId;
import com.example.demo.form.BookSearchForm;
import com.example.demo.service.BookSearchService;

/**
 * 書籍検索画面で指定された検索条件に応じて書籍検索結果の表示内容を制御するクラス
 */
@Controller
public class BookSearchController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private BookSearchService bookSearchService;

	/**
	 *  検索条件が入力・選択されている場合に書籍検索を行う。
	 *  <p>
	 *  検索条件が未指定の場合は案内メッセージを表示し、
	 *  検索条件に不備がある場合はバリデーションエラーメッセージを表示する。
	 *  該当する検索情報が存在しない場合は、
	 *  テンプレートで「該当なし」メッセージを一覧に表示する。
	 *  
	 *  @param  bookSearchForm 画面の検索条件の値を受け取るフォーム
	 *  @param bindingResult @Validated によるバリデーション結果
	 *  @param model 検索結果やメッセージをビュー渡すモデル
	 *  @return 書籍検索結果を表示する
	 */
	@GetMapping("/book-search-top")
	public String showBookSearchPage(@Validated @ModelAttribute BookSearchForm bookSearchForm,
			BindingResult bindingResult, Model model) {
		
		// 検索条件が全て未指定でかつバリデーションエラーがない場合
		if (unSpecifiedConditions(bookSearchForm) && !bindingResult.hasErrors()){
			// 検索欄下部に案内メッセージを表示する
			model.addAttribute("infoMessage", messageSource.getMessage("search.book.condition.required", null, Locale.JAPAN));
		
		// 検索条件が指定されており、かつバリデーションエラーがない場合は検索結果を返し、
		// 不備がある場合はエラーメッセージを検索条件下部に表示する。
		} else if (!(unSpecifiedConditions(bookSearchForm) || bindingResult.hasErrors())) {
			// 空の書籍IDオブジェクトを生成する。
			BookId bookId = null;
			// 画面で入力された書籍IDを検索条件として業務ロジック内で使用できるように正規化し、数値に変換する。
			if (StringUtils.hasText(bookSearchForm.getBookId())) {
			    bookId = new BookId(bookSearchForm.getBookId());
			}
			// 検索条件（変換した書籍ID、ジャンル、置き場所）を引数として検索条件オブジェクトを生成する。
			BookSearchCondition condition =
			        new BookSearchCondition(
			            bookId,
			            bookSearchForm.getGenre(),
			            bookSearchForm.getStorageLocation()
			        );
			
			// 検索条件をもとに書籍情報を検索し、検索結果を一覧表示用に設定し表示する。
			model.addAttribute("resultSearchBook", bookSearchService.searchBookByConditions(condition));
		}

		// 書籍検索画面を表示する。
		return "book-search";
	}
	
	/**
	 * 検索条件が全て指定してないことを判定する
	 * 
	 * @param bookSearchForm 画面で入力した検索条件を格納しているフォーム
	 * @return 検索条件がすべて未指定の場合は true、それ以外は false
	 */
	private boolean unSpecifiedConditions(BookSearchForm bookSearchForm) {
		return !StringUtils.hasText(bookSearchForm.getBookId())
				&& !StringUtils.hasText(bookSearchForm.getGenre())
				&& !StringUtils.hasText(bookSearchForm.getStorageLocation());
	}
}
