package com.licious.expenseapp.mapper;

import com.licious.expenseapp.dto.ExpenseDto;
import com.licious.expenseapp.model.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpenseMapper {
    @Autowired
    private ModelMapper mapper;


    public ExpenseDto entityToDto(Expense expense) {

        return mapper.map(expense,ExpenseDto.class);
    }

    public Expense dtoToEntity(ExpenseDto expenseDto) {
        return mapper.map(expenseDto,Expense.class);
    }

    public List<ExpenseDto> entityToDto(List<Expense> expenses) {
        return	expenses.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
