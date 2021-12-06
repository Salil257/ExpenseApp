package com.licious.expenseapp.service;

import com.licious.expenseapp.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService{
    Long addExpense(ExpenseDto expenseDto);

    List<ExpenseDto> getExpenses();

    boolean delete (long id);

    Long getExpenseTotal(String month);
}
