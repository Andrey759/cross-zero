package View;

import java.io.IOException;
import java.util.Stack;

import Controller.Classes.ExceptionLog;
import Controller.Classes.ListenerHasSizeDisable;
import Controller.Core;
import Model.Model;
import View.ENum.EForm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//С синглтоном здесь происходила какая-то фигня
// - возникает ошибка при обращении к нестатическим переменным в методе start
public class Forms extends Application {

//    private static Forms instance = new Forms();

//    public static Forms getIntance() {
//        return instance;
//    }

    private static Stage stage;
    private static Stack<Stage> stages = new Stack<>();
    private static Stack<ListenerHasSizeDisable> controllers = new Stack<>();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        for(EForm f : EForm.values()) { //Загружем все сцены, перечисленные в EForm
            f.setScene(new Scene(FXMLLoader.load(getClass().getResource("Elements/" + f.getFileName()))));
            f.setSize(f.getController().getWidth(), f.getController().getHeight());
        }
        stage.setTitle(EForm.LoginForm.getTitle());
        stage.setResizable(EForm.LoginForm.isResizable());
        stage.setScene(EForm.LoginForm.getScene()); //Ставим первую сцену LoginForm

        Core.getIntance().setCurrentController(EForm.LoginForm.getController());    //Указываем контроллер текущей сцены

        stage.setOnCloseRequest(value -> Forms.close());
        stage.show();
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

    public static void setWidth(Double width) {
        stage.setMinWidth(width + 16);
        stage.setWidth(width + 16);
    }

    public static void setHeight(Double height) {
        stage.setMinHeight(height + 38);
        stage.setHeight(height + 38);
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
        if(stages.isEmpty())
            Model.getIntance().empty(); //Если меняем сцену единственного окна, то обнуляем текущую игру
        stage.hide();
        stage.setTitle(scene.getTitle());
        stage.setResizable(scene.isResizable());
        stage.setScene(scene.getScene());
        setWidth(scene.getWidth());
        setHeight(scene.getHeight());
        stage.centerOnScreen();

        Core.getIntance().setCurrentController(scene.getController());
        Core.getIntance().getCurrentController().update();

        stage.show();
    }

    public static void newWindow(EForm scene) {
        Core.getIntance().getCurrentController().setDisable(true);

        stages.add(stage);  //Стек окон
        controllers.add(Core.getIntance().getCurrentController());
        stage = new Stage();
        stage.setResizable(scene.isResizable());
        stage.setTitle(scene.getTitle());
        stage.setScene(scene.getScene());

        Core.getIntance().setCurrentController(scene.getController());
        Core.getIntance().getCurrentController().update();

        stage.setOnCloseRequest(value -> Forms.close());
        stage.show();
    }

    public void updateScene() throws IOException {  //Не используется
        Model.getIntance().empty();
        EForm current = null;
        for(EForm f : EForm.values())
            if(f.getScene().equals(getScene()))
                current = f;
        current.setScene(new Scene(FXMLLoader.load(getClass().getResource("Elements/"+current.getFileName()))));
        stage.setScene(current.getScene());
    }

    public static void close() {    //Закрытие окна
        if(Core.getIntance().getCurrentController().equals(EForm.MainForm.getController()))
            Model.getIntance().empty();     //Если закрывается окно с игрой, то удаляем игру (иначе таймеры продолжат работать)
        stage.close();
        if(!stages.isEmpty()) {     //Если в стеке окон есть ещё окна, то извлекаем их из стека и делаем enable
            stage = stages.pop();
            if(!controllers.isEmpty())
                Core.getIntance().setCurrentController(controllers.pop());
            else
                ExceptionLog.println("Ошибка: Стэк controllers, работающий синхронно со стэком stages оказаля пустым, в то время, как stages - нет.");
            Core.getIntance().getCurrentController().setDisable(false);
            Core.getIntance().getCurrentController().update();
        }
    }
}
