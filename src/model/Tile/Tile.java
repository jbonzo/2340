package model.Tile;


import javafx.scene.layout.Background;

/**
 * Created by rbarillas3 on 9/23/15.
 */
public interface Tile {
    public int getOre();

    public String getLandType();

    public int getFood();

    public int getEnergy();

    public Background getColor();

}
