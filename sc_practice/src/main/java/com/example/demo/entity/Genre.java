package com.example.demo.entity;

import lombok.Data;

/**
 * ジャンルを表す Entity クラス
 */
@Data
public class Genre {
	/** ジャンルID（主キー） */
    private Integer genreId;
    /** ジャンル名 */
    private String genreName;
}