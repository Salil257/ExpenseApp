package com.licious.expenseapp.controller;

import com.licious.expenseapp.dto.ExpenseDto;
import com.licious.expenseapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class HomeController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/saveExpense")
    public Long saveExpense(@Valid @RequestBody ExpenseDto expenseDto ) {
       return expenseService.addExpense(expenseDto);
    }

    @GetMapping(value = "/getExpenses", consumes = MediaType.ALL_VALUE)
    public List<ExpenseDto> getExpenses() {
        return expenseService.getExpenses();
    }

    @GetMapping(value = "/getTotalAmount/{month}")
    public Long getTotalAmount(@PathVariable(value = "month") String month) {
        return expenseService.getExpenseTotal(month);
    }

    @DeleteMapping(value ="delete/{id}")
    public boolean delete(@PathVariable(value = "id") Long id){
        return expenseService.delete(id);
    }

}
