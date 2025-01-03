package com.app.cook_diary.controller;

import com.app.cook_diary.entity.Dish;
import com.app.cook_diary.security.CustomUserDetails;
import com.app.cook_diary.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/dishes")
    public String getAllDishes(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        Integer userId = customUserDetails.getId();
        model.addAttribute("dishes", dishService.getAllDishes(userId));
        return "dishes";
    }

    @GetMapping("/dishes/new")
    public String newDishForm(Model model) {
        model.addAttribute("dish", new Dish());
        return "newDish";
    }

    @PostMapping("/dishes")
    public String saveDish(@ModelAttribute("dish") Dish dish) {
//        dishService.saveDish(dish);
        return "redirect:/dishes";
    }
}
