package com.example.demo.service;

import com.example.demo.dto.view.BookDetailViewDto;

/**
 * Controllerから受け取った書籍IDを用いて書籍詳細内容を表示する インターフェース
 */
public interface BookDetailService {
    /**
     * 書籍IDをもとに書籍情報を1件取得する
     *
     * @param id 書籍ID
     * @return 取得した書籍情報
     */
    BookDetailViewDto findBookById(Integer id);

}