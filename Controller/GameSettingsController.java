package Controller;

import Controller.Classes.Listener;
import View.EForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSettingsController implements Initializable, Listener {

    @FXML private RadioButton raioButtonOnline;
    @FXML private RadioButton raioButtonOffline;
    @FXML private ChoiceBox choiceBoxGame;
    @FXML private Button buttonOk;
    @FXML private Button buttonCancel;

    @FXML
    public void action(ActionEvent event) {
        //
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EForm.GameSettingsForm.setController(this);
        //choiceBoxGame.add
    }

    @Override
    public void update() {
        //
    }
}
