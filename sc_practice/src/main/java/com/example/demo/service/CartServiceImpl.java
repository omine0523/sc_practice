package com.example.demo.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.param.CartInsertParam;
import com.example.demo.dto.view.CartListViewDto;
import com.example.demo.mapper.CartMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * CartService インターフェースの実装クラス
 * カートに関するビジネスロジックを提供する
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;

    /**
     * カート一覧を取得する
     */
    @Override
    @Transactional(readOnly = true)
    public List<CartListViewDto> findCartItems(Integer userId) {
        return cartMapper.selectCartItemsByUserId(userId);
        
    }

    /**
     * カートに書籍を追加する
     *
     * @param userId ユーザーID
     * @param bookId 書籍ID
     */
    @Override
    public void addCartItem(Integer userId, Integer bookId) {

        CartInsertParam param = new CartInsertParam(userId, bookId);

        try {
            // データベースにカートに追加する書籍情報を追加する
            cartMapper.insertCart(param);
            log.info("カート追加成功 userId={}, bookId={}", userId, bookId);

        } catch (DuplicateKeyException e) {
            // 重複キー例外が発生した場合、既にカートに追加されていることをログに出力する
            log.info("既にカートに追加されています。userId={}, bookId={}", userId, bookId);
        }
    }

    /**
     * カートから書籍を1件削除する
     */
    @Override
    public void deleteCartItem(Integer userId, Integer bookId) {
        // 実際の実装では、CartMapperを使用してデータベースからカート情報を削除する
    }

    /**
     * カートを空にする
     */
    @Override
    public void clearCart(Integer userId) {
        // 実際の実装では、CartMapperを使用してデータベースからカート情報を削除する
    }
}
