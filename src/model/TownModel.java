package model;

import control.MasterController;
import control.TownController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by ricky on 10/2/15.
 */

public class TownModel {

	private TownController controller;
	private Player[] players;

	public TownModel(TownController controller, Player[] players) {
		this.controller = controller;
		this.players = players;
	}

	public void onPubClicked() {
		Player player = GameModel.GetInstance().getCurrentPlayer();
		String result = player.goToPub();
		Alert al = new Alert(Alert.AlertType.NONE, result, ButtonType.OK);
		al.setTitle("You went to the pub!");
		al.showAndWait();
		System.out.println(player.getName() + " $" + player.getMoney());
		MasterController.GetInstance().endTurn();
		MasterController.GetInstance().GoToMapScene();
	}
}