package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.GameModel;
import model.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by michael on 10/5/15.
 */
public class StoreController implements Initializable {

    @FXML
    public Button btnFood;

    @FXML
    public Button btnEnergy;

    @FXML
    public Button btnOre;

    @FXML
    public Button btnMule;

    @FXML
    public ToggleGroup grpBuySell;

    @FXML
    public Label lblMoney;

    @FXML
    public Button btnTown;

    @FXML
    public Label storeTimeLabel;

    @FXML
    public Label storeTurnLabel;

    private Player user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //called whenever this scene appears to the user
    public void onSceneActivated() {
        user = GameModel.GetInstance().currentPlayer;
        lblMoney.setText("You have $" + user.getMoney());
    }

    public void btnFood_Action(ActionEvent event) {
        if (isBuying()) {
            System.out.println("Buying Food");
        } else {
            System.out.println("Selling Food");
        }
    }

    public void btnEnergy_Action(ActionEvent event) {
        if (isBuying()) {
            System.out.println("Buying Energy");
        } else {
            System.out.println("Selling Energy");
        }
    }

    public void btnOre_Action(ActionEvent event) {
        if (isBuying()) {
            System.out.println("Buying Ore");
        } else {
            System.out.println("Selling Ore");
        }
    }

    public void btnMule_Action(ActionEvent event) {
        if (isBuying()) {
            System.out.println("Buying Mule");
        } else {
            System.out.println("Selling Mule");
        }
    }

    public void btnTown_Action(ActionEvent event) {
        MasterController.GetInstance().GoToTownScene();
    }

    /**
     * Helper function to tell the controller if the user is buying or selling items
     * @return true if the user is buying, false if selling
     */
    private boolean isBuying() {
        RadioButton selected = (RadioButton) grpBuySell.getSelectedToggle();
        return selected.getText().toUpperCase().equals("BUYING");
    }

}
