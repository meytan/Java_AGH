package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController mainController = new MainController();
        mainController.showStage();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
