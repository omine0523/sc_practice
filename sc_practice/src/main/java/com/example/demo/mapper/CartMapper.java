package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.param.CartInsertParam;
import com.example.demo.dto.view.CartListViewDto;

/**
 * CartMapper インターフェース メソッド名をキーとして、CartMapper.xmlに定義されたSQLを実行する
 */
@Mapper
public interface CartMapper {
    /**
     * 指定されたユーザーIDに紐づくカート情報を取得する
     *
     * @param userId ユーザーID
     * @return カート情報のリスト
     */
    List<CartListViewDto> selectCartItemsByUserId(Integer userId);

	/**
     * カートに書籍情報を追加する
     *
     * @param param カート情報
     */
    void insertCart(CartInsertParam param);

    /**
	 * 指定されたユーザーIDと書籍IDに紐づくカート情報を1件削除する
	 * @param userId ユーザーID
	 * @param bookId 書籍ID
	 */
    void deleteCartItemByUserIdAndBookId(Integer userId, Integer bookId);
    
	/**
	 * 指定されたユーザーIDに紐づくカート情報を全件削除する
	 * @param userId ユーザーID
	 */
	void clearCartByUserId(Integer userId);
}
