package com.expense.tracker.service;
import com.expense.tracker.model.Expense;
import com.expense.tracker.model.Category;
import com.expense.tracker.model.ExpenseRequest;
import com.expense.tracker.repository.ExpenseRepository;
import com.expense.tracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return repository.findById(id);
    }

    public Expense saveExpense(Expense expense) {
        if (expense.getCategory() != null && expense.getCategory().getName() != null) {
            String categoryName = expense.getCategory().getName();
            Category category = categoryRepository.findByName(categoryName);
            if (category == null) {
                category = new Category();
                category.setName(categoryName);
                category = categoryRepository.save(category);
            }
            expense.setCategory(category);
        }
        return repository.save(expense);
    }

    public Expense saveExpenseFromRequest(ExpenseRequest request) {
        Category category = categoryRepository.findByName(request.getCategory());
        if (category == null) {
            category = new Category();
            category.setName(request.getCategory());
            category = categoryRepository.save(category);
        }

        Expense expense = new Expense();
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(category);

        return repository.save(expense);
    }

    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    public List<Expense> getExpensesByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return category != null ? repository.findByCategory(category) : List.of();
    }
}
