package Model.Game.GameMode;

import Controller.Classes.ExceptionLog;
import Controller.Core;
import Model.Game.ENum.EField;
import Model.Game.ENum.EPlayer;
import Model.Game.Game;

public class OfflineGame extends Game {

    public OfflineGame(int gameSize, int winLength) {
        super(gameSize, winLength);
    }

    @Override
    public void move(int x, int y, EPlayer player) {
        try {
            if (x >= 0 && x < super.getGameSize() && y >= 0 && y < super.getGameSize()) {
                if (getField(x, y) == EField.Empty) {
                    super.move(x, y, player);
                    if(player == EPlayer.FirstPlayer && !super.isGameIsEnded()) super.autoTurn(EPlayer.SecondPlayer);
                    Core.getIntance().update();
                } else
                    throw new ExceptionLog("Ошибка: попытка хода в занятое поле.");
            } else
                throw new ExceptionLog("Ошибка: попытка хода в поле, находящемся за пределами игровой сетки.");
        } catch (ExceptionLog e) {
            e.print();
        }
    }

}