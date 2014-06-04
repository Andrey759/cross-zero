package Controller;

import java.io.IOException;

import Controller.Classes.Listener;
import View.EForm;
import View.Forms;

//неленивый сингтон
public class Core {
    
    private static Core instance = new Core();
    private Listener currentController;
    
    public static Core getIntance() {
        return instance;
    }
    
    private Core() { }

    public Listener getCurrentController() {
        return currentController;
    }

    public void setCurrentController(Listener currentController) {
        this.currentController = currentController;
    }
    
    public void successfulLogin() {
        Forms.setScene(EForm.MainForm);
    }

    public void changeLogin() {
        Forms.setScene(EForm.LoginForm);
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
