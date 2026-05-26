package com.example.demo.common.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Securityの設定クラス。セキュリティに関する共通設定を行う。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * セキュリティフィルターチェーンのBean定義。HTTPセキュリティの設定を行う。
     *
     * @param http HttpSecurityオブジェクト
     * @return SecurityFilterChainのインスタンス
     * @throws Exception セキュリティ設定に関する例外が発生した場合
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 認証と認可の設定
                .authorizeHttpRequests(authz -> authz
                // 静的リソース（css, js等）を認証除外
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                // ログインページは全てのユーザーがアクセス可能
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
                )
                // フォームログインの設定
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // フィルタが直接受け取るURL
                .defaultSuccessUrl("/books/menu", true)
                .failureUrl("/login?error")
                .permitAll()
                )
                // ログアウトの設定
                .logout(logout -> logout
                .logoutUrl("/logout") // ログアウト処理のパス
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true) // セッションを無効化
                .deleteCookies("JSESSIONID") // クッキーを削除
                );

        return http.build();
    }

    /**
     * パスワードエンコーダーのBean定義。BCryptアルゴリズムを使用してパスワードをハッシュ化する。
     * ユーザーが入力したパスワードをこのエンコーダーでハッシュ化し、
     * DBに保存されているハッシュ値と比較する。
     *
     * @return BCryptPasswordEncoderのインスタンス
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
