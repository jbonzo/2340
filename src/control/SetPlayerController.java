package control;
import java.net.URL;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.GameModel;

public class SetPlayerController implements Initializable{

    @FXML
    private Label playerNumber;

    @FXML //  fx:id="myButton"
    private TextField playerName;

    public ChoiceBox getPlayerColor() {
        return playerColor;
    }

    @FXML
    private ChoiceBox playerColor;

    @FXML
    private ChoiceBox playerRace;

    @FXML
    private Button createButton;

    @FXML
    private Label playerInfo;

    private int playerNum = 0;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
       // assert playerName != null : "fx:id=\"playerName\" was not injected: check your FXML file 'SetPlayerView.fxml'.";
       // assert playerColor != null : "fx:id=\"playerColor\" was not injected: check your FXML file 'SetPlayerView.fxml'.";
       // assert playerRace != null : "fx:id=\"playerRace\" was not injected: check your FXML file 'SetPlayerView.fxml'.";
       // assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'SetPlayerView.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
       // System.out.println("Hi");

        reset();

        createButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                //May not work, but should print something and save variables when button is pressed
                String name = playerName.getText();
                if (name.length() == 0) {
                    new Alert(Alert.AlertType.ERROR, "You must enter a name!", ButtonType.OK).show();
                } else {
                    String color = (String) playerColor.getValue();
                    String race = (String) playerRace.getValue();
                    playerInfo.setText("Player Saved!");
                    GameModel.GetInstance().setPlayer(name, color, race, playerNum);
                }
            }
        });

    }

    public void setPlayerNumber(int num) {
        playerNum = num;
        playerNumber.setText("" + (num + 1));
        reset();
    }

    private void reset() {
        playerName.setText("");
        playerColor.setValue("Red");
        playerRace.setValue("Packer");
    }
}
