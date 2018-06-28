package com.odde.budget;

import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetsTest {

    BudgetRepo stubBudgetRepo = mock(BudgetRepo.class);
    BudgetBot budgetBot = new BudgetBot(stubBudgetRepo);

    @Test
    public void no_budget() {
        givenBudgets();

        assertTotalEquals(0,
                of(2018, 3, 10),
                of(2018, 3, 10));
    }

    @Test
    public void duration_inside_budget_month() {
        givenBudgets(new Budget("201803", 31));

        assertTotalEquals(1,
                of(2018, 3, 10),
                of(2018, 3, 10));
    }

    @Test
    public void duration_no_overlap_before_budget_first_day() {
        givenBudgets(new Budget("201804", 30));

        assertTotalEquals(0,
                of(2018, 3, 10),
                of(2018, 3, 10));
    }

    @Test
    public void duration_no_overlap_after_budget_last_day() {
        givenBudgets(new Budget("201802", 28));

        assertTotalEquals(0,
                of(2018, 3, 10),
                of(2018, 3, 10));
    }

    @Test
    public void duration_overlap_budget_last_day() {
        givenBudgets(new Budget("201803", 31));

        assertTotalEquals(2,
                of(2018, 3, 30),
                of(2018, 4, 1));
    }

    @Test
    public void duration_overlap_budget_first_day() {
        givenBudgets(new Budget("201804", 30));

        assertTotalEquals(1,
                of(2018, 3, 30),
                of(2018, 4, 1));
    }

    @Test
    public void invalid_duration() {
        givenBudgets(new Budget("201803", 31));

        assertTotalEquals(0,
                of(2018, 3, 10),
                of(2018, 3, 2));
    }

    @Test
    public void daily_amount() {
        givenBudgets(new Budget("201804", 600));

        assertTotalEquals(60,
                of(2018, 4, 1),
                of(2018, 4, 3));
    }

    @Test
    public void multiple_budgets() {
        givenBudgets(new Budget("201802", 28), new Budget("201803", 310));

        assertTotalEquals(101,
                of(2018, 2, 28),
                of(2018, 3, 10));
    }

    private void assertTotalEquals(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetBot.query(start, end), 0.1);
    }

    private void givenBudgets(Budget... budgets) {
        when(stubBudgetRepo.findAll()).thenReturn(asList(budgets));
    }

}
