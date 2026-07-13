package com.example.demo.dto.view;

import java.util.List;

import lombok.Data;

/**
 * ページング結果を表す共通DTOクラス。
 * <p>
 * 取得する一覧データと、ページングに必要な情報
 * （現在ページ・総ページ数・総件数）をまとめて保持する。
 * </p>
 * @param <T> 一覧に格納するDTOの型（例：BookListViewDto など）
 */
@Data
public class PageResult<T>  {

	/** 一覧表示用データ */
	private List<T> list;
	/** 現在のページ番号（1始まり） */
	private int currentPage;
	 /** 総ページ数 */
	private int totalPages;
	/** 総件数 */
	private int totalCount;
	
	/**
     * PageResult コンストラクタ
     *
     * @param list 一覧表示データ
     * @param currentPage 現在のページ番号
     * @param totalPages 総ページ数
     * @param totalCount 総件数
     */
	public PageResult(List<T>list, int currentPage, int totalPages, int totalCount) {
		this.list = list;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.totalCount = totalCount;
		}
	
}
