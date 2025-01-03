package com.app.cook_diary.controller;

import com.app.cook_diary.exception.UserRegistrationException;
import com.app.cook_diary.model.AccountForm;
import com.app.cook_diary.service.AccountService;
import com.app.cook_diary.service.CustomUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final CustomUserDetailsService customUserDetailsService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AccountForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid AccountForm accountForm, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            // バリデーションエラーの場合、登録画面を再表示
            return "register";
        }

        try {
            // ユーザー登録処理呼び出し
            accountService.registerUser(accountForm);
        } catch (UserRegistrationException e) {
            // エラーメッセージをモデルに設定
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }

        // 自動ログイン
//        try {
//            // ユーザー詳細をロード
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(accountForm.getEmail());
//
//            // 認証トークンを作成
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(userDetails, accountForm.getPassword(), userDetails.getAuthorities());
//
//            // 認証を試みる
//            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//            // 認証成功時にセキュリティコンテキストに設定
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            System.out.println("Authentication successful: " + userDetails.getUsername());
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("error", "自動ログインに失敗しました。");
//            return "redirect:/login";
//        }

        // 登録成功時のリダイレクト
        // 登録成功時のメッセージをリダイレクト属性に設定
        redirectAttributes.addFlashAttribute("successMessage", "ユーザー登録完了しました。");
        return "redirect:/dishes";
    }
}
