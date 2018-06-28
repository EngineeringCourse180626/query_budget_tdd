package com.odde.budget;

import java.time.LocalDate;

public class BudgetBot {
    private final BudgetRepo repo;

    public BudgetBot(BudgetRepo repo) {
        this.repo = repo;
    }

    public double query(LocalDate start, LocalDate end) {
        return repo.findAll().stream()
                .mapToDouble(budget -> budget.getOverlappingAmount(new Duration(start, end)))
                .sum();
    }

}
