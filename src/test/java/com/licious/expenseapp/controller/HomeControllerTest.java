package com.licious.expenseapp.controller;

import com.licious.expenseapp.dto.ExpenseDto;
import com.licious.expenseapp.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;


    @Test
    void contextLoads() throws Exception {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        ExpenseDto expense = new ExpenseDto();
        expense.setAmount(500L);
        expense.setExpenseName("Book");
        ExpenseDto expense1 = new ExpenseDto();
        expense.setAmount(500L);
        expense.setExpenseName("Note");
        expense.setDate(date);
        ExpenseDto expense2 = new ExpenseDto();
        expense.setAmount(500L);
        expense.setExpenseName("Lunch");
        expense.setDate(date);
        List<ExpenseDto> list = new ArrayList<>();
        list.add(expense);
        list.add(expense1);
        list.add(expense2);
        Mockito.when(expenseService.getExpenses()).thenReturn(list);
        String url = "/getExpenses";
        MvcResult mvcResult= mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
    }
}