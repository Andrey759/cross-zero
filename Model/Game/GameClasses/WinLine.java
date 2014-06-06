package Model.Game.GameClasses;

public class WinLine {

    private int beginX;
    private int beginY;
    private int endX;
    private int endY;

    public WinLine() { }

    public WinLine(int beginX, int beginY, int endX, int endY) {
        setBegin(beginX, beginY);
        setEnd(endX, endY);
    }

    public int getBeginX() {
        return beginX;
    }

    public int getBeginY() {
        return beginY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setBegin(int x, int y) {
        beginX = x;
        beginY = y;
    }

    public void setEnd(int x, int y) {
        endX = x;
        endY = y;
    }

}
