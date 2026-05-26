package com.example.demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー情報を表す Entity クラス
 */
@Data
public class UserInfo {
	/** ID（主キー） */
    private int id;

    /** ユーザー名 */
    private String userName;
    
    /** 表示名 */
    private String displayName;

    /** パスワード */
    private String password;

    /** 作成日時 */
    private LocalDateTime createdAt;

    /** 更新日時 */
    private LocalDateTime updatedAt;

    /** 権限 */
    private String role;

    /** 有効フラグ */
    private Boolean isActive;
}