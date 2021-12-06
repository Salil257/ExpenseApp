package com.licious.expenseapp.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ExpenseDto {
    private String expenseName;
    private Long amount;
    private Date date;
}
