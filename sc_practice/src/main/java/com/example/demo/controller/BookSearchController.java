package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.BookInfo;
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
	 *  書籍検索画面のトップページ表示する
	 *  
	 *  @param model ビューに渡すモデル情報
	 *  @return 書籍検索画面のテンプレートを返却する
	 */
	@GetMapping("/book-search-top")
	public String showBookSearchPage(@ModelAttribute BookSearchForm bookSearchForm, Model model) {

		// formで入力した書籍IDを参照して変数に代入する
		Integer bookId = bookSearchForm.getBookId();

		// ①トップページ遷移時と書籍IDの入力がない場合、検索ボタン押下時に何も表示しないでトップページ表示する
		// ②書籍IDの入力後に検索ボタンが押下された場合、入力された書籍IDを引数として書籍情報を検索する
		if (bookId != null) {
			List<BookInfo> resultBook = bookSearchService.searchBookById(bookId);
			// 検索結果をモデルでテンプレートに受け渡す
			model.addAttribute("resultBook", resultBook);
		}

		// トップページ表示する
		return "book-search";
	}
}
