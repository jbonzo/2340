package model;

import control.*;
import javafx.animation.Timeline;

import java.util.PriorityQueue;

/**
 * Created by Will on 9/23/2015.
 */
public class GameModel {

    public static final int TURN_LENGTH = 30;

    private int playerTurn;
    private int round;
    public int numPlayers;
    private String difficulty;
    private String mapType;
    public Player[] players;
    public static Timeline timer;
    public PriorityQueue<Player> turnOrder;
    public Player currentPlayer;

    public static GameModel Instance = new GameModel();

    public static MasterController masterController = MasterController.GetInstance();
    public static GameConfigController ConfigController;
    public static SetPlayerController PlayerController;
    public static TownController TownController;
    public static control.PlaceholderController PlaceholderController;
    public static StoreController StoreController;

    public static MapController MapController;
    public static MapModel mapModel;
    public static TownModel townModel;

    //we should only have one GameModel object, and it should always start with round 1 and player 1
    private GameModel() {
        playerTurn = 1;
        round = 1;
    }

    public void init(GameConfigController gcc, SetPlayerController spc, PlaceholderController phc,
                     MapController mc, TownController tc, StoreController sc) {
        ConfigController = gcc;
        PlayerController = spc;
        PlaceholderController = phc;
        TownController = tc;
        MapController = mc;
        StoreController = sc;
    }

    public void setPlayer(String name, String color, String race, int playerNum) {
        players[playerNum] = new Player(name, color, race);

        PlayerController.getPlayerColor().getItems().remove(color);

        //more players left to set
        if (playerNum < numPlayers - 1) {
            PlayerController.setPlayerNumber(playerNum + 1);
            GameModel.masterController.GoToPlayerScene();
        }
        else {
            mapModel = new MapModel(MapController, players);
            townModel = new TownModel(TownController, players);
            MapController.init(mapType, mapModel);
            masterController.GoToMapScene();
            TownController.init(townModel);

            mapModel.beginLandSelectionPhase();
        }
    }

    public void ConfigGame(String difficulty, String map, int numPlayers) {
        this.difficulty = difficulty;
        mapType = map;
        this.numPlayers = numPlayers;

        players = new Player[numPlayers];

        System.out.println("this is a test");

        GameModel.PlayerController.setPlayerNumber(0);
        masterController.GoToPlayerScene();
        turnOrder = new PriorityQueue<Player>(numPlayers, (p1, p2) -> p1.getMoney() - p2.getMoney());
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }
    public static GameModel GetInstance() { return Instance; }

    public Player[] getPlayers(){ return players; }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


}
