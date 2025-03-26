package com.expense.tracker.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "expense_date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "category")
    private String category;

    //Getters
    public long getId() {
        return id;
    }
    public String getDescription(){
        return description;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getCategory(){
        return category;
    }
    public BigDecimal getAmount(){
        return amount;
    }

    //Setters
    public void setId(long id){
        this.id = id;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    //Constructors
    public Expense(){}

    public Expense(BigDecimal amount,String description,LocalDate date, String category){
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

}
