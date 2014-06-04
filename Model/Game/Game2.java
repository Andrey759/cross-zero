package Model.Game;

import Controller.Classes.ExceptionLog;
import Model.Game.ENum.EField;
import Model.Game.ENum.ELine;
import Model.Game.ENum.EPlayer;
import Model.Game.GameClasses.Point;
import Model.Game.GameClasses.WinLine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game2 {

    private final int gameSize;
    private final int winLength;
    private EField[][] field;
    private int[][] priority;
    private EField firstPlayer;
    private EField secondPlayer;
    ArrayList<WinLine> winLines;

    public Game2(int gameSize, int winLength) {
        this.gameSize = gameSize;
        this.winLength = winLength;
        field = new EField[gameSize][gameSize];
        for(int i = 0; i < gameSize; i++)
            for(int j = 0; j < gameSize; j++) field[i][j] = EField.Empty;
        firstPlayer = EField.Cross;
        secondPlayer = EField.Zero;
        winLines = new ArrayList<WinLine>();
    }

    public int getGameSize() {
        return gameSize;
    }

    public int getWinLength() {
        return winLength;
    }

    public EField getField(int x, int y) {
        try {
            if (!(x >= 0 && x < gameSize && y >= 0 && y < gameSize))
                throw new ExceptionLog("Ошибка: обращение к полю, находящемуся за пределами игровой сетки.");
        } catch (ExceptionLog e) { e.print(); }
        return field[x][y];
    }

    public void move(int x, int y, EPlayer player) {
        try {
            if (x >= 0 && x < gameSize && y >= 0 && y < gameSize) {
                if (field[x][y] == EField.Empty) {
                    if (player == EPlayer.FirstPlayer)
                        field[x][y] = firstPlayer;
                    else
                        field[x][y] = secondPlayer;
                    tryWinAndUpdate();
                } else
                    throw new ExceptionLog("Ошибка: попытка хода в занятое поле.");
            } else
                throw new ExceptionLog("Ошибка: попытка хода в поле, находящемся за пределами игровой сетки.");
        } catch (ExceptionLog e) {
            e.print();
        }
    }

    private void tryWinAndUpdate() {
        for(int i = 0; i < getGameSize(); i++)
            for(int j = 0; j < getGameSize(); j++)
                priority[i][j] = 0;
        tryAllLines();
        tryWin();
    }

    private void tryAllLines() {
        for(int i = 0; i < getGameSize(); i++)
            tryLine(0, i, ELine.HorizontalLine);
        for(int i = 0; i < getGameSize(); i++)
            tryLine(i, 0, ELine.VerticalLine);
        for(int i = 0; i < (getGameSize() - getWinLength() + 1); i++)
            tryLine(0, i, ELine.DiagonalLine);
        for(int i = 1; i < (getGameSize() - getWinLength() + 1); i++)
            tryLine(i, 0, ELine.DiagonalLine);
    }

    private void tryLine(int x, int y, ELine line) {
        int count = 1;
        EField type = field[x][y];
        x += line.getDx();
        y += line.getDy();
        while(x < getGameSize() && y < getGameSize() && x >=0 && y >=0) {
            if(field[x][y] != type) {
                check(new WinLine(x - count * line.getDx(), y - count * line.getDy(), x - line.getDx(), y - line.getDy()));
                count = 1;
                type = field[x][y];
            } else
                count++;
            x += line.getDx();
            y += line.getDy();
        }
        check(new WinLine(x - count * line.getDx(), y - count * line.getDy(), x - line.getDx(), y - line.getDy()));
    }

    private void check(WinLine line) {
        int length;
        if(line.getBeginX() - line.getEndX() == 0)
            length = line.getBeginY() - line.getEndY();
        else
            length = line.getBeginX() - line.getEndX();
        if(length >= getWinLength())
            winLines.add(line);
    }

    private void tryWin() {
        if(!winLines.isEmpty()) {
            if(getField(winLines.get(0).getBeginX(), winLines.get(0).getBeginY()) == firstPlayer)
                System.out.println("Вы победили!");
            else
                System.out.println("Вы проиграли!");
        }
    }

    private void autoTurn() {
        int index;
        List<Point> list = new ArrayList<Point>();
        for(int x = 0; x < getGameSize(); x++)
            for(int y = 0; y < getGameSize(); y++)
                list.add(new Point(x, y));
        index = (int) Math.random() * (list.size() - 1);
        move(list.get(index).x, list.get(index).y, EPlayer.SecondPlayer);
    }

}
