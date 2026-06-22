package com.example.demo.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.condition.BookQueryCondition;
import com.example.demo.domain.condition.BookQueryConditionFactory;
import com.example.demo.dto.view.BookDetailViewDto;
import com.example.demo.dto.view.PageResult;
import com.example.demo.form.BookQueryForm;
import com.example.demo.service.BookDeleteService;
import com.example.demo.service.BookQueryConditionService;
import com.example.demo.service.BookQueryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 書籍情報を一覧で表示し、選択した書籍の削除処理を行う Controller クラス
 * 書籍検索画面と同様に検索条件で検索することが可能。
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/books")
public class BookDeleteController {

	private final BookQueryConditionService bookQueryConditionService;
	
	private final BookQueryService bookQueryService;

	private final BookQueryConditionFactory bookQueryConditionFactory;
	
	private final BookDeleteService bookDeleteService;
	
	private final MessageSource messageSource;

	/**
	 * 書籍検索画面のオブジェクト使用して削除対象の書籍をDBから一覧で取得し表示する。
	 * 
	 * @param  bookQueryForm 画面の検索条件の値を受け取るフォーム
	 * @param bindingResult @Valid によるバリデーション結果
	 * @param model 検索結果やメッセージをビュー渡すモデル
	 * @return 書籍検索結果を表示する
	 */
	@GetMapping("/delete")
	public String showBookDeletePage(@Valid @ModelAttribute BookQueryForm bookQueryForm,
			BindingResult bindingResult, @RequestParam(defaultValue = "1") int page, Model model) {

		// 検索条件の選択肢を取得
		// ジャンル・置き場所をテーブルから取得してモデルに設定し、セレクトボタン内の選択肢に反映する
		model.addAttribute("genres", bookQueryConditionService.findAllGenres());
		model.addAttribute("storageLocations", bookQueryConditionService.findAllStorageLocations());

		// 全件取得・条件指定取得いずれの場合も条件分岐後の共通処理で使用するため、ページング結果を保持する変数を事前に宣言する。
		PageResult<BookDetailViewDto> result = null;

		// 検索条件が全て未指定でかつバリデーションエラーがない場合
		if (unSpecifiedConditions(bookQueryForm) && !bindingResult.hasErrors()) {
			// 検索欄下部に検索条件の入力を促す案内メッセージを表示する。
			model.addAttribute("infoMessage",
					messageSource.getMessage("search.book.condition.required", null, Locale.JAPAN));

			// ページ情報を引数に検索条件が未指定の状態で表示する書籍情報の一覧とページング情報を取得する。
			result = bookQueryService.findAllBook(page);

			// 検索条件が指定されており、かつバリデーションエラーがない場合は検索結果を返し、
			// 不備がある場合はエラーメッセージを検索条件下部に表示する。
		} else if (!unSpecifiedConditions(bookQueryForm) && !bindingResult.hasErrors()) {

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
		
		// 書籍削除画面を表示する
		return "book-delete";
	}

	/**
	 * 削除ボタンが押された際にボタン横の書籍IDに紐づく書籍情報を一覧から論理削除する。
	 * 
	 * @param bookId 削除対象の書籍ID
	 * @param redirectAttributes リダイレクト後の画面に表示するメッセージ等を一時的に保持するためのオブジェクト
	 * @return 削除結果（論理削除）を画面に表示する
	 */
	@PostMapping("/delete")
	public String deleteBook(@RequestParam Integer bookId, RedirectAttributes redirectAttributes) {

		// 対象の書籍IDに紐づく書籍情報を論理削除する処理を呼び出し、実行結果を取得する。
		bookDeleteService.logicalDeleteBook(bookId);

		// リダイレクト後の画面で削除完了メッセージを表示するため一時的にオブジェクトに保持して画面に渡す。
		redirectAttributes.addFlashAttribute("deleteMessage",
				messageSource.getMessage("book.delete.complete", null, Locale.JAPAN));
		// 削除結果を画面に表示する。
		return "redirect:/admin/books/delete";
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
