package Controller;

import Controller.Classes.ExceptionLog;
import Controller.Classes.Listener;
import Model.Game.ENum.EField;
import Model.Game.ENum.EGameMode;
import Model.Game.ENum.EPlayer;
import Model.Model;
import View.EForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

//Контроллер первого представления, ленивый синглтон
public class MainController implements Initializable, Listener {

    @FXML private AnchorPane gamePane;
    @FXML private Label labelFirstPlayerWins;
    @FXML private Label labelFirstPlayerLoses;
    @FXML private Label labelSecondPlayerWins;
    @FXML private Label labelSecondPlayerLoses;
    @FXML private TitledPane firstPlayerNick;
    @FXML private TitledPane secondPlayerNick;
    @FXML private Label labelEndGameText;
    @FXML private MenuItem menuNewGame;
    @FXML private MenuItem menuSearch;
    @FXML private MenuItem menuChangeUser;
    @FXML private MenuItem menuClose;
    private Button field[][] = null;

    private void MainController() { }

    @FXML public void action(ActionEvent event) {

        if(event.getSource().equals(menuNewGame)) {
            Model.getIntance().newGame(EGameMode.offline, 3, 3);
        }

        else if(event.getSource().equals(menuSearch)) {
            //
        }

        else if(event.getSource().equals(menuChangeUser)) {
            Core.getIntance().changeLogin();
        }

        else if(event.getSource().equals(menuClose)) {
            Core.getIntance().close();
        }

        else
            for(int x = 0; x < field.length; x++)
                for(int y = 0; y < field.length; y++)
                    if(event.getSource().equals(field[x][y]))
                        Model.getIntance().getGame().move(x, y, EPlayer.FirstPlayer);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EForm.MainForm.setController(this);
    }
    
    @Override
    public void update() {
        firstPlayerNick.setText(Model.getIntance().getFirstUser().getNick());
        labelFirstPlayerWins.setText("Побед: " + Model.getIntance().getFirstUser().getWins());
        labelFirstPlayerLoses.setText("Поражений: " + Model.getIntance().getFirstUser().getLoses());
        secondPlayerNick.setText(Model.getIntance().getSecondUser().getNick());
        labelSecondPlayerWins.setText("Побед: " + Model.getIntance().getSecondUser().getWins());
        labelSecondPlayerLoses.setText("Поражений: " + Model.getIntance().getSecondUser().getLoses());
        if(field == null) {
            if(Model.getIntance().getGame().getGameSize() > 0)
                createFields();
        } else if(field.length != Model.getIntance().getGame().getGameSize()) {
            removeFields();
            if(Model.getIntance().getGame().getGameSize() > 0)
                createFields();
        } else
            updateFields();
    }

    private void removeFields() {
        if(field != null) {
            for (Button buttons[] : field)
                for (Button button : buttons)
                    gamePane.getChildren().remove(button);
            field = null;
        }
    }

    private void createFields() {
        if(Model.getIntance().getGame().getGameSize() > 0) field = new Button[Model.getIntance().getGame().getGameSize()][Model.getIntance().getGame().getGameSize()];
        for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++) {
            for(int y = 0; y < Model.getIntance().getGame().getGameSize(); y++) {
                field[x][y] = new Button();
                field[x][y].setLayoutX(21 + 24 * x);
                field[x][y].setLayoutY(14 + 25 * y);
                field[x][y].setPrefHeight(25);
                field[x][y].setPrefWidth(24);
                field[x][y].setMnemonicParsing(false);
                field[x][y].setId("field[" + x + "," + y + "]");
                field[x][y].setOnAction(this::action);
                gamePane.getChildren().add(field[x][y]);
            }
        }
        updateFields();
    }

    private void updateFields() {
        for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++) {
            for (int y = 0; y < Model.getIntance().getGame().getGameSize(); y++) {
                if(Model.getIntance().getGame().getField(x, y) == EField.Cross) field[x][y].setText("X");
                    else if(Model.getIntance().getGame().getField(x, y) == EField.Zero) field[x][y].setText("0");
                        else field[x][y].setText(" ");
            }
        }
        if(Model.getIntance().getGame().isGameIsEnded()) {
            for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++)
                for (int y = 0; y < Model.getIntance().getGame().getGameSize(); y++)
                    field[x][y].setDisable(true);
            if(Model.getIntance().getGame().getWinner() == EPlayer.FirstPlayer)
                labelEndGameText.setText("Вы победили!");
            else if(Model.getIntance().getGame().getWinner() == EPlayer.SecondPlayer)
                labelEndGameText.setText("Вы проиграли!");
            else
                labelEndGameText.setText("Ничья!");
        } else {
            labelEndGameText.setText("");
            for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++)
                for (int y = 0; y < Model.getIntance().getGame().getGameSize(); y++)
                    field[x][y].setDisable(false);
        }
    }

}
