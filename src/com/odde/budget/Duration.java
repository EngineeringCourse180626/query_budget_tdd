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

    public double getOverlappingDays(Duration another) {
        if (isNoOverlapping(another))
            return 0;

        LocalDate effectiveStart = start.isAfter(another.start) ? start : another.start;
        LocalDate effectiveEnd = end.isBefore(another.end) ? end : another.end;

        if (effectiveStart.isAfter(effectiveEnd))
            return 0;

        return Period.between(effectiveStart, effectiveEnd).getDays() + 1;
    }

    private boolean isNoOverlapping(Duration anotherDuration) {
        return start.isAfter(anotherDuration.end) || end.isBefore(anotherDuration.start);
    }
}
