package sample;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public Button stopBtn;
    public Button startBtn;
    public TextField pointsTF;
    public Label resultLabel;
    public ProgressBar progressBar;

    private MonteCarlo task;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        startBtn.setOnAction(actionEvent -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            resultLabel.setText("Result: ");
            task = new MonteCarlo(gc, Integer.parseInt(pointsTF.getText()));
            task.setOnSucceeded(workerStateEvent -> {
                resultLabel.setText("Result: "+ task.getValue());

            });

            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.setVisible(true);
            new Thread(task).start();
        });

        stopBtn.setOnAction(actionEvent -> {
            task.cancel(true);
        });
    }
}
