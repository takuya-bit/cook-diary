package com.app.cook_diary.service;




import com.app.cook_diary.entity.Account;
import com.app.cook_diary.mapper.AccountMapper;
import com.app.cook_diary.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Primary
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // データベースからメールアドレスでユーザーを取得
        Account account = accountMapper.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // UserDetails オブジェクト（認証情報）を作成して返す
        return new CustomUserDetails(
                account.getId(), // Accountテーブルのid
                account.getEmail(),
                account.getPassword(),
                null // 権限は必要に応じて設定
        );
//        return User.builder()
//                .username(userEntity.getEmail())
//                .password(userEntity.getPassword())
//                .roles(userEntity.getRole())
//                .build();
    }
}

