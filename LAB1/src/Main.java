import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("JAVA LAB 1");
        primaryStage.setScene(new Scene(new myGrid().getGrid(), 350, 200));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
