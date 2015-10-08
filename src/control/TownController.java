package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.TownModel;

//import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by michael on 9/24/15.
 */
public class TownController implements Initializable {

	@FXML
	private TownModel model;

    @FXML
	private AnchorPane town;

    @FXML
    public Label townTimeLabel;

    @FXML
    public Label townTurnLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing the town scene");
    }

    public void init(TownModel model) {
    	this.model = model;
    }
    
    public void onMapButtonClicked(ActionEvent event) {
        MasterController.GetInstance().GoToMapScene();
    }

    public void onPubButtonClicked(ActionEvent event) {
    	model.onPubClicked();
    }

    public void onStoreButtonClicked(ActionEvent event) {
        MasterController.GetInstance().GoToStoreScene();
    }
}
