package com.app.cook_diary.config;

import com.app.cook_diary.security.CustomAuthenticationFailureHandler;
import com.app.cook_diary.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    // AuthenticationManager を Bean として定義
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthenticationFailureHandler failureHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // 必要に応じて無効化
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/register", "/login", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                ) // 他は認証が必要
                .formLogin(form -> form
                        .loginPage("/login") // ログイン画面のURL
                        .usernameParameter("username") // ユーザー名にメールアドレスを使用
                        .passwordParameter("password") // パスワードのパラメータ名
                        .failureHandler(failureHandler) // カスタムハンドラーを設定
                        .defaultSuccessUrl("/dishes", true) // 認証成功後のリダイレクト先
                        .permitAll() // ログインページは全員アクセス可能
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // ログアウトのURL
                        .logoutSuccessUrl("/") // ログアウト成功後のリダイレクト先
                        .permitAll() // ログアウトは全員アクセス可能
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
