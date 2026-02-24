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

import com.example.demo.domain.condition.BookQueryCondition;
import com.example.demo.domain.condition.BookQueryConditionFactory;
import com.example.demo.form.BookQueryForm;
import com.example.demo.service.BookQueryConditionService;
import com.example.demo.service.BookQueryService;

/**
 * 書籍検索画面で指定された検索条件に応じて書籍検索結果の表示内容を制御するクラス
 */
@Controller
public class BookSearchController {

	@Autowired
	private BookQueryConditionService bookQueryConditionService;
	@Autowired
	private BookQueryService bookQueryService;
	@Autowired
	private BookQueryConditionFactory bookQueryConditionFactory;
	@Autowired
	private MessageSource messageSource;

	/**
	 *  検索条件が入力・選択されている場合に書籍検索を行う。
	 *  <p>
	 *  検索条件が未指定の場合は案内メッセージを表示し、全件取得した書籍情報を一覧に表示する。
	 *  検索条件に不備がある場合はバリデーションエラーメッセージを検索条件下部に表示する。
	 *  該当する検索情報が存在しない場合は、
	 *  テンプレートで「該当なし」メッセージを一覧に表示する。
	 *  
	 *  @param  bookSearchForm 画面の検索条件の値を受け取るフォーム
	 *  @param bindingResult @Validated によるバリデーション結果
	 *  @param model 検索結果やメッセージをビュー渡すモデル
	 *  @return 書籍検索結果を表示する
	 */
	@GetMapping("/books/search")
	public String showBookSearchPage(@Validated @ModelAttribute BookQueryForm bookQueryForm,
			BindingResult bindingResult, Model model) {
		
		// 検索条件の選択肢を取得
		// ジャンル・置き場所をテーブルから取得してモデルに設定し、セレクトボタン内の選択肢に反映する
		model.addAttribute("genres", bookQueryConditionService.findAllGenres());
		model.addAttribute("storageLocations", bookQueryConditionService.findAllStorageLocations());

		// 検索条件が全て未指定でかつバリデーションエラーがない場合
		if (unSpecifiedConditions(bookQueryForm) && !bindingResult.hasErrors()) {
			// 検索欄下部に検索条件の入力を促す案内メッセージを表示する。
			model.addAttribute("infoMessage",
					messageSource.getMessage("search.book.condition.required", null, Locale.JAPAN));
			// 検索条件が未指定で書籍情報を検索し、テーブル登録されている全ての書籍一覧をモデルに設定し一覧表示する。
			model.addAttribute("resultSearchBook", bookQueryService.findAllBook());

			// 検索条件が指定されており、かつバリデーションエラーがない場合は検索結果を返し、
			// 不備がある場合はエラーメッセージを検索条件下部に表示する。
		} else if (!unSpecifiedConditions(bookQueryForm) && !bindingResult.hasErrors()) {
			
			// 画面で入力した検索条件（変換した書籍ID、ジャンル、置き場所）を引数として
			// 業務ロジック・DB検索等で使用できるように正規化・数値化し検索条件オブジェクトを生成する。
			BookQueryCondition condition = bookQueryConditionFactory.createCondition(bookQueryForm);
			// 検索条件をもとに書籍情報を検索し、検索結果一覧をモデルに設定し一覧表示する。
			model.addAttribute("resultSearchBook", bookQueryService.findBookByConditions(condition));
		}
		// 書籍検索画面を表示する。
		return "book-search";
	}

	/**
	 * 画面で検索条件が未指定であることを判定する
	 * 
	 * @param bookSearchForm 画面で入力した検索条件を格納しているフォーム
	 * @return 検索条件がすべて未指定の場合は true、それ以外は false
	 */
	private boolean unSpecifiedConditions(BookQueryForm bookQueryForm) {
		return !StringUtils.hasText(bookQueryForm.getBookId()) // 書籍ID
				&& !StringUtils.hasText(bookQueryForm.getBookName()) // 書籍名
				&& bookQueryForm.getGenreId() == null // ジャンルID
				&& bookQueryForm.getStorageLocationId() == null; // 置き場所ID
	}
}
