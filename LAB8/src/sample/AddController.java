package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {


    public javafx.scene.control.TextField titleTextField;
    public DatePicker expDateDatePicker;
    public ComboBox<Priority> priorityComboBox;
    public TextArea descriptionTextArea;
    public Button addButton;

    private Stage thisStage;
    private MainController mainController;


    AddController(MainController mainController){

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Add");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mainController = mainController;
    }
    AddController(MainController mainController,Task task,boolean isReadOnly){

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Details");
            addButton.setVisible(false);

            titleTextField.setText(task.getTitle());
            priorityComboBox.setValue(task.getPriority());
            expDateDatePicker.setValue(task.getExpDate());
            descriptionTextArea.setText(task.getDescription());

            titleTextField.setEditable(false);
            descriptionTextArea.setEditable(false);
            expDateDatePicker.setEditable(false);
            expDateDatePicker.setDisable(true);
            priorityComboBox.setEditable(false);
            priorityComboBox.setDisable(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mainController = mainController;
    }


    AddController(MainController mainController,Task task){

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Edit");

            titleTextField.setText(task.getTitle());
            priorityComboBox.setValue(task.getPriority());
            expDateDatePicker.setValue(task.getExpDate());
            descriptionTextArea.setText(task.getDescription());

            addButton.setText("Edit");
            addButton.setOnAction(editTask(task));

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mainController = mainController;
    }

    public void showStage(){
        thisStage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorityComboBox.getItems().addAll(Priority.LOW,Priority.NORMAL,Priority.HIGH);
    }

    public void addTask(ActionEvent actionEvent) {
        mainController.addTaskToToDoList(new Task(titleTextField.getText(),priorityComboBox.getValue(),expDateDatePicker.getValue(),descriptionTextArea.getText()));
        thisStage.close();
    }
    public EventHandler<ActionEvent> editTask(Task task){
        return actionEvent -> {

            task.edit(titleTextField.getText(),priorityComboBox.getValue(),expDateDatePicker.getValue(),descriptionTextArea.getText());
            mainController.todoList.getSelectionModel().selectFirst();
            mainController.inProgressList.getSelectionModel().selectFirst();
            mainController.doneList.getSelectionModel().selectFirst();

            thisStage.close();
        };
    }
}
