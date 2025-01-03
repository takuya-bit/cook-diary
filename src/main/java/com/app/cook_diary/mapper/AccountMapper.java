package com.app.cook_diary.mapper;

import com.app.cook_diary.entity.Account;
import com.app.cook_diary.model.AccountForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {

    Account findByEmail(String email);

    void registerAccount(AccountForm accountForm);

    Integer verifyExistsUser(String email);
}