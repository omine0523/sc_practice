package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BookInfo;
import com.example.demo.mapper.BookInfoMapper;

/** 
 * 入力した書籍IDの書籍を検索する インターフェースの実装クラス
 * 
 * @param bookId 書籍ID
 * @return 入力情報で一致した書籍情報を返却する
 */
@Service
public class BookSearchServiceImpl implements BookSearchService {
	
	@Autowired
	private BookInfoMapper bookInfoMapper;
	
	/**
	 * 入力した書籍IDで該当書籍を検索する(検索条件なし)
	 */
	@Override
	public List<BookInfo> searchBookById(int bookId){
		return bookInfoMapper.findBookById(bookId);
	}
}
