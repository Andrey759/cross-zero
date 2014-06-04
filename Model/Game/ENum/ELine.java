package Model.Game.ENum;

public enum ELine {

    HorizontalLine(1, 0),
    VerticalLine(0, 1),
    DiagonalLine(1, 1);

    private int dx;
    private int dy;

    private ELine(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

}
