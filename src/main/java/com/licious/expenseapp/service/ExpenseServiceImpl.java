package com.licious.expenseapp.service;

import com.licious.expenseapp.advice.exception.BusinessException;
import com.licious.expenseapp.dto.ExpenseDto;
import com.licious.expenseapp.mapper.ExpenseMapper;
import com.licious.expenseapp.model.Expense;
import com.licious.expenseapp.repository.ExpenseRepository;
import org.hibernate.QueryTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseMapper expenseMapper;

    Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Override
    public Long addExpense(ExpenseDto expenseDto) {

        try{
            Expense expense = expenseMapper.dtoToEntity(expenseDto);
         return expenseRepository.save(expense).getId();
        }catch (Exception e){
            BusinessException businessException = new BusinessException();
            businessException.setErrorMessage(e.getMessage());
            throw businessException;
        }
    }

    @Override
    public List<ExpenseDto> getExpenses() {
        try{
            return expenseMapper.entityToDto(expenseRepository.findAll());
        }catch (QueryTimeoutException e){
            BusinessException businessException = new BusinessException();
            businessException.setErrorMessage(e.getMessage()+" please Retry ... Query has timedOut");
            throw businessException;
        }
    }

    @Override
    public boolean delete(long id) {
       try {
           expenseRepository.deleteById(id);
           return true;
       }catch (EmptyResultDataAccessException e){
           logger.warn(e.getMessage());
           return false;
       }
    }

    @Override
    public Long getExpenseTotal(String month) {
        try{
            if(month.length()==1){
                BusinessException businessException = new BusinessException();
                businessException.setErrorMessage("enter the months in string format, e.g. september");
                throw businessException;
            }

       return expenseRepository.getExpenseTotal(month);}
        catch (NullPointerException e){
            BusinessException businessException = new BusinessException();
            businessException.setErrorMessage(e.getMessage()+" No Record of Expenditure found for this month");
            throw businessException;
        }
        catch (QueryTimeoutException e){
            BusinessException businessException = new BusinessException();
            businessException.setErrorMessage(e.getMessage()+" please Retry ... Query has timedOut");
            throw businessException;
        }

    }
}
