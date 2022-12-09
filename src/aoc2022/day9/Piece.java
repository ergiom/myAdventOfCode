package aoc2022.day9;

public class Piece {
    private boolean head;
    private boolean tail;
    private boolean hadHead;
    private boolean hadTail;

    Piece() {
        head = tail = hadTail = hadHead = false;
    }

    public void setHead() {
        head = hadHead = true;
    }

    public void setTail() {
        tail = hadTail = true;
    }

    public boolean hadTail() {
        return hadTail;
    }

    public void removeHead() {
        head = false;
    }

    public void removeTail() {
        tail = false;
    }
}
