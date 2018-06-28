package com.odde.budget;

import java.time.LocalDate;
import java.time.Period;

public class Duration {
    private final LocalDate start;
    private final LocalDate end;

    public Duration(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public int getDays() {
        return Period.between(start, end).getDays() + 1;
    }
}
