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

        return Period.between(start, end).getDays() + 1;
    }
}
