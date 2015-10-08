package model.Tile;

import javafx.scene.control.*;
import model.Player;


/**
 * Created by rbarillas3 on 9/23/15.
 */
public class LandTile {
    private Tile landType;
    private Player owner;
    private Button tile;
    public static final String PLAINS = "PlainTile"; //green
    public static final String RIVER = "RiverTile"; //blue
    public static final String MOUNTAIN1 = "1 Mountain"; //brown
    public static final String MOUNTAIN2 = "2 Mountains"; //red
    public static final String MOUNTAIN3 = "3 Mountains"; //purple
    public static final String TOWN = "TownTile"; //gray


    public LandTile(Tile landType, Button tile) {
        this.landType = landType;
        this.owner = null;
        this.tile = tile;
        tile.setBackground(landType.getColor());
    }

    public int getOre() {
        return landType.getOre();
    }

    public Button getButton() {
        return tile;
    }

    public String getLandType() {
        return landType.getLandType();
    }

    public int getFood() {
        return landType.getFood();
    }

    public int getEnergy() {
        return landType.getEnergy();
    }

    public Player getOwner() {
        return owner;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        tile.setStyle("-fx-border-color: " + owner.getColor().toLowerCase() + "; -fx-border-width: 10");
    }
}
