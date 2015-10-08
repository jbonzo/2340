import control.MapController;
import control.MasterController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.GameModel;
import model.MapModel;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MasterController controller = MasterController.GetInstance();
        controller.init(primaryStage);



        new AnimationTimer()
        {
            public void handle(long currentTime)
            {
                if(GameModel.GetInstance().timer != null) {
                    //System.out.println(GameModel.timer.getCurrentTime().toSeconds());
                    int time = GameModel.TURN_LENGTH - (int)GameModel.timer.getCurrentTime().toSeconds();
                    GameModel.MapController.mapTimeLabel.setText(time + "");
                    GameModel.TownController.townTimeLabel.setText(time + "");
                    GameModel.StoreController.storeTimeLabel.setText(time + "");

                    //GameModel.TownController.timeLabel_town.setText(100 - (int)GameModel.timer.getCurrentTime().toSeconds() + "");
                    if (time <= 0) {
                        stop();
                        new Alert(Alert.AlertType.WARNING, "YOUR TIME IS UP!", ButtonType.OK).show();
                        MasterController.GetInstance().endTurn();
                        start();
                    }
                }
            }
        }.start();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
