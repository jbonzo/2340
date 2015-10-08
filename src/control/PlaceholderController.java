package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.GameModel;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mmogh on 9/17/2015.
 */
public class PlaceholderController implements Initializable {

    @FXML
    private TextArea txtNames;

    @FXML
    private TextArea txtRaces;

    @FXML
    private TextArea txtColors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void init() {
        String names = "Player Names: ";
        String races = "Player Races: ";
        String colors = "Player Colors: ";

        for (Player p : GameModel.GetInstance().getPlayers()) {
            names += p.getName() + ", ";
            races += p.getRace() + ", ";
            colors += p.getColor() + ", ";
        }

        txtNames.setText(names);
        txtRaces.setText(races);
        txtColors.setText(colors);
    }
}
