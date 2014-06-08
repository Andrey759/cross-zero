package View.ENum;

import Controller.Classes.ListenerHasSizeDisable;
import javafx.scene.Scene;

public enum EForm {
    
    LoginForm("LoginForm.fxml", "Аутентификация", false),
    MainForm("MainForm.fxml", "Игра крестики-нолики", true),
    GameSettingsForm("GameSettingsForm.fxml", "Новая игра", false);
    
    private String fileName;
    private String title;
    private boolean resizable;
    private Scene scene;
    private Double width;
    private Double height;
    private ListenerHasSizeDisable controller;
    
    private EForm(String fileName, String startTitle, boolean resizable) {
        this.fileName = fileName;
        this.title = startTitle;
        this.resizable = resizable;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getTitle() {
        return title;
    }

    public boolean isResizable() {
        return resizable;
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

    public ListenerHasSizeDisable getController() {
        return controller;
    }
    
    public void setController(ListenerHasSizeDisable controller) {
        this.controller = controller;
    }

}
