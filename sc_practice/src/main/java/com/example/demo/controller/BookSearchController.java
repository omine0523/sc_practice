package com.example.demo.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.condition.BookQueryCondition;
import com.example.demo.domain.condition.BookQueryConditionFactory;
import com.example.demo.dto.view.BookListViewDto;
import com.example.demo.dto.view.PageResult;
import com.example.demo.form.BookQueryForm;
import com.example.demo.service.BookQueryConditionService;
import com.example.demo.service.BookQueryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 書籍検索画面で指定された検索条件に応じて書籍検索結果の表示内容を制御するクラス
 */
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookSearchController {

	private final BookQueryConditionService bookQueryConditionService;

	private final BookQueryService bookQueryService;
	
	private final BookQueryConditionFactory bookQueryConditionFactory;

	private final MessageSource messageSource;
	
	/**
	 *  検索条件が入力・選択されている場合に書籍検索を行う。
	 *  <p>検索条件が未指定の場合は案内メッセージを表示し、全件取得した書籍情報を一覧に表示する。
	 *  検索条件に不備がある場合はバリデーションエラーメッセージを検索条件下部に表示する。
	 *  該当する検索情報が存在しない場合は、テンプレートで「該当なし」メッセージを一覧に表示する。</p>
	 *  @param  bookQueryForm 画面の検索条件の値を受け取るフォーム
	 *  @param bindingResult @Valid によるバリデーション結果
	 *  @param page 画面から受け取ったページングの情報
	 *  @param model 検索結果やメッセージをビュー渡すモデル
	 *  @return 書籍検索結果を表示する
	 */
	@GetMapping("/search")
	public String showBookSearchPage(@Valid @ModelAttribute BookQueryForm bookQueryForm,
		BindingResult bindingResult, @RequestParam(defaultValue = "1") int page, Model model) {
		
		// 検索条件の選択肢を取得
		// ジャンル・置き場所をテーブルから取得してモデルに設定し、セレクトボタン内の選択肢に反映する
		model.addAttribute("genres", bookQueryConditionService.findAllGenres());
		model.addAttribute("storageLocations", bookQueryConditionService.findAllStorageLocations());

		// 全件取得・条件指定取得いずれの場合も条件分岐後の共通処理で使用するため、ページング結果を保持する変数を事前に宣言する。
		PageResult<BookListViewDto> result = null;
		
		if (bindingResult.hasErrors()) {
        // バリデーションエラー時は空リストとnullページ情報をセットし、エラーメッセージを検索条件下部に表示する。
        model.addAttribute("bookList", null);
        model.addAttribute("pageResult", null);
        return "book-search";
		}
		// 検索条件が全て未指定でかつバリデーションエラーがない場合
		if (unSpecifiedConditions(bookQueryForm)) {
			// 検索欄下部に検索条件の入力を促す案内メッセージを表示する。
			model.addAttribute("infoMessage",
				messageSource.getMessage("search.book.condition.required", null, Locale.JAPAN));
				// ページ情報を引数に検索条件が未指定の状態で表示する書籍情報の一覧とページング情報を取得する。
			result = bookQueryService.findAllBook(page);
		// 検索条件が指定されており、かつバリデーションエラーがない場合は検索結果を返す。
		} else {
			// 画面で入力した検索条件（変換した書籍ID、ジャンル、置き場所）を引数として
			// 業務ロジック・DB検索等で使用できるように正規化・数値化し検索条件オブジェクトを生成する。
			BookQueryCondition condition = bookQueryConditionFactory.createCondition(bookQueryForm);
			// ページ情報を引数にして、検索条件をもとに表示する書籍情報の一覧とページング情報を取得する。
			result = bookQueryService.findBookByConditions(condition, page);
		}
		// 取得したページング情報と書籍一覧をモデルに設定する。
		if (result != null) {
		    model.addAttribute("bookList", result.getList()); // 書籍一覧をモデルに詰める
		    model.addAttribute("pageResult", result); // ページングの情報を詰める
		}
		// 書籍検索画面を表示する。
		return "book-search";
	}

	/**
	 * 画面で検索条件が未指定であることを判定する
	 * 
	 * @param bookQueryForm 画面で入力した検索条件を格納しているフォーム
	 * @return 検索条件がすべて未指定の場合は true、それ以外は false
	 */
	private boolean unSpecifiedConditions(BookQueryForm bookQueryForm) {
		return !StringUtils.hasText(bookQueryForm.getBookId()) // 書籍ID
				&& !StringUtils.hasText(bookQueryForm.getBookName()) // 書籍名
				&& bookQueryForm.getGenreId() == null // ジャンルID
				&& bookQueryForm.getStorageLocationId() == null; // 置き場所ID
	}
}
