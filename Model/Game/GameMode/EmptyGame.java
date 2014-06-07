package Model.Game.GameMode;

import Controller.Core;
import Model.Game.ENum.EField;
import Model.Game.ENum.EPlayer;
import Model.Game.Game;

public class EmptyGame extends Game {

    public EmptyGame() {
        super(0,0);
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
}
