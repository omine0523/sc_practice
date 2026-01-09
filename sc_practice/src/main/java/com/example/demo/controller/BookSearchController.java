package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.BookSearchForm;
import com.example.demo.service.BookSearchService;

/**
 * 書籍検索画面の処理を制御する Controller クラス。
 */
@Controller
public class BookSearchController {

	@Autowired
	private BookSearchService bookSearchService;

	/**
	 *  書籍検索画面のトップページの表示と検索ボタン押下時の処理を制御する Controllerクラス
	 *  
	 *  @param model 検索結果やメッセージをビュー渡すモデル
	 *  @param  bookSearchForm 画面の検索条件の値を受け取るフォーム
	 *  @param bindingResult @Validated によるバリデーション結果
	 *  @param search 検索ボタン押下判定用のリクエストパラメータ
	 *  @return 書籍検索画面のテンプレートを返却する
	 */
	@GetMapping("/book-search-top")
	public String showBookSearchPage(@Validated @ModelAttribute BookSearchForm bookSearchForm,
			BindingResult bindingResult,
			@RequestParam(required = false) String search,
			Model model) {

		// ＜トップページの初期表示する＞
		if (search == null) {
			return "book-search";
		}

		// ＜検索ボタン押下時の処理＞
		// formで入力した値が数字以外の場合、エラーメッセージを表示してにトップページに戻す
		if (bindingResult.hasErrors()) {
			return "book-search";
		}

		// formで検索条件全てが未入力・未選択の場合、メッセージを表示してトップページに戻す
		if (!StringUtils.hasText(bookSearchForm.getBookId())
				&& !StringUtils.hasText(bookSearchForm.getGenre())
				&& !StringUtils.hasText(bookSearchForm.getStorageLocation())) {
			model.addAttribute("infoMsg", "検索条件を入力してください");
			return "book-search";
		}

		// 検索条件が選択されている（書籍IDが数字入力）の場合、Service に検索条件全てを渡す
		// 検索結果をモデルに詰めてテンプレートに渡す
		model.addAttribute("resultBook", bookSearchService.searchBookById(bookSearchForm));

		// トップページ表示する
		return "book-search";
	}
}
