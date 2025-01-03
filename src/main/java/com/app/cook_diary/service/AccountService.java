package com.app.cook_diary.service;

import com.app.cook_diary.exception.UserRegistrationException;
import com.app.cook_diary.mapper.AccountMapper;
import com.app.cook_diary.model.AccountForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(AccountForm accountForm) {
        // パスワードをエンコード
        String encodedPassword = passwordEncoder.encode(accountForm.getPassword());
        accountForm.setPassword(encodedPassword);

        // メールアドレスが既に登録済みの場合
        if (Objects.nonNull(accountMapper.verifyExistsUser(accountForm.getEmail())))  {
            throw new UserRegistrationException("このメールアドレスは既に登録されています: " + accountForm.getEmail());
        }
        accountMapper.registerAccount(accountForm);
    }
}
