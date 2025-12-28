package com.suraj.Expense_Tracker.service;

import com.suraj.Expense_Tracker.dto.ExpenseRequest;
import com.suraj.Expense_Tracker.dto.ExpenseResponse;
import com.suraj.Expense_Tracker.model.*;
import com.suraj.Expense_Tracker.repository.*;
import com.suraj.Expense_Tracker.security.SecurityUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseResponse createExpense(ExpenseRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow();

        Expense expense = new Expense();
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setUser(user);
        expense.setCategory(category);

        Expense saved = expenseRepository.save(expense);

        return mapToResponse(saved);
    }

    public List<ExpenseResponse> getMyExpenses() {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        return expenseRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ExpenseResponse mapToResponse(Expense expense) {
        ExpenseResponse res = new ExpenseResponse();
        res.id = expense.getId();
        res.amount = expense.getAmount();
        res.description = expense.getDescription();
        res.expenseDate = expense.getExpenseDate();
        res.category = expense.getCategory().getName();
        return res;
    }

    public ExpenseResponse updateExpense(Long expenseId, ExpenseRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // SECURITY CHECK
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository
                    .findById(request.getCategoryId())
                    .orElseThrow();
            expense.setCategory(category);
        }

        Expense updated = expenseRepository.save(expense);
        return mapToResponse(updated);
    }

    public void deleteExpense(Long expenseId) {

        String email = SecurityUtils.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow();

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        // SECURITY CHECK
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        expenseRepository.delete(expense);
    }
}
