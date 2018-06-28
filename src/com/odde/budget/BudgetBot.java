package com.odde.budget;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class BudgetBot {
    private final BudgetRepo repo;

    public BudgetBot(BudgetRepo repo) {
        this.repo = repo;
    }

    public double query(LocalDate start, LocalDate end) {
        List<Budget> budgets = repo.findAll();

        if (budgets.isEmpty())
            return 0;

        Duration duration = new Duration(start, end);
        Budget budget = budgets.get(0);

        return getOverlappingDays(duration, budget);
    }

    private double getOverlappingDays(Duration duration, Budget budget) {
        if (isNoOverlapping(duration, budget))
            return 0;

        LocalDate effectiveEnd = duration.getEnd().isBefore(budget.getLastDay()) ? duration.getEnd() : budget.getLastDay();
        LocalDate effectiveStart = duration.getStart().isAfter(budget.getFirstDay()) ? duration.getStart() : budget.getFirstDay();
        return Period.between(effectiveStart, effectiveEnd).getDays() + 1;
    }

    private boolean isNoOverlapping(Duration duration, Budget budget) {
        return duration.getStart().isAfter(budget.getLastDay()) || duration.getEnd().isBefore(budget.getFirstDay());
    }

}
