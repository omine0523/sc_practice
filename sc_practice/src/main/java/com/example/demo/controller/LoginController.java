package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.LoginForm;

/**
 * 書籍管理システムの画面遷移先ボタンを表示するクラス
 */
@Controller
public class LoginController {
	/**
	 *  ログイン画面を表示する
	 *
	 * 	@param loginForm ログイン画面の入力項目を受け取るフォーム
	 *  @return ログイン画面 books/login.htmlを表示する
	 */
	@GetMapping("/login")
	public String viewLogin(@ModelAttribute LoginForm loginForm) {
		return "login";
	}
}
