package com.suraj.Expense_Tracker.repository;

import com.suraj.Expense_Tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long userId);

    @Query("""
        SELECT c.name, SUM(e.amount)
        FROM Expense e
        JOIN e.category c
        WHERE e.user.id = :userId
          AND MONTH(e.expenseDate) = :month
        GROUP BY c.name
    """)
    List<Object[]> getMonthlySummary(Long userId, int month);
}