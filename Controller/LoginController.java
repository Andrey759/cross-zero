package Controller;

import Controller.Classes.Hash;
import Controller.Classes.Listener;
import Model.Model;
import View.Forms;
import java.net.URL;
import java.util.*;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

//Контроллер первого представления, ленивый синглтон
public class LoginController implements Initializable, Listener {

    private static boolean firstLoad = true;

    @FXML private AnchorPane anchorPane1;
    @FXML private Label labelLogin;
    @FXML private Label labelPass;
    @FXML private TextField editLogin;
    @FXML private PasswordField editPass;
    @FXML private Button buttonOk;
    @FXML private Button buttonCancel;

    private void LoginController() { }

    @FXML public void action(ActionEvent event) {
        
        if(event.getSource().equals(buttonOk)) {
            String hpass = Hash.md5(editPass.getText());
            Model.getIntance().getFirstUser().set(editLogin.getText(), hpass, editPass.getText().length());
        }

        if(event.getSource().equals(buttonCancel)) {
            Core.getIntance().close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void update() {
        Core.getIntance().setCurrentController(this);
    }

    public void setFirstLoad() {
        firstLoad = true;
    }

}
