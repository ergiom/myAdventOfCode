package aoc2022.day4;

public class PartOneLineReducer implements LineReducer {
    private int sum;

    PartOneLineReducer() {
        sum = 0;
    }

    @Override
    public void add(String line) {
        String[] ranges = line.split(",");
        InclusiveRange range1 = makeRange(ranges[0]);
        InclusiveRange range2 = makeRange(ranges[1]);

        if (range1.contains(range2)) sum++;
        else if (range2.contains(range1)) sum ++;
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
