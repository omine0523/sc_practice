package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Genre;
import com.example.demo.entity.StorageLocation;
import com.example.demo.mapper.GenreMapper;
import com.example.demo.mapper.StorageLocationMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *  検索条件内のセレクトボタンの項目内容を取得する 実装クラス
 * 
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class BookQueryConditionServiceImpl implements BookQueryConditionService {

	private final GenreMapper genreMapper;
	
	private final StorageLocationMapper storageLocationMapper;
	/**
	 * 検索条件：ジャンルの選択肢を押下した際の項目情報を取得する
	 * @return 選択肢の項目を一覧で抽出し結果を返却する
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Genre> findAllGenres() {
		return genreMapper.selectAllGenres();
	}

	/**
	 * 検索条件：置き場所の選択肢を押下した際の項目情報を取得する
	 * @return 選択肢の項目を一覧で抽出し結果を返却する
	 */
	@Transactional(readOnly = true)
	@Override
	public List<StorageLocation> findAllStorageLocations() {
		return storageLocationMapper.selectAllStorageLocations();
	}

}
