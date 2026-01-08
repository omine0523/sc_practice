package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.BookInfo;

/**
 *  BookInfoMapperインターフェース
 *  findBookByIdメソッドをキーとして、BookInfoMapper.xmlに定義されたSQLを実行する
 * 
 * @param bookId 書籍番号
 * @return 書籍情報一覧（検索条件なし）を取得して返却する
 */
@Mapper
public interface BookInfoMapper {
	/**
	 * BookInfoの entity に定義されている項目で一覧取得する
	 */
	List<BookInfo> findBookById(int bookId);
}
