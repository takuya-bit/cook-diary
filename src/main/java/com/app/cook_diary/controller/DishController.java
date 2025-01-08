package com.app.cook_diary.controller;

import com.app.cook_diary.entity.Dish;
import com.app.cook_diary.model.DishForm;
import com.app.cook_diary.security.CustomUserDetails;
import com.app.cook_diary.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/dishes/new")
    public String saveDish(@ModelAttribute("dish") Dish dish) {
        dishService.registerDish(dish);
        return "redirect:/dishes";
    }

    @PostMapping(value = "/dishes/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadRecipe(
            @RequestPart("data") DishForm dishForm,
            @RequestPart("image") MultipartFile image) {
        try {
            String imagePath = dishService.registerDish(dishForm, image);
            return ResponseEntity.ok("Recipe saved with image path: " + imagePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
