package Controller;

import Controller.Classes.ListenerHasSizeDisable;
import View.ENum.EForm;
import View.Forms;

//неленивый сингтон
public class Core {
    
    private static Core instance = new Core();
    private ListenerHasSizeDisable currentController;
    
    public static Core getIntance() {
        return instance;
    }
    
    private Core() { }

    public ListenerHasSizeDisable getCurrentController() {
        return currentController;
    }

    public void setCurrentController(ListenerHasSizeDisable currentController) {
        this.currentController = currentController;
    }
    
    public void successfulLogin() {
        Forms.setScene(EForm.MainForm);
    }

    public void changeLogin() {
        Forms.setScene(EForm.LoginForm);
    }

    public void openGameSettings() {
        Forms.newWindow(EForm.GameSettingsForm);
    }
    
    public void update() {
        this.getCurrentController().update();
    }
    
    public void close() {
        Forms.close();
    }
    
    public void setLoginTitle(String title) {
        Forms.setTitle(title);
    }
    
}
