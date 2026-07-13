package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 書籍管理システムの画面遷移先ボタンを表示するクラス
 */
@Controller
@RequestMapping("/books")
public class ManagementMenuController {

	/**
	 *  メニュー画面を表示する
	 *  
	 *  @return 遷移先のボタンを表示する
	 */
	@GetMapping("/menu")
	public String viewMenu() {
		return "menu";
	}
}
