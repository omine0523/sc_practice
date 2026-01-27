package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BookSearchResultDto;

/**
 *  BookInfoMapper インターフェース
 *  メソッド名をキーとして、BookInfoMapper.xmlに定義されたSQLを実行する
 * 
 * @param bookId 書籍番号
 * @return 書籍情報一覧を取得して返却する
 */
@Mapper
public interface BookInfoMapper {
	/**
	 * 登録されている書籍情報を全件取得する
	 * @return 全件取得した書籍一覧
	 */
	List<BookSearchResultDto> selectAllBooks();
	
	/**
	 * 検索条件と一致している書籍情報を一覧で取得する。
	 * 
	 * @param bookId 書籍ID
	 * @param bookName 書籍名
	 * @param genreId ジャンルID
	 * @param storageLocationId 置き場所ID
	 * @return 検索条件を満たす書籍情報一覧
	 */
	List<BookSearchResultDto> selectBookByConditions(
			@Param("bookId") Integer bookId, 
			@Param("bookName") String bookName,
	        @Param("fkGenreId") Integer fkGenreId,
	        @Param("fkStorageLocationId") Integer fkStorageLocationId);
}
