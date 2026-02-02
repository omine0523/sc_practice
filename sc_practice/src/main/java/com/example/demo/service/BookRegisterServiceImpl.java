package com.example.demo.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.BookRegisterRequestDto;
import com.example.demo.exception.BookRegisterException;
import com.example.demo.mapper.BookInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 入力された書籍情報の登録処理を実行する 実装クラス
 */
@Slf4j
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class BookRegisterServiceImpl implements BookRegisterService {
	
	@Autowired
	private BookInfoMapper bookInfoMapper;

	/**
	 * 入力された書籍情報を元に登録処理を実行する
	 * 処理中にエラーが発生した際にはロールバックが実行する
	 * 
	 * @param bookRegisterForm 登録画面で入力された書籍情報
	 */
	@Transactional
	@Override
	public void registerBook(BookRegisterRequestDto requestDto) {
			
			// 書籍情報を登録する処理を実行する
		    try {
		        bookInfoMapper.insertBook(requestDto);
		    } catch (PersistenceException e) {
		    	// DB操作中に起きる問題（SQL文のエラー、接続問題、データ整合性違反など）が発生した場合は
		    	// エラーログを出力し、DB操作中の例外はカスタム例外に変換してメッセージと共にを投げる
		    	log.error("書籍登録失敗", e);
		        throw new BookRegisterException("書籍登録に失敗しました", e);
		    }
		    
	}

}
