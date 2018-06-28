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

        return getOverlappingDays(duration, new Duration(budget.getFirstDay(), budget.getLastDay()));
    }

    private double getOverlappingDays(Duration duration, Duration anotherDuration) {
        if (duration.getStart().isAfter(anotherDuration.getEnd()) || duration.getEnd().isBefore(anotherDuration.getStart()))
            return 0;

        LocalDate effectiveEnd = duration.getEnd().isBefore(anotherDuration.getEnd()) ? duration.getEnd() : anotherDuration.getEnd();
        LocalDate effectiveStart = duration.getStart().isAfter(anotherDuration.getStart()) ? duration.getStart() : anotherDuration.getStart();
        return Period.between(effectiveStart, effectiveEnd).getDays() + 1;
    }

}
