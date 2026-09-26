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


@Controller
@RequiredArgsConstructor
public class CartController {

    @Qualifier("CartServiceImpl")
    private final CartService cartService;

    @GetMapping("/cart")
    public String showCartPage(Model model, @AuthenticationPrincipal LoginUser loginUser) {

        final Integer userId = loginUser.getId();

        List<CartListViewDto> cartItems = cartService.findCartItems(userId);
        model.addAttribute("cartItems", cartItems);

        return "cart";
    }

    @PostMapping("/books/{bookId}/cart")
    public String addToCart(
            @PathVariable Integer bookId,
            @AuthenticationPrincipal LoginUser loginUser,
            RedirectAttributes redirectAttributes) {

        final Integer userId = loginUser.getId();

        cartService.addCartItem(userId, bookId);
        redirectAttributes.addFlashAttribute("message", "カートに追加しました");

        return "redirect:/books/search";
    }

    @PostMapping("/cart/items/{cartId}/delete")
    public String deleteCartItem(
            @PathVariable Integer cartId,
            @AuthenticationPrincipal LoginUser loginUser,
            RedirectAttributes redirectAttributes) {

        final Integer userId = loginUser.getId();
        cartService.deleteCartItem(userId, cartId);
        redirectAttributes.addFlashAttribute("message", "カートから削除しました");

        return "redirect:/cart";
    }
}
