package com.expense.tracker.repository;

import com.expense.tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

//    @Query("select sum(e.amount) from expenses e join categories c on e.category_id = c.id where c.name = ?")
//    public BigDecimal totalAmountByCategory(String categoryName);

//    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.categoryId = (SELECT c.id FROM Category c WHERE c.name = :name)")
//    BigDecimal totalAmountByCategory(@Param("name") String categoryName);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category.name = :categoryName")
    BigDecimal totalAmountByCategory(@Param("categoryName") String categoryName);

}