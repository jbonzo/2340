package model;

import model.Tile.LandTile;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by mmogh on 9/23/2015.
 * Edited by Will, Ricky
 */
public class Player {

    private String name;
    private String color;
    private String race;
    private HashMap<String, Integer> storehouse;

    private int money;

    private ArrayList<LandTile> property;

    /**
     * Player constructor
     * @param name Player's name
     * @param color Player's color
     * @param race Player's race
     */
    public Player(String name, String color, String race) {
        this.name = name;
        this.color = color;
        this.race = race;

        this.storehouse = new HashMap<String, Integer>();
        this.storehouse.put("Food", 0);
        this.storehouse.put("Energy", 0);
        this.storehouse.put("Ore", 0);

        property = new ArrayList<>();

        switch (race.toUpperCase()) {
            case "FLAPPER":
                money = 1600;
                break;
            case "HUMANOID":
                money = 600;
                break;
            default:
                money = 1000;
        }
    }

    /**
     * Player enters pub and gets money
     * @return String - the pub outcome
     */
    public String goToPub() {
        //Different pub outcomes:
        //Gain average money (majority chance) 0-499 (50%)
        //Lose small amount of money (moderate chance) 500-749 (25%)
        //Lose hefty amount of money (moderate-slim chance) 750 - 949 (20%)
        //A wild pokemon appeared! [less time on next turn] (twice as slim) 950 - 969(2%)
        //Blackout and lost a turn (twice as slim) 970 - 989(2%)
        //Gain a lot of money (slim chance) 990 - 999 (1%) 
        Random rand = new Random();
        int num = rand.nextInt(1000);
        int avgMoney = rand.nextInt(300) + 1;
        int smallLoss = rand.nextInt(150) + 1;
        int heftyLoss = rand.nextInt(200) + 300;
        int lotteryMoney = 5000;

        if (num <= 499) {
            addMoney(avgMoney);
            return "You got $" + avgMoney + " at the pub last night";

        } else if (num <= 749) {
            deductMoney(smallLoss);
            return "You lost $" + smallLoss + " at the pub last night";

        } else if (num <= 949) {
            deductMoney(heftyLoss);
            return "You lost $" + heftyLoss + " at the pub last night";

        } else if (num <= 969) {
            return "A wild Pokemon appeared!";

        } else if (num <= 989) {
            return "You forgot last night and you lose a turn!";

        } else if (num <= 999) {
            addMoney(lotteryMoney);
            return "You got $" + lotteryMoney + " at the pub last night";

        } else {
            System.out.println("Something went wrong in goToPub()");
        }
        return "-1000";
        
    }

    /**
     * Player purchases a land tile
     * @param tile A tile
     * @param price The price of the land tile
     * @return boolean - true if the land was bought, false if not
     */
    public boolean purchaseProperty(LandTile tile, int price) {
        if (canAffordItem(price) && !tile.hasOwner()) {
            deductMoney(price);
            tile.setOwner(this);
            property.add(tile);

            return true;
        } else {
            return false;
        }
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void addMoney(int toAdd) { money += toAdd; }

    public void deductMoney(int toDeduct) {money -= toDeduct; }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getRace() {
        return race;
    }

    public int getMoney() { return money; }

    public ArrayList<LandTile> getProperty() {
        return property;
    }

    /**
     * Checks if the Player can afford the land tile or resource
     * @param itemPrice Price of the item being bought
     * @return boolean - true if the Player can afford the item, false if not
     */
    public boolean canAffordItem(int itemPrice) { return money >= itemPrice; }

    /**
     * Checks to see if the Player can sell that number of resources
     * @param resource The resource being sold
     * @param numToSell The number of items being sold
     * @return boolean - true if the Player can sell that many resources, false if otherwise
     */
    public boolean canSellResources(String resource, int numToSell) {
        return numToSell <= storehouse.get(resource);
    }

    /**
     * Adds 1 food to the Player's storehouse
     */
    public void addFood() {
        int newValue = storehouse.get("Food") + 1;
        storehouse.put("Food", newValue);
    }

    /**
     * Adds X food to the Player's storehouse
     * @param valueToAdd number of resources to add
     */
    public void addFood(int valueToAdd) {
        int newValue = storehouse.get("Food") + valueToAdd;
        storehouse.put("Food", newValue);
    }

    /**
     * Adds 1 energy to the Player's storehouse
     */
    public void addEnergy() {
        int newValue = storehouse.get("Energy") + 1;
        storehouse.put("Energy", newValue);
    }

    /**
     * Adds X energy to the Player's storehouse
     * @param valueToAdd number of resources to add
     */
    public void addEnergy(int valueToAdd) {
        int newValue = storehouse.get("Energy") + valueToAdd;
        storehouse.put("Energy", newValue);
    }

    /**
     * Adds 1 ore to the Player's storehouse
     */
    public void addOre() {
        int newValue = storehouse.get("Ore") + 1;
        storehouse.put("Ore", newValue);
    }


    /**
     * Adds X ore to the Player's storehouse
     * @param valueToAdd number of resources to add
     */
    public void addOre(int valueToAdd) {
        int newValue = storehouse.get("Ore") + valueToAdd;
        storehouse.put("Ore", newValue);
    }
}
