package com.licious.expenseapp.integration;

import com.licious.expenseapp.controller.HomeController;
import com.licious.expenseapp.dto.ExpenseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
class IntegrationTest {
    @Autowired
    private HomeController homeController;

    @Test
    void testHomeController() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        ExpenseDto expense = new ExpenseDto();
        expense.setAmount(500L);
        expense.setExpenseName("Book");
        ;
        Assertions.assertThat(homeController.saveExpense(expense)).isEqualTo(1l);
    }
    void getExpenseTest() {
        Assertions.assertThat(homeController.getExpenses()).isNot(null);
    }
}
