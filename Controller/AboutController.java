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
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable, ListenerHasSizeDisable {

    @FXML private AnchorPane mainPane;
    @FXML private Button buttonOk;

    @FXML
    public void action(ActionEvent event) {
        if(event.getSource().equals(buttonOk))
            Core.getIntance().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EForm.AboutForm.setController(this);
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
