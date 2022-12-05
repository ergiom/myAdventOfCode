package aoc2022.day4;

public class PartTwoLineReducer implements LineReducer {
    private int sum;

    PartTwoLineReducer() {
        sum = 0;
    }

    @Override
    public void add(String line) {
        String[] ranges = line.split(",");
        InclusiveRange range1 = makeRange(ranges[0]);
        InclusiveRange range2 = makeRange(ranges[1]);

        if (range1.overlap(range2)) sum++;
    }

    private InclusiveRange makeRange(String str) {
        String[] startStop = str.split("-");
        int start = Integer.parseInt(startStop[0]);
        int stop = Integer.parseInt(startStop[1]);

        return new InclusiveRange(start, stop);
    }

    @Override
    public String getResult() {
        return String.valueOf(sum);
    }
}
