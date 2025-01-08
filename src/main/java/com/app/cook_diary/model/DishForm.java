package com.app.cook_diary.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DishForm {
    @NotBlank(message = "料理名は必須です")
    // 料理名
    private String name;
    // 調理時間
    private Integer preparationTime;
    // 金額
    private Integer cost;
}
