package View;

import java.io.IOException;

import Controller.Core;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//С синглтоном здесь происходила какая-то фигня
// - возникает ошибка при обращении к нестатическим переменным в методе start
public class Forms extends Application {

    private static Forms instance = new Forms();

    public static Forms getIntance() {
        return instance;
    }

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        for(EForm f : EForm.values())
            f.setScene(new Scene((javafx.scene.Parent) FXMLLoader.load(getClass().getResource("Elements/"+f.getFileName()))));
        Core.getIntance().setCurrentController(EForm.LoginForm.getController());
        stage.setResizable(false);
        stage.setTitle(EForm.LoginForm.getTitle());
        stage.setScene(EForm.LoginForm.getScene());
        stage.show();
//      Stage stage2 = new Stage();
//      stage2.setResizable(false);
//      stage2.setTitle(EForm.MainForm.getTitle());
//      stage2.setScene(EForm.MainForm.getScene());
//      stage2.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public String getTitle() {
        return stage.getTitle();
    }

    public static void setTitle(String title) {
        stage.setTitle(title);
    }

    public static void show() {
        stage.show();
    }

    public static void hide() {
        stage.hide();
    }

    public static Scene getScene() {
        return stage.getScene();
    }

    public static void setScene(EForm scene) {
        stage.hide();
        stage.setTitle(scene.getTitle());
        stage.setScene(scene.getScene());
        stage.centerOnScreen();

        Core.getIntance().setCurrentController(scene.getController());
        Core.getIntance().getCurrentController().update();

        stage.show();
    }

    public void updateScene() throws IOException {
        EForm current = null;
        for(EForm f : EForm.values())
            if(f.getScene().equals(getScene()))
                current = f;
        current.setScene(new Scene((javafx.scene.Parent) FXMLLoader.load(getClass().getResource("Elements/"+current.getFileName()))));
        stage.setScene(current.getScene());
    }

    public static void close() {
        stage.close();
    }

}
