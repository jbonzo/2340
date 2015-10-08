package control;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.GameModel;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class GameConfigController implements Initializable {

    @FXML
    private ChoiceBox cbDifficulty;

    @FXML
    private ChoiceBox cbMapType;

    @FXML
    private ToggleGroup grpNumPlayers;

    @FXML
    private Button btnContinue;

    @FXML
    private RadioButton rdobtn1Player;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {


        cbDifficulty.setValue("Beginner");
        cbMapType.setValue("Standard");
        rdobtn1Player.setSelected(true);

        btnContinue.setOnAction(event -> {
            String difficulty = (String)cbDifficulty.getValue();
            String mapType = (String)cbMapType.getValue();
            RadioButton selected = (RadioButton) grpNumPlayers.getSelectedToggle();
            int numPlayers = Integer.parseInt(selected.getText());
            GameModel.GetInstance().ConfigGame(difficulty, mapType, numPlayers);


        });
    }

}
