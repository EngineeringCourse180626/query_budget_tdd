package com.odde.budget;

import java.time.LocalDate;
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

        return duration.getOverlappingDays(new Duration(budget.getFirstDay(), budget.getLastDay()));
    }

}
