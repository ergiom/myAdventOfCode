package aoc2022.day4;

public class InclusiveRange {
    private final int start;
    private final int stop;

    InclusiveRange(int start, int stop) {
        if (start > stop) throw new RuntimeException();

        this.start = start;
        this.stop = stop;
    }

    public boolean overlap(InclusiveRange range) {
        return startInRange(range) || stopInRange(range) || range.startInRange(this);
    }

    private boolean startInRange(InclusiveRange range) {
        return this.start <= range.start && this.stop >= range.start;
    }

    private boolean stopInRange(InclusiveRange range) {
        return this.start <= range.stop && this.stop >= range.stop;
    }

    public boolean contains(InclusiveRange range) {
        return startInRange(range) && stopInRange(range);
    }
}
