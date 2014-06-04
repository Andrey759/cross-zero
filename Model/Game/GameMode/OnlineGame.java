package Model.Game.GameMode;

import Model.Game.Game;

public class OnlineGame extends Game {

    public OnlineGame(int gameSize, int winLength) {
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
        
    }
        
}