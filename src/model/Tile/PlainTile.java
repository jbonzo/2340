package model.Tile;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * Created by PearlTerminal on 9/23/15.
 */
public class PlainTile implements Tile {
    @Override
    public int getOre() {
        return 1;
    }

    @Override
    public String getLandType() {
        return LandTile.PLAINS;
    }

    @Override
    public int getFood() {
        return 2;
    }

    @Override
    public int getEnergy() {
        return 3;
    }

    @Override
    public Background getColor() {
        return new Background(new BackgroundFill(Color.DARKOLIVEGREEN, null, null));
    }
}
