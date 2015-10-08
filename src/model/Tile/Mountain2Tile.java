package model.Tile;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * Created by PearlTerminal on 9/23/15.
 */
public class Mountain2Tile implements Tile {
    @Override
    public int getOre() {
        return 3;
    }

    @Override
    public String getLandType() {
        return LandTile.MOUNTAIN2;
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
        return new Background(new BackgroundFill(Color.CRIMSON, null, null));
    }
}
