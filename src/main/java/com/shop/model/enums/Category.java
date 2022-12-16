package com.shop.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    MEAT("Мясо"),
    FISH("Рыба"),
    SEAFOOD("Морепродукты"),
    EGG_PRODUCTS("Яичные продукты"),
    MILK_PRODUCTS("Молочные продукты"),
    SOY_PRODUCTS("Cоевые продукты"),
    VEGETABLE_PRODUCTS("Овощные продукты"),
    GREENERY("Зелень"),
    FRUITS("Фрукты"),
    MUSHROOMS("Грибы");

    private final String name;
}

