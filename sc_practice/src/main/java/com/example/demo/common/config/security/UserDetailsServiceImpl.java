package com.example.demo.common.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * Spring Securityの認証処理でユーザー情報を
 * DBからロードするUserDetailsServiceの実装クラス
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoMapper userInfoMapper;

    /**
     * ユーザーネームをキーにユーザー情報をDBから取得し、
     * Spring Securityが理解できるUserDetailsオブジェクトに詰め替えて返す
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

        // Spring Securityが理解できる UserDetails オブジェクトに詰め替えて返す
        return User.withUsername(userInfo.getUserName())
                .password(userInfo.getPassword())
                .authorities(userInfo.getRole())
                .disabled(!userInfo.getIsActive())
                .build();
    }
}
