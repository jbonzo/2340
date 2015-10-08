package control;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameModel;
import javafx.concurrent.Task;
import model.Player;

import java.io.IOException;

/**
 * Created by mmogh on 9/23/2015.
 */
public class MasterController {

    private static MasterController Instance = new MasterController();

    private static Scene ConfigScene;
    private static Scene PlayerScene;
    private static Scene PlaceholderScene;
    private static Scene MapScene;
    private static Scene TownScene;
    private static Scene StoreScene;

    private static Stage PrimaryStage;

    private MasterController() {

    }

    public void init(Stage stage) throws IOException {
        this.PrimaryStage = stage;

        FXMLLoader configLoader = new FXMLLoader(getClass().getResource("../view/GameConfigView.fxml"));
        FXMLLoader playerLoader = new FXMLLoader(getClass().getResource("../view/SetPlayerView.fxml"));
        FXMLLoader placeholderLoader = new FXMLLoader(getClass().getResource("../view/PlaceholderView.fxml"));
        FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("../view/MapView.fxml"));
        FXMLLoader townLoader = new FXMLLoader(getClass().getResource("../view/TownView.fxml"));
        FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("../view/StoreView.fxml"));

        Pane configPane = configLoader.load();
        Pane playerPane = playerLoader.load();
        Pane placeholderPane = placeholderLoader.load();
        Pane mapPane = mapLoader.load();
        Pane townPane = townLoader.load();
        Pane storePane = storeLoader.load();

        GameConfigController ConfigController = configLoader.<GameConfigController>getController();
        SetPlayerController PlayerController = playerLoader.<SetPlayerController>getController();
        PlaceholderController PlaceholderController = placeholderLoader.<PlaceholderController>getController();
        MapController MapController = mapLoader.<MapController>getController();
        TownController TownController = townLoader.<TownController>getController();
        StoreController storeController = storeLoader.<StoreController>getController();

        ConfigScene = new Scene(configPane, 667, 600);
        PlayerScene = new Scene(playerPane, 667, 600);
        PlaceholderScene = new Scene(placeholderPane, 667, 600);
        MapScene = new Scene(mapPane, 667, 600);
        TownScene = new Scene(townPane, 667, 600);
        StoreScene = new Scene(storePane, 667, 600);

        GoToConfigScreen();
        PrimaryStage.setResizable(false);
        PrimaryStage.show();


        GameModel.GetInstance().init(ConfigController, PlayerController, PlaceholderController,
                MapController, TownController, storeController);
    }

    public void GoToPlayerScene() {
        PrimaryStage.setTitle("M.U.L.E. Player Configuration");
        PrimaryStage.setScene(PlayerScene);
    }

    public void GoToConfigScreen() {
        PrimaryStage.setTitle("M.U.L.E. Game Configuration");
        PrimaryStage.setScene(ConfigScene);
    }

    public void GoToPlaceholderScreen() {
        PrimaryStage.setTitle("M.U.L.E. Place Holder Scene");
        PrimaryStage.setScene(PlaceholderScene);
    }

    public void GoToMapScene() {
        PrimaryStage.setTitle("M.U.L.E. Map");
        PrimaryStage.setScene(MapScene);
    }

    public void GoToTownScene() {
        PrimaryStage.setTitle("M.U.L.E. Town");
        PrimaryStage.setScene(TownScene);
    }

    public void GoToStoreScene() {
        GameModel.GetInstance().StoreController.onSceneActivated();
        PrimaryStage.setTitle("M.U.L.E. Store");
        PrimaryStage.setScene(StoreScene);
    }

    public void startRound() {
        for (Player player : GameModel.GetInstance().getPlayers()) {
            GameModel.GetInstance().turnOrder.add(player);
        }
        startTurn();
    }

    public void startTurn() {
        Player curPlayer = GameModel.GetInstance().turnOrder.remove();
        GameModel.MapController.turnLabel.setText(curPlayer.getName() + "'s turn!");
        GameModel.TownController.townTurnLabel.setText(curPlayer.getName() + "'s turn!");
        GameModel.StoreController.storeTurnLabel.setText(curPlayer.getName() + "'s turn!");
        //GameModel.TownController.turnLabel_town.setText(curPlayer.getName() + "'s turn!");
        System.out.println("Cur Player: " + curPlayer.getName());


        GameModel.GetInstance().setCurrentPlayer(curPlayer);
        Task task = new Task<Void>() {
            @Override public Void call() {

                GameModel.GetInstance().timer = new Timeline(new KeyFrame(
                        Duration.minutes(2),
                        ae -> endTurn()
                ));
                GameModel.GetInstance().timer.setCycleCount(Animation.INDEFINITE);
                GameModel.GetInstance().timer.play();

                return null;
            }

        };


        //new Alert(Alert.AlertType.CONFIRMATION, curPlayer.getName() + ", are you ready?", ButtonType.OK).showAndWait();
        new Thread(task).start();

        /*GameModel.GetInstance().timer = new Timeline(new KeyFrame(
                Duration.minutes(2),
                ae -> endTurn()
        ));
        GameModel.GetInstance().timer.setCycleCount(Animation.INDEFINITE);
        GameModel.GetInstance().timer.play();
        */


    }

    public void endTurn() {
        MasterController.GetInstance().GoToMapScene();
        if (GameModel.GetInstance().turnOrder.size() > 0) {
            startTurn();
        } else {
            endRound();
        }
    }

    public void endRound() {
        //round counter++?
        startRound();
    }

    public static MasterController GetInstance() { return Instance; }

}
