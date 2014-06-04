package Model.Game;

import Controller.Core;

public class Game {
    
    private final int gameSize;
    private final int winLength;
    private int[][] field;

    public Game(int gameSize, int winLength) {
        this.gameSize = gameSize;
        this.winLength = winLength;
        field = new int[gameSize][gameSize];
        for(int i = 0; i < gameSize; i++)
            for(int j = 0; j < gameSize; j++) field[i][j] = 0;
    }
    
    public int getGameSize() {
        return gameSize;
    }
    
    public int getWinLength() {
        return winLength;
    }
    
    public int getField(int x, int y) {
        if(x >= 0 && x < gameSize && y >=0 && y < gameSize)
            return field[x][y];
        else
            return -1;
    }
    
    public void move(int x, int y, boolean player) {
        Line line;
        if(x < gameSize && y < gameSize) {
            if(field[x][y] == 0) {
                field[x][y] = 1;
                line = tryLines(x, y, false);
                if(line.getCount() >= winLength)
                    win(line, player);
            }
        }
        Core.getIntance().update();
    }
    
    private void countLine(int startX, int startY, int stepX, int stepY, Line line, boolean withFree) {
        int x = startX, y = startY;
        while(true) {
            x += stepX;
            y += stepY;
            if(x >= 0 && x < gameSize && y >=0 && y < gameSize)
                if(field[startX][startY] == field[x][y])
                    line.add(x, y);
                else {
                    if(withFree && field[startX][startY] == 0) line.addCount();
                    break;
                }
            else break;
        }
    }
    
    private void countAllLine(int startX, int startY, int stepX, int stepY, Line line, boolean withFree) {
        line.add(startX, startY);
        countLine(startX, startY, stepX, stepY, line, withFree);
        countLine(startX, startY, -stepX, -stepY, line, withFree);
    }
    
    public Line tryLines(int startX, int startY, boolean withFree) {
        Line line = new Line(winLength*2), maxLine = new Line(winLength*2);
        countAllLine(startX, startY, -1, 0, maxLine, withFree);
        for(int i = -1; i <= 1; i++) {
            line.free();
            countAllLine(startX, startY, i, -1, line, withFree);
            if(line.getCount() > maxLine.getCount()) {
                maxLine = line;           
            }
        }
        return maxLine;
    }
    
    public Line findFree() {
        boolean finded = false;
        Line coord = new Line(1);
        for(int x = 0; x < getGameSize(); x++) {
            if(finded) break;
            for(int y = 0; y < getGameSize(); y++) {
                if(finded) break;
                if(getField(x,y) == 0) {
                    coord.add(x, y);
                    finded = true;
                }
            }
        }
        if(!finded) {
            coord.add(-1, -1);
            draw();
        }
        return coord;
    }
    
    private void win(Line line, boolean player) {
        if(player)
            System.out.println("Вы выйграли!");
        else
            System.out.println("Вы проиграли!");
    }
    
    private void draw() {
        System.out.println("Ничья!");
    }
    
}