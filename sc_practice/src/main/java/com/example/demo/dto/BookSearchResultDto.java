package com.example.demo.dto;

import lombok.Data;

/**
 * 書籍検索結果を画面表示するための DTO
 * （JOIN した名称系データを保持する）
 */
@Data
public class BookSearchResultDto {

    private Integer bookId;
    private String bookName;
    private String genreName;
    private String storageLocationName;
    private String status;
}
