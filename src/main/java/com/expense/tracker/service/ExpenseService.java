package com.expense.tracker.service;

import com.expense.tracker.DTO.ExpenseDto;
import com.expense.tracker.model.Expense;
import com.expense.tracker.repository.ExpenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public ExpenseDto saveExpense(ExpenseDto expenseDto){
        Expense expense = new Expense();
        BeanUtils.copyProperties(expenseDto,expense);

        if(Objects.nonNull(expenseDto.getCategoryId())){
            if(expenseRepository.getTotalAmountByCategory(expenseDto.getCategoryId())==null){
                throw new IllegalArgumentException("Category with given ID does not exist");
            }
        }
        Expense savedExpense = expenseRepository.save(expense);
        BeanUtils.copyProperties(savedExpense,expenseDto);
        /// To-Do : make an entity to dto converter using mapper function
        return expenseDto;
    }

    public ExpenseDto updateExpense(Long id,ExpenseDto expenseDto){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("Expense not found with the given Id"));

        BeanUtils.copyProperties(expenseDto,expense);

        if(Objects.nonNull(expenseDto.getCategoryId())){
            if(expenseRepository.getTotalAmountByCategory(expenseDto.getCategoryId()) == null){
                throw new IllegalArgumentException("Category with given Id does not exist");
            }
        }

        Expense updatedExpense = expenseRepository.save(expense);
        ///  To-do : make an entity to dto converter using mapper function
        return expenseDto;
    }

    public void deleteExpense(Long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Expense with this Id is not found"));

        expenseRepository.delete(expense);
    }

    public List<Expense> getExpenseByCategoryId(Long categoryId){
        /// To-do : use ExpenseDto instead of Expense and map the entity to dto while returning
        List<Expense> expenses = expenseRepository.findByCategoryId(categoryId);
        return expenses;
    }

    public List<Expense> getExpenseByDateRange(LocalDate startDate,LocalDate endDate){
        List<Expense> expenses = expenseRepository.findByDateRange(startDate,endDate);
        return expenses;
    }

    public BigDecimal getTotalAmountByCategoryId(Long categoryId){
        return expenseRepository.getTotalAmountByCategory(categoryId);
    }

    private ExpenseDto convertEntityToDTO(Expense expense){
        ExpenseDto expenseDto = new ExpenseDto();
        BeanUtils.copyProperties(expense,expenseDto);
        return expenseDto;
    }

}
