package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * ログイン画面で入力された値をバインドするformクラス。
 */
@Data
public class LoginForm {
    /** ユーザーネーム */
    @NotNull(message = "{error.user.name.required}")
    @Size(max = 32, message = "{error.user.name.max}")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,32}$", message = "{error.user.name.alphanumeric.only}")
    private String userName;
    /** パスワード */
    @NotNull(message = "{error.user.password.required}")
    @Size(min = 8, max = 32, message = "{error.user.password.length}")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}$", message = "{error.user.password.alphanumeric.only}")
    private String password;
}
