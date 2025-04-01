package com.expense.tracker.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses",
        indexes = @Index(name = "idx_expense_date",columnList = "expense_date"),
        uniqueConstraints = @UniqueConstraint(columnNames = "description")
)
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

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinColumn(
            name = "category_id",
            nullable = true,
            foreignKey = @ForeignKey(name = "fk_expense_category")
    )
    private Category category;

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
    public Category getCategory(){
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    //Constructors
    public Expense(){}

    public Expense(BigDecimal amount,String description,LocalDate date, Category category){
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.category = category;
    }

}
