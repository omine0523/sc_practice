package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.view.BookDetailViewDto;
import com.example.demo.service.BookDetailService;

import lombok.RequiredArgsConstructor;


/**
 * 選択された書籍IDに基づいた書籍詳細画面を表示するクラス
 */
@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookDetailController {

	private final BookDetailService bookDetailService;

	/**
	 * 書籍一覧から選択した書籍IDに基づいた書籍詳細画面を表示する
	 *
	 * @param id パスパラメータから受け取る書籍ID
	 * @param model ビューに渡すモデル
	 * @return IDに基づいた書籍詳細画面を表示する
	 */
	@GetMapping("/detail/{id}")
	public String showBookDetailPage(@PathVariable Integer id, Model model) {
		// IDをもとに書籍の情報を1件取得する
		BookDetailViewDto book = bookDetailService.findBookById(id);
		model.addAttribute("bookDetail", book);

		// book-detail.html を表示
		return "book-detail";
	}
}