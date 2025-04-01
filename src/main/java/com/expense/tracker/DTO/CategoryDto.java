package com.expense.tracker.DTO;

import com.expense.tracker.model.Expense;

import java.util.List;

public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(){

    }

    public CategoryDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

}
