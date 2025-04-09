package com.expense.tracker.repository;

import com.expense.tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("SELECT e FROM Expense e where e.category.id = ?1")
    List<Expense> findByCategoryId(long categoryId);

    @Query("SELECT e FROM Expense e WHERE e.date BETWEEN ?1 AND ?2")
    List<Expense> findByDateRange(LocalDate startDate,LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category.id = ?1")
    BigDecimal getTotalAmountByCategory(Long categoryId);

    @Query("SELECT e FROM Expense e")
    List<Expense> getAllExpenses();


}
