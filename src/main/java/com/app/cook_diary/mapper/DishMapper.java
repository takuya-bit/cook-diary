package com.app.cook_diary.mapper;

import com.app.cook_diary.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {
    List<Dish> getAllDishes(Integer userId);

}