package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Genre;
import com.example.demo.entity.StorageLocation;

/**
 * 検索条件内のセレクトボタンの項目内容を取得する インターフェース
 * 
 */
public interface BookSearchConditionService {
	
	/**
	 * 検索条件：ジャンルの選択肢を押下した際の項目情報を取得する
	 * @return 選択肢の項目を返却する
	 */
	List<Genre> findAllGenres();
	
	/**
	 * 検索条件：置き場所の選択肢を押下した際の項目情報を取得する
	 * @return 選択肢の項目を返却する
	 */
    List<StorageLocation> findAllStorageLocations();
}
