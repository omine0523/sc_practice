package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.request.BookRegisterRequestDto;
import com.example.demo.dto.view.BookListViewDto;

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
	List<BookListViewDto> selectAllBooks(int pageSize, int offset);
	
	
	/**
	 * 検索条件と一致している書籍情報を一覧でページネーションごとに取得する。
	 * 
	 * @param bookId 書籍ID
	 * @param bookName 書籍名
	 * @param genreId ジャンルID
	 * @param storageLocationId 置き場所ID
	 * @param pageSize ページサイズ
	 * @param offset 現在のページ
	 * @return 検索条件を満たす書籍情報一覧
	 */
	List<BookListViewDto> selectBookByConditions(
			@Param("bookId") Integer bookId, 
			@Param("bookName") String bookName,
	        @Param("fkGenreId") Integer fkGenreId,
	        @Param("fkStorageLocationId") Integer fkStorageLocationId,
	        int pageSize,
	        int offset
	        );
	
	
	/**
	 * 登録されている全書籍の件数をページネーション用に取得する。
	 * 
	 * @return 全ての書籍件数の取得結果
	 */
	int countAllBooks();
	
	
	/**
	 * 指定された検索条件で該当する書籍の件数をページネーション用に取得する。
	 * 
	 * @param bookId 書籍ID
	 * @param bookName 書籍名
	 * @param genreId ジャンルID
	 * @param storageLocationId 置き場所ID
	 * @return 書籍情報件数の取得結果
	 */
	int countBookByConditions(
			@Param("bookId") Integer bookId, 
			@Param("bookName") String bookName,
	        @Param("fkGenreId") Integer fkGenreId,
	        @Param("fkStorageLocationId") Integer fkStorageLocationId
		    );
	
	
	/**
	 * 画面で入力した書籍情報をDBに登録する。
	 * 
	 * @param requestDto 登録用のDTO
	 */
	void insertBook(BookRegisterRequestDto requestDto);
	
	
	/**
	 * 登録されている書籍情報の削除フラグをtrueにして書籍一覧から非表示にする。
	 * 
	 * @param bookId 一覧で表示されている削除対象の書籍ID
	 * @return 
	 */
	int logicalDeleteBook(Integer bookId);
}
