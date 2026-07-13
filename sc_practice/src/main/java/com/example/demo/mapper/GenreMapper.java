package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Genre;


/**
 *  GenreMapper インターフェース
 *  メソッド名をキーとして、GenreMapper.xmlに定義されたSQLを実行する
 * 
 * @return ジャンルの一覧を取得して返却する
 */
@Mapper
public interface GenreMapper {
	/**
	 * テーブルに登録されているジャンルを一覧で取得する
	 * @return ジャンル一覧を返却する
	 */
	List<Genre>selectAllGenres();
}