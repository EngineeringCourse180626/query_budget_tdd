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

    private void assertTotalEquals(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetBot.query(start, end), 0.1);
    }

    private void givenBudgets(Budget... budgets) {
        when(stubBudgetRepo.findAll()).thenReturn(asList(budgets));
    }

}
