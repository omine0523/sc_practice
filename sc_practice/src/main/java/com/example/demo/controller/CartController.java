package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.config.security.LoginUser;
import com.example.demo.dto.view.CartListViewDto;
import com.example.demo.service.CartService;

import lombok.RequiredArgsConstructor;

/**
 * カート画面の表示とカートへの追加・削除処理を行う Controller クラス
 */
@Controller
@RequiredArgsConstructor
public class CartController {

    @Qualifier("CartServiceImpl")
    private final CartService cartService;

    /**
     * カート画面を表示する
     *
     * @param model ビューに渡すモデル
     * @param loginUser ログインユーザー情報
     * @return カート画面を表示する
     */
    @GetMapping("/cart")
    public String showCartPage(Model model, @AuthenticationPrincipal LoginUser loginUser) {

        // SpringSecurityで保持されているログインユーザーのIDを取得する。
        final Integer userId = loginUser.getId();

        // ログインユーザーのカート情報を取得し、ビューに渡す。
        List<CartListViewDto> cartItems = cartService.findCartItems(userId);
        model.addAttribute("cartItems", cartItems);

        // カート画面を表示する
        return "cart";
    }

    /**
     * 書籍をカートに追加する
     *
     * @param bookId 追加する書籍のID
     * @param loginUser ログインユーザー情報
     * @param redirectAttributes リダイレクト時にメッセージを渡すためのオブジェクト
     * @return 書籍検索画面にリダイレクトする
     */
    @PostMapping("/books/{bookId}/cart")
    public String addToCart(
            @PathVariable Integer bookId,
            @AuthenticationPrincipal LoginUser loginUser,
            RedirectAttributes redirectAttributes) {
        
        // SpringSecurityで保持されているログインユーザーのIDを取得する。
        final Integer userId = loginUser.getId();

        // カートに書籍を追加する
        cartService.addCartItem(userId, bookId);
        redirectAttributes.addFlashAttribute("message", "カートに追加しました");

        // 書籍検索画面にリダイレクトする
        return "redirect:/books/search";
    }

    /**
     * カートから書籍を削除する
     *
     * @param cartId 削除するカートアイテムのID
     * @param loginUser ログインユーザー情報
     * @param redirectAttributes リダイレクト時にメッセージを渡すためのオブジェクト
     * @return カート画面にリダイレクトする
     */
    @PostMapping("/cart/items/{cartId}/delete")
    public String deleteCartItem(
            @PathVariable Integer cartId,
            @AuthenticationPrincipal LoginUser loginUser,
            RedirectAttributes redirectAttributes) {

        // SpringSecurityで保持されているログインユーザーのIDを取得する。
        final Integer userId = loginUser.getId();

        // カートから書籍を削除する
        cartService.deleteCartItem(userId, cartId);
        redirectAttributes.addFlashAttribute("message", "カートから削除しました");

        // カート画面にリダイレクトする
        return "redirect:/cart";
    }
}
