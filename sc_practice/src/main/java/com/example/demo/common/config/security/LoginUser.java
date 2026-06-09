package com.example.demo.common.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 独自のUserDetails実装クラス。
 * アプリケーション固有のユーザー情報（表示名など）を保持できるように拡張します。
 */
@Getter
@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private final UserInfo userInfo;

    // 表示名を取得するためのメソッド
    public String getDisplayName() {
        return userInfo.getDisplayName();
    }

    // 権限を日本語で表示するためのメソッド
    public String checkRoleLabel() {
        return "ADMIN".equals(userInfo.getRole())
                ? "管理者"
                : "ユーザー";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ロールに "ROLE_" プレフィックスを付与して SimpleGrantedAuthority を作成
        // SecurityConfigでの hasRole("ADMIN") 等の判定に対応させます
        return List.of(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole()));
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userInfo.getIsActive();
    }
}