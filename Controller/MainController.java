package Controller;

import Controller.Classes.ListenerHasSizeDisable;
import Model.Game.ENum.EField;
import Model.Game.ENum.EPlayer;
import Model.Game.GameClasses.WinLine;
import Model.Model;
import View.ENum.EForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//Контроллер первого представления, ленивый синглтон
public class MainController implements Initializable, ListenerHasSizeDisable {

    @FXML private AnchorPane mainPane;
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
    private List<Line> lines = new ArrayList<>();

    @FXML public void action(ActionEvent event) {
        if(event.getSource().equals(menuNewGame))
            Core.getIntance().openGameSettings();

        else if(event.getSource().equals(menuSearch)) {
            //
        }

        else if(event.getSource().equals(menuChangeUser)) {
            Model.getIntance().empty();
            Core.getIntance().changeLogin();
        }

        else if(event.getSource().equals(menuClose))
            Core.getIntance().close();

        else
            for(int x = 0; x < field.length; x++)
                for(int y = 0; y < field.length; y++)
                    if(event.getSource().equals(field[x][y]))
                        if (Model.getIntance().getGame().getField(x, y).equals(EField.Empty))
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
            else
                updateFields();
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
        gamePane.setPrefWidth(34 * 2 + 24 * Model.getIntance().getGame().getGameSize());
        gamePane.setPrefHeight(25 * 2 + 20 + 25 * Model.getIntance().getGame().getGameSize());

        if(Model.getIntance().getGame().getGameSize() > 0)
            field = new Button[Model.getIntance().getGame().getGameSize()][Model.getIntance().getGame().getGameSize()];

        for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++) {
            for(int y = 0; y < Model.getIntance().getGame().getGameSize(); y++) {
                field[x][y] = new Button();
                field[x][y].setLayoutX(34 + 24 * x);
                field[x][y].setLayoutY(25 + 25 * y);
                field[x][y].setPrefWidth(24);
                field[x][y].setPrefHeight(25);
                field[x][y].setMnemonicParsing(false);
                field[x][y].setId("field[" + x + "," + y + "]");
                field[x][y].setOnAction(this::action);
                gamePane.getChildren().add(field[x][y]);
            }
        }
        updateFields();
    }

    private void printLines(List<WinLine> winLines) {
        for (WinLine winLine : winLines) {
            Line line = new Line();
            int dx = 0, dy = 0;
            Button beginField = field[winLine.getBeginX()][winLine.getBeginY()];
            Button endField = field[winLine.getEndX()][winLine.getEndY()];
            if (winLine.getEndX() > winLine.getBeginX())
                dx = 10;
            else if (winLine.getEndX() < winLine.getBeginX())
                dx = -10;
            if (winLine.getEndY() > winLine.getBeginY())
                dy = 10;
            else if (winLine.getEndY() < winLine.getBeginY())
                dy = -10;
            line.setLayoutX(beginField.getLayoutX() + 12 - dx);
            line.setLayoutY(beginField.getLayoutY() + 12 - dy);
            line.setStartX(0);
            line.setStartY(0);
            line.setEndX(endField.getLayoutX() - beginField.getLayoutX() + dx * 2);
            line.setEndY(endField.getLayoutY() - beginField.getLayoutY() + dy * 2);
            line.styleProperty().setValue("-fx-stroke: Gray;");
            lines.add(line);
            gamePane.getChildren().add(line);
        }
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
            if(lines.isEmpty())
                printLines(Model.getIntance().getGame().getWinLines());
        } else {
            if(!lines.isEmpty()) {
                for (Line line : lines)
                    gamePane.getChildren().remove(line);
                lines.removeAll(lines);
            }
            labelEndGameText.setText("");
            for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++)
                for (int y = 0; y < Model.getIntance().getGame().getGameSize(); y++)
                    field[x][y].setDisable(false);
        }
    }

    @Override
    public double getWidth() {
        return mainPane.getPrefWidth() - 9;
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
