package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;

import jakarta.validation.Valid;

/**
 * 書籍管理システムの画面遷移先ボタンを表示するクラス
 */
@Controller
public class LoginController {
	/**
	 *  ログイン画面を表示する
	 *
	 *  @return ログイン画面 books/login.htmlを表示する
	 */
	@GetMapping("/login")
	public String viewLogin(@ModelAttribute LoginForm loginForm,
		BindingResult bindingResult) {
		return "login";
	}

	@PostMapping("/login")
	public String successLogin(@Valid @ModelAttribute LoginForm loginForm,
		BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
        return "login";
		}

		// 仮の認証処理: userNameが"admin"かつpasswordが"password"の場合のみログイン成功
        if ("admin".equals(loginForm.getUserName()) && "password".equals(loginForm.getPassword())) {
            return "menu";
        }

        // それ以外はログイン画面に戻す
        return "login";
	}
}
