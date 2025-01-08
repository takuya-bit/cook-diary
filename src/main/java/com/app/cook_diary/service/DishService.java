package com.app.cook_diary.service;

import com.app.cook_diary.entity.Dish;
import com.app.cook_diary.mapper.DishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishMapper dishMapper;

    public List<Dish> getAllDishes(Integer userId) {
        return dishMapper.getAllDishes(userId);
    }

    public void registerDish(Dish dish) {
        dishMapper.registerDish(dish);
    }
}
