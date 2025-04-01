package com.expense.tracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories",uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    List<Expense> expenses = new ArrayList<>();




    //Getters and Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    //Constructors
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }


    public void addExpense(Expense expense){
        this.expenses.add(expense);
        expense.setCategory(this);
    }

    public void removeExpense(Expense expense){
        this.expenses.remove(expense);
        expense.setCategory(null);
    }
}
