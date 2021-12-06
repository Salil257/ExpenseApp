package com.licious.expenseapp.service;

import com.licious.expenseapp.model.Expense;
import com.licious.expenseapp.repository.ExpenseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@DataJpaTest
class ExpenseServiceTest {


    @Autowired
    private ExpenseRepository expenseRepository;


    @Test
    void saveExpense() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        Expense expense = new Expense();
        expense.setAmount(500L);
        expense.setExpenseName("Book");
        expense.setDate(date);
        Expense expense1 = new Expense();
        expense.setAmount(500L);
        expense.setExpenseName("Note");
        expense.setDate(date);
        Expense expense2 = new Expense();
        expense.setAmount(500L);
        expense.setExpenseName("Lunch");
        expense.setDate(date);
        expenseRepository.save(expense);
        expenseRepository.save(expense1);
        expenseRepository.save(expense2);
        Assertions.assertNotNull(expenseRepository.findAll());

    }


}







