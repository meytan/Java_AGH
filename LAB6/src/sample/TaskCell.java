package sample;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class TaskCell extends ListCell<Task> {



    @Override
    public void updateItem(Task item, boolean empty){
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setStyle("");
        }
        else{
            this.setText(item.getTitle());

            switch (item.getPriority()) {
                case LOW:
                    setStyle("-fx-background-color: aqua;");
                    break;
                case NORMAL:
                    setStyle("-fx-background-color: chartreuse;");
                    break;
                case HIGH:
                    setStyle("-fx-background-color: red;");
                    break;
                default:
                    setStyle("-fx-background-color: aliceblue;");
            }


            //setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        }






    }
}
