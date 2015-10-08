package model.Tile;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * Created by PearlTerminal on 9/23/15.
 */
public class TownTile implements Tile {
    @Override
    public int getOre() {
        return 0;
    }

    @Override
    public String getLandType() {
        return LandTile.TOWN;
    }

    @Override
    public int getFood() {
        return 0;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public Background getColor() {
        return new Background(new BackgroundFill(Color.GRAY, null, null));
    }
}
