package com.example.demo.common.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * Spring Securityの認証処理でユーザー情報を
 * DBからロードUserDetailsServiceの実装クラス
 * ユーザー名をキーにユーザー情報を取得し、独自UserDetailsオブジェクトに詰め替えて返す。
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoMapper userInfoMapper;

    /**
     * ユーザーネームをキーにユーザー情報をDBから取得し、
     * Spring SecurityのUserDetailsオブジェクトに詰め替えて返す
     * @param username ユーザーネーム
     * @throws UsernameNotFoundException ユーザーが見つからなかった場合の例外
     * @return ユーザー情報
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // DBからユーザーを取得
        UserInfo userInfo = userInfoMapper.selectUserName(username);

        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // 独自の LoginUser オブジェクトに詰め替えて返す
        return new LoginUser(userInfo);
    }
}
