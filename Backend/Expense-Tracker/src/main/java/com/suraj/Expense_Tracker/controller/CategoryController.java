package com.suraj.Expense_Tracker.controller;

import com.suraj.Expense_Tracker.model.Category;
import com.suraj.Expense_Tracker.repository.CategoryRepository;
import com.suraj.Expense_Tracker.security.SecurityUtils;
import com.suraj.Expense_Tracker.repository.UserRepository;
import com.suraj.Expense_Tracker.model.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryController(CategoryRepository categoryRepository,
                              UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Category> getMyCategories() {
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();
        return categoryRepository.findByUserId(user.getId());
    }
}