package com.odde.budget;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Budget {

    private String month;
    private int amount;

    public Budget(String month, int amount) {
        this.month = month;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getFirstDay() {
        return YearMonth.parse(month, ofPattern("yyyyMM")).atDay(1);
    }

    public LocalDate getLastDay() {
        return YearMonth.parse(month, ofPattern("yyyyMM")).atEndOfMonth();
    }

    public int getDailyAmount() {
        return amount / getFirstDay().lengthOfMonth();
    }
}
