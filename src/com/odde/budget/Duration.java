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

    public double getOverlappingDays(Duration anotherDuration) {
        if (isNoOverlapping(anotherDuration))
            return 0;

        LocalDate effectiveEnd = end.isBefore(anotherDuration.end) ? end : anotherDuration.end;
        LocalDate effectiveStart = start.isAfter(anotherDuration.start) ? start : anotherDuration.start;
        return Period.between(effectiveStart, effectiveEnd).getDays() + 1;
    }

    private boolean isNoOverlapping(Duration anotherDuration) {
        return start.isAfter(anotherDuration.end) || end.isBefore(anotherDuration.start);
    }
}
