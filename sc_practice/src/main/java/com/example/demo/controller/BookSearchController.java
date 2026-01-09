package com.example.demo.controller;

import java.text.Normalizer;

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
	 *  書籍検索画面のトップページ表示する
	 *  
	 *  @param model ビューに渡すモデル情報
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
		
		// formで検索条件が未入力・未選択の場合、メッセージを表示してトップページに戻す
		if (!StringUtils.hasText(bookSearchForm.getBookId())) {
			model.addAttribute("infoMsg", "検索条件を入力してください");
			return "book-search";
		}
		
		// formで入力した値が数値の場合
		if (StringUtils.hasText(bookSearchForm.getBookId())) {
			
			// 全角 → 半角に正規化する
		    String normalizedBookId =
		            Normalizer.normalize(bookSearchForm.getBookId(), Normalizer.Form.NFKC);
		    //書籍ID（文字列）を数値型に変換する
		    Integer bookId = Integer.valueOf(normalizedBookId);
		    // 書籍IDを引数として書籍情報を検索する → 検索結果をモデルに詰めてテンプレートに渡す
		    model.addAttribute("resultBook", bookSearchService.searchBookById(bookId));
		    
		}

		// トップページ表示する
		return "book-search";
	}
}
