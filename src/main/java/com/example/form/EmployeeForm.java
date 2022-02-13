package com.example.form;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmployeeForm {
    // 社員番号
    @Id
    private Long userId;
    // 氏名
    @NotNull (message = "必須入力です")
    private String username;
    // Email
    @NotNull (message = "必須入力です")
    @Pattern (regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", message = "メールアドレス形式で入力してください")
    // @Pattern (regexp =
    // "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$")
    private String email;
    // 電話番号
    // 半角でハイフン無し
    @Pattern (regexp = "0\\d{9,10}", message = "電話番号の形式でハイフンを含めず半角数字だけで入力してください")
    @NotNull (message = "必須入力です")
    private String phone_number;
    // 性別
    // ラジオボタン
    // 初期値が選択されているのでNotBlankを削除
    private String gender;
    // 生年月日
    @NotNull (message = "必須入力です")
    @DateTimeFormat (pattern = "yyyy/MM/dd")
    private String birthday;
    // 入社日
    @NotNull (message = "必須入力です")
    @DateTimeFormat (pattern = "yyyy/MM/dd")
    private String joinday;
    // コメント
    // 最大値を１００文字制限
    @NotNull (message = "必須入力です")
    @Size (min = 1, max = 100, message = "コメントは1文字以上１００文字以内で入力してください")
    // @Size (min = 1, max = 100, message = "{validation.not-selected}")
    private String comment;
}
