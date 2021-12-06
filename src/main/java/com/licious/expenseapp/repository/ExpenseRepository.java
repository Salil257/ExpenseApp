package com.licious.expenseapp.repository;


import com.licious.expenseapp.model.Expense;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    List<Expense> findAll();
    @Query(
            value = "SELECT SUM(amount) from expense where MONTHNAME(date)= ?1",
            nativeQuery = true)
    Long getExpenseTotal(String month);
}
