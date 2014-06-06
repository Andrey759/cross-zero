package View;

import Controller.Classes.Listener;
import javafx.scene.Scene;
import javax.swing.GroupLayout.Group;

public enum EForm {
    
    LoginForm("LoginForm.fxml", "Аутентификация"),
    MainForm("MainForm.fxml", "Игра крестики-нолики"),
    GameSettingsForm("GameSettingsForm.fxml", "Новая игра");
    
    private String fileName;
    private String title;
    private Scene scene;
    private Listener controller;
    
    private EForm(String fileName, String startTitle) {
        this.fileName = fileName;
        this.title = startTitle;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Scene getScene() {
        return scene;
    }
    
    public void setScene(Scene scene) {
       this.scene = scene;
    }
    
    public Listener getController() {
        return controller;
    }
    
    public void setController(Listener controller) {
        this.controller = controller;
    }

}
