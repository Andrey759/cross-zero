package Controller;

import Controller.Classes.ListenerHasSizeDisable;
import Model.Game.ENum.EGame;
import Model.Game.ENum.EGameMode;
import Model.Model;
import View.ENum.EForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSettingsController implements Initializable, ListenerHasSizeDisable {

    @FXML private AnchorPane mainPane;
    @FXML private RadioButton radioButtonOnline;
    @FXML private RadioButton radioButtonOffline;
    @FXML private ChoiceBox choiceBoxGame;
    @FXML private Button buttonOk;
    @FXML private Button buttonCancel;

    @FXML
    public void action(ActionEvent event) {
        if(event.getSource().equals(buttonOk)) {
            Core.getIntance().close();
            Model.getIntance().newGame(EGameMode.offline, ((EGame) choiceBoxGame.getValue()).getGameSize(), ((EGame) choiceBoxGame.getValue()).getWinLength());
        }

        if(event.getSource().equals(buttonCancel))
            Core.getIntance().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxGame.getItems().addAll(EGame.values());
        choiceBoxGame.setValue(choiceBoxGame.getItems().get(0));
        EForm.GameSettingsForm.setController(this);
    }

    @Override
    public void update() {
        buttonOk.requestFocus();
    }

    @Override
    public double getWidth() {
        return mainPane.getPrefWidth();
    }

    @Override
    public double getHeight() {
        return mainPane.getPrefHeight();
    }

    @Override
    public void setDisable(boolean value) {
        mainPane.setDisable(value);
    }

    @Override
    public boolean isDisable() {
        return mainPane.isDisable();
    }
}
