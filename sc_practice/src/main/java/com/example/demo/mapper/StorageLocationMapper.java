package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.StorageLocation;

/**
 *  StorageLocationMapper インターフェース
 *  メソッド名をキーとして、GenreMapper.xmlに定義されたSQLを実行する
 * 
 * @return 置き場所の一覧を取得して返却する
 */
@Mapper
public interface StorageLocationMapper {
	/**
	 * テーブルに登録されている置き場所を一覧で取得する
	 * @return 置き場所一覧を返却する
	 */
    List<StorageLocation> selectAllStorageLocations();
}