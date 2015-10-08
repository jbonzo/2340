package model.Tile;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * Created by PearlTerminal on 9/23/15.
 */
public class Mountain3Tile implements Tile {
    @Override
    public int getOre() {
        return 4;
    }

    @Override
    public String getLandType() {
        return LandTile.MOUNTAIN3;
    }

    @Override
    public int getFood() {
        return 1;
    }

    @Override
    public int getEnergy() {
        return 1;
    }

    @Override
    public Background getColor() {
        return new Background(new BackgroundFill(Color.PURPLE, null, null));
    }
}
