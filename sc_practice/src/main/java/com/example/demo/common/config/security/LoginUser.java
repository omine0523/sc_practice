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
 * アプリケーション固有のユーザー情報（表示名など）を保持できるように拡張し
 * UserDetailsインターフェースを実装する。
 */
@Getter
@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private final UserInfo userInfo;

    // 表示名を取得するgetterメソッド。
    public String getDisplayName() {
        return userInfo.getDisplayName();
    }

    // 権限を日本語で表示するためのメソッド
    public String checkRoleLabel() {
        return "ADMIN".equals(userInfo.getRole())
                ? "管理者"
                : "ユーザー";
    }
    // ユーザーの権限を返す。DBから取得したユーザー情報のロールを
    // "ROLE_" プレフィックス付きで SimpleGrantedAuthority に変換して返して
    // SecurityConfigでの hasRole("ADMIN") 等の判定に対応させる。
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(new SimpleGrantedAuthority("ROLE_" + userInfo.getRole()));
    }
    // ユーザーネームを返す。DBから取得したユーザー情報のユーザーネームを返す。
    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }
    // パスワードを返す。DBから取得したユーザー情報のパスワードを返す。
    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }
    // アカウントが期限切れでないかを返す。ここでは常にtrueを返す。
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // アカウントがロックされていないかを返す。ここでは常にtrueを返す。
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // パスワードの有効期限が切れていないかを返す。ここでは常にtrueを返す。
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // ユーザーが有効かどうかを返す。DBのisActiveフラグを参照している。
    @Override
    public boolean isEnabled() {
        return userInfo.getIsActive();
    }
}