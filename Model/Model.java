package Model;

import Controller.Core;
import Model.Game.*;
import Model.Game.ENum.EGameMode;
import Model.Game.GameMode.EmptyGame;
import Model.Game.GameMode.OfflineGame;
import Model.Game.GameMode.OnlineGame;
import Model.User.CurUser;



public class Model {
    
    private static Model instance = new Model();
    
    private CurUser firstUser = new CurUser();
    //private User secondUser = new User();
    private Game game = new EmptyGame();
    
    public static Model getIntance() {
        return instance;
    }
    
    private Model() { }
    
    public CurUser getFirstUser() {
        return firstUser;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void newGame(EGameMode mode, int gameSize, int winLength) {
        if(mode == EGameMode.online)
            game = new OnlineGame(gameSize, winLength);
        else if(mode == EGameMode.offline)
            game = new OfflineGame(gameSize, winLength);
        Core.getIntance().update();
    }
    
}
