package Model.Game.GameMode;

import Model.Game.Game;
import Model.Game.Line;

public class OfflineGame extends Game {

    public OfflineGame(int gameSize, int winLength) {
        super(gameSize, winLength);
    }

    public int getGameSize() {
        return super.getGameSize();
    }
    
    public void move(int x, int y) {
        super.move(x, y, true);
        secondMove();
    }
    
    private void secondMove() {
        boolean moveDone = false;
        int countPlayerLine = 0, countComputerLine = 0;
        int newX, newY;
        Line line, maxLine = null;
        for(int x = 0; x < super.getGameSize(); x++) {
            if(moveDone) break;
            for(int y = 0; y < super.getGameSize(); y++) {
                if(moveDone) break;
                if(super.getField(x, y) == 1) {     //Ищем большие последовательности игрока
                    line = super.tryLines(x, y, true);
                    if(line.getCount() >= super.getWinLength()) {
                        newX = 2*line.getX(0) - line.getX(1);
                        newY = 2*line.getY(0) - line.getY(1);
                        if(newX >= 0 && newY >= 0)
                            if(super.getField(newX, newY) == 0) {
                                super.move(newX, newY, false);
                                moveDone = true;
                            }
                        if(!moveDone) {
                            newX = 2*line.getLastX(0) - line.getLastX(1);
                            newY = 2*line.getLastY(0) - line.getLastY(1);
                            if(newX >= 0 && newY >= 0)
                                if(super.getField(newX, newY) == 0) {
                                    super.move(newX, newY, false);
                                    moveDone = true;
                                }
                        }
                    }
                }
            }
        }
        for(int x = 0; x < super.getGameSize(); x++) {
            if(moveDone) break;
            for(int y = 0; y < super.getGameSize(); y++) {
                if(moveDone) break;
                if(super.getField(x, y) == 2) {     //Ищем наибольшую последовательность компьютера
                    line = super.tryLines(x, y, false);
                    if(line.getCount() >= (super.getWinLength()-1)) {
                        newX = 2*line.getX(0) - line.getX(1);
                        newY = 2*line.getY(0) - line.getY(1);
                        if(newX >= 0 && newY >= 0)
                            if(super.getField(newX, newY) == 0) {
                                super.move(newX, newY, false);
                                moveDone = true;
                            }
                        if(!moveDone) {
                            newX = 2*line.getLastX(0) - line.getLastX(1);
                            newY = 2*line.getLastY(0) - line.getLastY(1);
                            if(newX >= 0 && newY >= 0)
                                if(super.getField(newX, newY) == 0) {
                                    super.move(newX, newY, false);
                                    moveDone = true;
                                }
                        }
                    } else {
                        if(maxLine == null)
                            maxLine = line;
                        else if(line.getIndex() > maxLine.getIndex())
                            maxLine = line;
                    }
                }
            }
        }
        if(!moveDone) {
            if(maxLine == null) {
                line = super.findFree();
                if(line.getX(0) != -1) {
                    super.move(line.getX(0), line.getY(0), false);
                    moveDone = true;
                }
            } else {
                newX = 2*maxLine.getX(0) - maxLine.getX(1);
                newY = 2*maxLine.getY(0) - maxLine.getY(1);
                if(newX >= 0 && newY >= 0)
                    if(super.getField(newX, newY) == 0) {
                        super.move(newX, newY, false);
                        moveDone = true;
                    }
                if(!moveDone) {
                    newX = 2*maxLine.getLastX(0) - maxLine.getLastX(1);
                    newY = 2*maxLine.getLastY(0) - maxLine.getLastY(1);
                    if(newX >= 0 && newY >= 0)
                        if(super.getField(newX, newY) == 0) {
                            super.move(newX, newY, false);
                            moveDone = true;
                        }
                }
                if(!moveDone) {
                    line = super.findFree();
                    if(line.getX(0) != -1) {
                        super.move(line.getX(0), line.getY(0), false);
                        moveDone = true;
                    }
                }
            }
        }
    }
    
}