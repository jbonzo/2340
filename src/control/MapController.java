package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.*;
import model.Tile.*;

import javafx.scene.control.*;
//import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ricky on 9/23/15.
 */
public class MapController implements Initializable {

    private MapModel model;

    @FXML
    private GridPane map;

    @FXML
    public Label mapTimeLabel;

    @FXML
    public Label turnLabel;

    private Button[][] buttons = new Button[9][5];
    private LandTile[][] tiles = new LandTile[9][5];
    private String mapType;
    private final Tile[][] STANDMAP = {
            {new PlainTile(),new PlainTile(),new Mountain3Tile(),new PlainTile(),new PlainTile()},
            {new PlainTile(),new Mountain1Tile(),new PlainTile(),new Mountain2Tile(),new PlainTile()},
            {new Mountain1Tile(), new PlainTile(),new PlainTile(),new PlainTile(),new Mountain2Tile()},
            {new PlainTile(),new PlainTile(),new PlainTile(),new PlainTile(),new PlainTile()},
            {new RiverTile(),new RiverTile(),new TownTile(),new RiverTile(),new RiverTile()} ,
            {new PlainTile(),new PlainTile(),new PlainTile(),new PlainTile(),new PlainTile()},
            {new Mountain3Tile(), new PlainTile(),new PlainTile(),new Mountain2Tile(),new PlainTile()},
            {new PlainTile(),new PlainTile(),new PlainTile(),new PlainTile(),new PlainTile()},
            {new PlainTile(),new Mountain3Tile(),new Mountain1Tile(),new PlainTile(),new Mountain2Tile()}};


    public void init(String mapType, MapModel model) {
        System.out.println("Map Type: " + mapType);

        this.model = model;
        this.mapType = mapType;
        for (Node pane : map.getChildren()) {
            String id = pane.getId();
            int gridCol = Integer.parseInt(id.substring(id.length() - 1));
            int gridRow = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));
            buttons[gridRow][gridCol] = (Button) pane;
        }
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                Tile tile;
                if (mapType.equals("Standard")) {
                    tile = STANDMAP[row][col];
                } else {
                    tile = model.getRandomTile(row, col);
                }
                tiles[row][col] = new LandTile(tile, buttons[row][col]);
            }
        }
    }



    @FXML
    public void onGridButtonAction(ActionEvent event) {
        Button buttonPresssed = (Button) event.getSource();
        String id = buttonPresssed.getId();
        int gridCol = Integer.parseInt(id.substring(id.length() - 1));
        int gridRow = Integer.parseInt(id.substring(id.length() - 2, id.length() - 1));

        model.onLandTileClicked(tiles[gridRow][gridCol]);
    }

    public MapModel getModel() {
        return model;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
