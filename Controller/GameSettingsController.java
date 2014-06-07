package Controller;

import Controller.Classes.ListenerWithSize;
import View.EForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSettingsController implements Initializable, ListenerWithSize {

    @FXML private AnchorPane mainPane;
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

    @Override
    public double getWidth() {
        return mainPane.getPrefWidth();
    }

    @Override
    public double getHeight() {
        return mainPane.getPrefHeight();
    }
}
