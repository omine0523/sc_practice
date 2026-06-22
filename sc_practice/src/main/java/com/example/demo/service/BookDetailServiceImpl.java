package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.view.BookDetailViewDto;
import com.example.demo.mapper.BookInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
@RequiredArgsConstructor
public class BookDetailServiceImpl implements BookDetailService {

    private final BookInfoMapper bookInfoMapper;

    /**
	 * 書籍IDをもとに書籍情報を1件取得する
	 *
	 * @param id 書籍ID
	 * @return 取得した書籍情報
	 */
	@Override
	@Transactional(readOnly = true)
	public BookDetailViewDto findBookById(Integer id) {
		return bookInfoMapper.selectBookById(id);
	}

}
