package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 書籍管理システムの画面遷移先ボタンを表示するクラス
 */
@Controller
public class ManagementMenuController {

	/**
	 *  メニュー画面を表示する
	 *  
	 *  @return 遷移先のボタンを表示する
	 */
	@GetMapping("/books/menu")
	public String viewMenu() {
		return "menu"; 
	}
}
