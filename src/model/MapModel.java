package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Tile.*;
import control.MapController;
import control.MasterController;

/**
 * Created by michael on 9/24/15.
 */
public class MapModel {

    private Player[] players;
    private MapController controller;

    private int landSelectionPlayerIndex;
    private int landSelectionRound;
    private int numSelectionPasses;
    private int tilePrice;
    private boolean isLandSelectionPhase = false;


    public MapModel(MapController controller, Player[] players) {
        this.controller = controller;
        this.players = players;
    }

    public void beginLandSelectionPhase() {
        isLandSelectionPhase = true;
        landSelectionPlayerIndex = 0;
        landSelectionRound = 0;
        numSelectionPasses = 0;

        promptPlayerForLandSelection();

    }

    public void onLandTileClicked(LandTile tile) {
        //System.out.println(GameModel.GetInstance().timer.getCurrentTime().toMinutes());
        if (isLandSelectionPhase) {
            Player player = players[landSelectionPlayerIndex];
            if (tile.hasOwner()) {
                Alert al = new Alert(Alert.AlertType.WARNING, "You can't buy that tile!\n(Someone owns it)", ButtonType.OK);
                al.setTitle("Bad!");
                al.showAndWait();
            } else if (tile.getLandType().equals(LandTile.TOWN)) {
                Alert al = new Alert(Alert.AlertType.NONE, "You can't buy the town!", ButtonType.OK);
                al.setTitle("Bad!");
                al.showAndWait();
            } else {
                player.purchaseProperty(tile, tilePrice);
                updateLandSelectionPhase();
            }
        }
        else {
            if (tile.getLandType().equals(LandTile.TOWN)) {
                MasterController.GetInstance().GoToTownScene();
            }
        }
    }

    public Tile getRandomTile(int x, int y) {
        //middle col is always river/town
        if (x == 4) {
            if (y == 2) {
                return new TownTile(); // middle tile is always town
            } else {
                return new RiverTile();
            }
        } else {
            int rand = (int) (Math.random() * 100);
            if (rand < 50) { //50% chance of plains
                return new PlainTile();
            } else if (rand < 75) { //25% chance of Mountains 1
                return new Mountain1Tile();
            } else if (rand < 90) { //15% change of Mountains 2
                return new Mountain2Tile();
            } else { // 10% chance of mountain 3
                return new Mountain3Tile();
            }
        }
    }

    private void promptPlayerForLandSelection() {
        Player current = players[landSelectionPlayerIndex];



        Alert ad;
        if (landSelectionRound < 2) {
            tilePrice = 0;
            ad = new Alert(Alert.AlertType.NONE, current.getName() + ", it's your turn to select land!" +
                    "\nCost: FREE\nYou Have $" + current.getMoney(),
                    new ButtonType("Okay"));
        } else {
            tilePrice = 300;

            if (current.canAffordItem(tilePrice)) {
                ad = new Alert(Alert.AlertType.NONE, current.getName() + ", it's your turn to purchase land!" +
                        "\nCost: " + tilePrice + "\nYou Have $" + current.getMoney(),
                        new ButtonType("Okay"), new ButtonType("Pass"));
            } else {
                ad = new Alert(Alert.AlertType.NONE, current.getName() + ", it's your turn to purchase land, BUT YOU CAN'T!!!!!" +
                        "\nCost: " + tilePrice + "\nYou Have $" + current.getMoney(),
                        new ButtonType("Pass"));
            }
        }

        ad.setTitle("Buying Property");
        ad.showAndWait().ifPresent(response -> {
            if (response.getText().equals("Okay")) {

            }
            else if (response.getText().equals("Pass")) {
                numSelectionPasses++;
                updateLandSelectionPhase();
            }


        });
    }

    private void updateLandSelectionPhase() {
        landSelectionPlayerIndex++;
        if (landSelectionPlayerIndex >= players.length) { // end of selection round
            if (numSelectionPasses == players.length) {
                isLandSelectionPhase = false;
                System.out.println("no more buyin land!");
                MasterController.GetInstance().startRound();
                //begin turn phase
            } else {
                landSelectionPlayerIndex = 0;
                numSelectionPasses = 0;
                //System.out.println(landSelectionRound);
                landSelectionRound++;
            }
        }

        if (isLandSelectionPhase) {
            promptPlayerForLandSelection();
        }
    }

    public MapController getController() {
        return controller;
    }
}
