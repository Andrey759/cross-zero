package Model.Game.ENum;

public enum EGame {

    g3v3("3 на 3", 3, 3),
    g5v5("4 на 4", 5, 4),
    g7v7("5 на 5", 7, 5);

    private String text;
    private int gameSize;
    private int winLength;

    private EGame(String text, int gameSize, int winLength) {
        this.text = text;
        this.gameSize = gameSize;
        this.winLength = winLength;
    }

    public int getGameSize() {
        return gameSize;
    }

    public int getWinLength() {
        return winLength;
    }

}
