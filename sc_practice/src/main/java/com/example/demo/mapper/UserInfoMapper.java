package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.UserInfo;

/**
 * ユーザー情報をDBから取得するMapper
 */
@Mapper
public interface UserInfoMapper {
    /**
     * ユーザーネームをキーにユーザー情報を検索する
     * @param userName ユーザーネーム
     * @return ユーザー情報
     */
    UserInfo selectUserName(String userName);
}
