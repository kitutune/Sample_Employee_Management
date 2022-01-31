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
    @NotNull (message = "空白は許可されていません")
    private Long user_id;
    // 氏名
    @NotBlank
    private String user_name;
    // Email
    @NotBlank
    private String email;
    // 電話番号
    // 半角でハイフン無し
    @Pattern (regexp = "0\\d{9,10}", message = "電話番号の形式でハイフンを含めず半角数字だけで入力してください")
    @NotBlank
    private String phone_number;
    // 性別
    // ラジオボタン
    @NotBlank
    private String gender;
    // セレクトボックス
    // 生年月日：年、月、日で入力分けている
    // @NotBlank
    private int date_of_birth_year;
    // @NotBlank
    private int date_of_birth_month;
    // @NotBlank
    private int date_of_birth_day;
    // 入社日：生年月日と同じ仕様
    // @NotBlank
    private int joining_date_year;
    // @NotBlank
    private int joining_date_month;
    // @NotBlank
    private int joining_date_day;
    // コメント
    // 最大値を１００文字制限
    // @NotBlank
    // @Max (100)
    // @Min (1)
    @Size (min = 1, max = 100)
    private String comment;
}
