package Controller;

import Controller.Classes.Hash;
import Controller.Classes.ListenerWithSize;
import Model.Model;
import View.EForm;
import java.net.URL;
import java.util.*;

import View.Forms;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

//Контроллер первого представления, ленивый синглтон
public class LoginController implements Initializable, ListenerWithSize {

    private static boolean firstLoad = true;

    @FXML private AnchorPane mainPane;
    @FXML private Label labelLogin;
    @FXML private Label labelPass;
    @FXML private Label labelError;
    @FXML private TextField editLogin;
    @FXML private PasswordField editPass;
    @FXML private Button buttonOk;
    @FXML private Button buttonCancel;

    private double sceneHeight = 0;
    private double buttonOkY;
    private double buttonCancelY;
    private final double height = 25;

    private void LoginController() { }

    @FXML public void action(ActionEvent event) {
        
        if(event.getSource().equals(buttonOk)) {
            String hpass = Hash.md5(editPass.getText());
            if(editLogin.getText().equals("Компьютер"))
                Model.getIntance().getFirstUser().empty();
            else
                Model.getIntance().getFirstUser().set(editLogin.getText(), hpass);
        }

        if(event.getSource().equals(buttonCancel)) {
            Core.getIntance().close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EForm.LoginForm.setController(this);
        buttonOkY = buttonOk.getLayoutY();
        buttonCancelY = buttonCancel.getLayoutY();
    }

    @Override
    public void update() {
        if(sceneHeight == 0) sceneHeight = EForm.LoginForm.getScene().getHeight();
        if(Model.getIntance().getFirstUser().getNick().length() > 0) {
            editLogin.requestFocus();
            editLogin.setText("");
            editPass.setText("");
            labelError.setText("");
            buttonOk.setLayoutY(buttonOkY);
            buttonCancel.setLayoutY(buttonCancelY);
        } else {
            editPass.requestFocus();
            editPass.selectAll();
            labelError.setText("Ошибка: неправильный пароль.");
            Forms.setHeight(EForm.LoginForm.getHeight() + height);
            buttonOk.setLayoutY(buttonOkY + height);
            buttonCancel.setLayoutY(buttonCancelY + height);
        }
    }

    @Override
    public double getWidth() {
        return mainPane.getPrefWidth();
    }

    @Override
    public double getHeight() {
        return mainPane.getPrefHeight();
    }

}
