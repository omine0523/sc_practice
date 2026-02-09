package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.converter.BookRegisterConverter;
import com.example.demo.dto.request.BookRegisterRequestDto;
import com.example.demo.form.BookRegisterForm;
import com.example.demo.service.BookRegisterService;
import com.example.demo.service.BookSearchConditionService;

/**
 * 書籍登録画面にてフォームを表示し入力された書籍情報に登理処理を行う クラス
 */
@Controller
public class BookRegisterController {
	@Autowired
	private BookSearchConditionService bookSearchConditionService;
	@Autowired
	private BookRegisterService bookRegisterService;
	@Autowired
	private MessageSource messageSource;

	/**
	 * 選択肢をマスタから取得して書籍情報を登録するフォームを画面表示する。
	 * 
	 * @param bookRegisterForm 画面の登録項目に入力された値を受け取るフォーム
	 * @param bindingResult @Validated によるバリデーション結果
	 * @param model テーブルから取得した選択肢やメッセージをビュー渡すモデル
	 * @return 書籍登録を行うフォームを表示する。
	 */
	@GetMapping("/books/register")
	public String showBookRegisterPage(@ModelAttribute BookRegisterForm bookRegisterForm, Model model) {
		// 入力フォームを初期化する。
		model.addAttribute("bookRegisterForm", new BookRegisterForm());
		// ジャンル・置き場所を選択肢を各マスタから取得してモデルに設定し反映する。
		model.addAttribute("genres", bookSearchConditionService.findAllGenres());
		model.addAttribute("storageLocations", bookSearchConditionService.findAllStorageLocations());

		// 書籍登録画面を表示する。
		return "book-register";
	}

	/**
	 * 書籍情報を登録するため入力した情報を送信する。
	 * 
	 * @param  bookRegisterForm 画面の登録項目に入力された値を受け取るフォーム
	 * @param bindingResult @Validated によるバリデーション結果
	 * @param model テーブルから取得した選択肢やメッセージをビュー渡すモデル
	 * @return 書籍登録後の結果を表示する。
	 */
	@PostMapping("/books/register")
	public String registerBook(@Validated @ModelAttribute BookRegisterForm bookRegisterForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		// ジャンル・置き場所を選択肢を各マスタから取得してモデルに設定し反映する。
	    model.addAttribute("genres", bookSearchConditionService.findAllGenres());
	    model.addAttribute("storageLocations", bookSearchConditionService.findAllStorageLocations());
	    
	    // Form → Request DTO に変換
	    BookRegisterRequestDto requestDto = BookRegisterConverter.toRequestDto(bookRegisterForm);
		
		// 入力された内容に不備があれば各入力欄の下部にエラーメッセージを表示する。
		if (!bindingResult.hasErrors()) {
			// 入力された書籍情報を元に登録処理を呼び出す。
			bookRegisterService.registerBook(requestDto);
			// 登録処理完了後のリダイレクト画面に登録完了メッセージを表示する。
			redirectAttributes.addFlashAttribute("successMessage", 
					messageSource.getMessage("book.register.success", null, Locale.JAPAN));
			// 書籍登録処理結果を返却し登録画面を再度表示する。
			return "redirect:/books/register";
		}
		
		// エラー内容を記載して書籍登録画面を表示する。
		return "book-register";
	}

}
