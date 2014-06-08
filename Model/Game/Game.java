package Model.Game;

import Controller.Classes.ExceptionLog;
import Controller.Core;
import Model.Game.ENum.EField;
import Model.Game.ENum.ELine;
import Model.Game.ENum.EPlayer;
import Model.Game.GameClasses.Point;
import Model.Game.GameClasses.WinLine;
import Model.Model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final int gameSize;
    private final int winLength;
    private EField[][] field;
    private EField firstPlayer;
    private EField secondPlayer;
    List<Point> emptyFields = new ArrayList<>();
//  private int[][] priority;
    ArrayList<WinLine> winLines;
    boolean gameIsEnded;
    EPlayer winner;

    public Game(int gameSize, int winLength) {
        int rand;
        this.gameSize = gameSize;
        this.winLength = winLength;
        if (gameSize >= 3 && winLength >= 3) {
            field = new EField[gameSize][gameSize];

            for (int i = 0; i < gameSize; i++)
                for (int j = 0; j < gameSize; j++) field[i][j] = EField.Empty;

            for (int x = 0; x < getGameSize(); x++)
                for (int y = 0; y < getGameSize(); y++)
                    if (getField(x, y) == EField.Empty) emptyFields.add(new Point(x, y));

            winLines = new ArrayList<>();
            gameIsEnded = false;
            winner = null;

            rand = (int) (Math.random() * 2);
            if (rand == 0) {
                firstPlayer = EField.Cross;
                secondPlayer = EField.Zero;
            } else {
                firstPlayer = EField.Zero;
                secondPlayer = EField.Cross;
                autoTurn(EPlayer.SecondPlayer);
            }
        }
    }

    public int getGameSize() {
        return gameSize;
    }

    public int getWinLength() {
        return winLength;
    }

    public boolean isGameIsEnded() {
        return gameIsEnded;
    }

    public EPlayer getWinner() {
        return winner;
    }

    public ArrayList<WinLine> getWinLines() {
        return winLines;
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
                if (getField(x, y) == EField.Empty) {
                    if (player == EPlayer.FirstPlayer)
                        field[x][y] = firstPlayer;
                    else
                        field[x][y] = secondPlayer;
                    for(int i = 0; i < emptyFields.size(); i++)
                        if(emptyFields.get(i).x == x && emptyFields.get(i).y == y) emptyFields.remove(i);
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
//        for(int i = 0; i < getGameSize(); i++)
//            for(int j = 0; j < getGameSize(); j++)
//                priority[i][j] = 0;
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
        for(int i = getGameSize() - 1; i > (getGameSize() - getWinLength() + 1); i--)
            tryLine(i, 0, ELine.DiagonalLine2);
        for(int i = 1; i < (getGameSize() - getWinLength() + 1); i++)
            tryLine(getGameSize() - 1, i, ELine.DiagonalLine2);
    }

    private void tryLine(int x, int y, ELine line) {
        int count = 1;
        EField type = getField(x, y);
        x += line.getDx();
        y += line.getDy();
        while(x < getGameSize() && y < getGameSize() && x >=0 && y >=0) {
            if(getField(x, y) != type) {
                check(new WinLine(x - count * line.getDx(), y - count * line.getDy(), x - line.getDx(), y - line.getDy()));
                count = 1;
                type = getField(x, y);
            } else
                count++;
            x += line.getDx();
            y += line.getDy();
        }
        check(new WinLine(x - count * line.getDx(), y - count * line.getDy(), x - line.getDx(), y - line.getDy()));
    }

    private void check(WinLine line) {
        int length;
        if(getField(line.getBeginX(), line.getBeginY()) != EField.Empty) {
            if (line.getBeginX() - line.getEndX() == 0)
                length = line.getEndY() - line.getBeginY();
            else
                length = line.getEndX() - line.getBeginX();
            if(length < 0) length *= -1;
            length++;
            if (length >= getWinLength())
                winLines.add(line);
        }
    }

    private void tryWin() {
        if(!winLines.isEmpty()) {
            if(getField(winLines.get(0).getBeginX(), winLines.get(0).getBeginY()) == firstPlayer) {
                winner = EPlayer.FirstPlayer;
                Model.getIntance().getFirstUser().addWin();
                Model.getIntance().getSecondUser().addLose();
            } else {
                winner = EPlayer.SecondPlayer;
                Model.getIntance().getFirstUser().addLose();
                Model.getIntance().getSecondUser().addWin();
            }
            gameIsEnded = true;
        } else if(emptyFields.isEmpty())
            gameIsEnded = true;
    }

    public void autoTurn(EPlayer player) {
        int index;
        index = (int) (Math.random() * emptyFields.size());
        move(emptyFields.get(index).x, emptyFields.get(index).y, player);
    }

}
