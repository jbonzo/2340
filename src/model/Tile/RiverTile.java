package model.Tile;


import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * Created by PearlTerminal on 9/23/15.
 */
public class RiverTile implements Tile {

    @Override
    public int getOre() {
        return 0;
    }

    @Override
    public String getLandType() {
        return LandTile.RIVER;
    }

    @Override
    public int getFood() {
        return 4;
    }

    @Override
    public int getEnergy() {
        return 2;
    }

    @Override
    public Background getColor() {
        return new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null));
    }
}
