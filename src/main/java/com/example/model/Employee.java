package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Employee {
    // 社員番号
    @Id
    @NotNull (message = "必須入力です")
    private Long user_id;
    // 氏名
    @NotBlank (message = "必須入力です")
    private String user_name;
    // Email
    @NotBlank (message = "必須入力です")
    @Pattern (regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$", message = "メールアドレス形式で入力してください")
    private String email;
    // 電話番号
    // 半角でハイフン無し
    @Pattern (regexp = "0\\d{9,10}", message = "電話番号の形式でハイフンを含めず半角数字だけで入力してください")
    @NotBlank (message = "必須入力です")
    private String phone_number;
    // 性別
    // ラジオボタン
    // 初期値が選択されているのでNotBlankを削除
    private String gender;
    // セレクトボックス
    // 生年月日：年、月、日で入力分けている
    // 初期値が選択されているのでNotBlankを削除
    private int birth_year;
    private int birth_month;
    private int birth_day;
    // 入社日：生年月日と同じ仕様
    private int jdate_year;
    private int jdate_month;
    private int jdate_day;
    // コメント
    // 最大値を１００文字制限
    
    @Size (min = 1, max = 100, message = "コメントは1文字以上100文字以下で入力してください")
    private String comment;
}
