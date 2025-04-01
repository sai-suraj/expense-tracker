package com.expense.tracker.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDto {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    private Long categoryId;

    // Getter and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

   public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
   }

   public Long getCategoryId(){
        return this.categoryId;
   }

   //constructors
    public ExpenseDto(){

    }

    public ExpenseDto(Long id,BigDecimal amount,String description,LocalDate date,Long categoryId){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
    }

}
