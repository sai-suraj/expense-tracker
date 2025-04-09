package com.expense.tracker.controller;

import com.expense.tracker.DTO.ExpenseDto;
import com.expense.tracker.model.Expense;
import com.expense.tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){
        ExpenseDto createdExpense = expenseService.saveExpense(expenseDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdExpense.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED)
                .location(location)
                .body(createdExpense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Expense>> getAllExpenseByCategory(@PathVariable Long categoryId){
        List<Expense> expenses = expenseService.getExpenseByCategoryId(categoryId);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Expense>> getExpensesByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        List<Expense> expenses = expenseService.getExpenseByDateRange(startDate,endDate);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("category/total/{categoryId}")
    public ResponseEntity<BigDecimal> getTotalExpenseByCategory(@PathVariable Long categoryId){
        BigDecimal totalExpense = expenseService.getTotalAmountByCategoryId(categoryId);
        return ResponseEntity.ok(totalExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id,@RequestBody ExpenseDto expense) {
        ExpenseDto updatedExpense = expenseService.updateExpense(id,expense);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

}
