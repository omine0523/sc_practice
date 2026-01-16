package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.BookInfo;

/**
 *  BookInfoMapperインターフェース
 *  findBookByIdメソッドをキーとして、BookInfoMapper.xmlに定義されたSQLを実行する
 * 
 * @param bookId 書籍番号
 * @return 書籍情報一覧を取得して返却する
 */
@Mapper
public interface BookInfoMapper {
	
	/**
	 * 検索条件と一致している書籍情報を一覧で取得する。
	 * 
	 * @param bookId 書籍ID
	 * @param genre ジャンル
	 * @param storageLocation 置き場所
	 * @return 検索条件を満たす書籍情報一覧
	 */
	List<BookInfo> findBookByConditions(
			@Param("bookId") Integer bookId,
	        @Param("genre") String genre,
	        @Param("storageLocation") String storageLocation);
}
