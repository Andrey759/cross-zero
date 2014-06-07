package View;

import Controller.Classes.Listener;
import Controller.Classes.ListenerWithSize;
import javafx.scene.Scene;
import javax.swing.GroupLayout.Group;

public enum EForm {
    
    LoginForm("LoginForm.fxml", "Аутентификация"),
    MainForm("MainForm.fxml", "Игра крестики-нолики"),
    GameSettingsForm("GameSettingsForm.fxml", "Новая игра");
    
    private String fileName;
    private String title;
    private Scene scene;
    private Double width;
    private Double height;
    private ListenerWithSize controller;
    
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

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }

    public void setSize(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    public ListenerWithSize getController() {
        return controller;
    }
    
    public void setController(ListenerWithSize controller) {
        this.controller = controller;
    }

}
