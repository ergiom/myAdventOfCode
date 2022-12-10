package aoc2022.day9;

public class Piece {
    private boolean hadHead;
    private boolean hadTail;

    Piece() {
        hadTail = hadHead = false;
    }

    public void setHead() {
        hadHead = true;
    }

    public void setTail() {
        hadTail = true;
    }

    public boolean hadTail() {
        return hadTail;
    }
}
