package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.BookRegisterForm;
import com.example.demo.service.BookSearchConditionService;

/**
 * フォームで入力された書籍情報の登理処理に向けて制御する クラス
 */
@Controller
public class BookRegisterController {
	@Autowired
	private BookSearchConditionService bookSearchConditionService;

	/**
	 * フォームで入力した書籍情報を登録する
	 * 
	 * @return 登録結果を表示する
	 */
	@GetMapping("/books/register")
	public String showBookRegisterPage(@ModelAttribute BookRegisterForm bookRegisterForm,
			Model model) {

		// ジャンル・置き場所を各テーブルから取得してモデルに設定し、セレクトボタン内の選択肢に反映する
		model.addAttribute("genres", bookSearchConditionService.findAllGenres());
		model.addAttribute("storageLocations", bookSearchConditionService.findAllStorageLocations());

		// 書籍登録画面を表示する。
		return "book-register";
	}
}
