package com.example.demo.entity;

import lombok.Data;

/**
 * 置き場所を表す Entity クラス
 */
@Data
public class StorageLocation {
	
	/** 置き場所ID（主キー） */
    private Integer storageLocationId;
    /** 置き場所名（主キー） */
    private String storageLocationName;
}