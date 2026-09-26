package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.view.CartListViewDto;

public interface CartService {

    /**
     * カート一覧を取得する
     */
    List<CartListViewDto> findCartItems(Integer userId);

    /**
     * カートに書籍を追加する
     */
    void addCartItem(Integer userId, Integer bookId);

    /**
     * カートから書籍を1件削除する
     */
    void deleteCartItem(Integer userId, Integer bookId);

    /**
     * カートを空にする
     */
    void clearCart(Integer userId);
}
