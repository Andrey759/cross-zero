package Model.Game.GameMode;

import Controller.Classes.TimerClass;
import Controller.Core;
import Model.Game.ENum.EField;
import Model.Game.ENum.EPlayer;
import Model.Game.Game;
import Model.Game.GameClasses.WinLine;

import java.util.ArrayList;

public class EmptyGame extends Game {

    private volatile TimerClass emptyTimer;


    public EmptyGame() {
        super(0,0);
        emptyTimer = new TimerClass(3, 0);
    }

    public int getGameSize() {
        return 0;
    }

    @Override
    public int getWinLength() {
        return 0;
    }

    @Override
    public boolean isGameIsEnded() {
        return false;
    }

    @Override
    public EPlayer getWinner() {
        return EPlayer.FirstPlayer;
    }

    @Override
    public EField getField(int x, int y) {
        return EField.Empty;
    }

    @Override
    public void move(int x, int y, EPlayer player) {
        //Nothing
    }

    @Override
    public void autoTurn(EPlayer player) {
        //Nothing
    }

    @Override
    public TimerClass getFirstPlayerTimer() {
        return emptyTimer;
    }

    @Override
    public TimerClass getSecondPlayerTimer() {
        return emptyTimer;
    }

    @Override
    public ArrayList<WinLine> getWinLines() {
        return null;
    }
}
