package com.example.demo.service;

import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.entity.BookInfo;
import com.example.demo.form.BookSearchForm;
import com.example.demo.mapper.BookInfoMapper;

/** 
 * 入力した書籍IDの書籍を検索する インターフェースの実装クラス
 *
 */
@Service
public class BookSearchServiceImpl implements BookSearchService {
	
	@Autowired
	private BookInfoMapper bookInfoMapper;
	
	/**
	 * 入力した検索条件で該当書籍を検索する
	 * 
	 * @param bookSearchForm 画面の検索条件の値が格納されたフォーム
	 * @return 画面で入力・選択した検索条件と一致した書籍情報を返却する
	 */
	@Override
	public List<BookInfo> searchBookById(BookSearchForm bookSearchForm){
		
		Integer bookId = null;
		
		// formで入力した値をMapperクラスに渡すため、DBの型に合わせて変換する
		if (StringUtils.hasText(bookSearchForm.getBookId())) {
        	// 全角 → 半角に正規化する
            String normalized =
                    Normalizer.normalize(bookSearchForm.getBookId(), Normalizer.Form.NFKC);
            // 文字列を数値に変換する
            bookId = Integer.valueOf(normalized);
        }
        // 全ての検索条件の値を引数で渡し、書籍情報を検索する
		return bookInfoMapper.findBookById(
				bookId,
				bookSearchForm.getGenre(),
				bookSearchForm.getStorageLocation());
	}
}
