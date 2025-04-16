package com.expense.tracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseRequest {
    private String description;
    private BigDecimal amount;
    private String category;
    private LocalDate date;

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
