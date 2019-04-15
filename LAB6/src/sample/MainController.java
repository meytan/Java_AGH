package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public ListView<Task> todoList;
    public Button addButton;
    public ListView<Task> inProgressList;
    public ListView<Task> doneList;
    private ObservableList<Task> todo;
    private ObservableList<Task> inProgress;
    private ObservableList<Task> done;
    public Label todoLabel;

    private final Stage primaryStage;

    private MenuItem cut;
    private MenuItem copy;
    private MenuItem paste;
    private ContextMenu contextMenu;

    public MainController() {

        primaryStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            loader.setController(this);
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Kanban");
//            primaryStage.getScene().getStylesheets().add("./style.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        primaryStage.showAndWait();
    }

    void updateLists() {
        todo.add(new Task("elo", Priority.NORMAL, LocalDate.now(), "asdfjaksldf"));
    }

    public void add_new_task_clicked(MouseEvent mouseEvent) {
        AddController addController = new AddController(this);
        addController.showStage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todo = FXCollections.observableArrayList();
        inProgress = FXCollections.observableArrayList();
        done = FXCollections.observableArrayList();
        todoList.setItems(todo);
        todoList.setCellFactory(new TaskCellFactory());
        inProgressList.setItems(inProgress);
        inProgressList.setCellFactory(new TaskCellFactory());
        doneList.setItems(done);
        doneList.setCellFactory(new TaskCellFactory());

        contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(
            new MenuItem("Edit"),
            new SeparatorMenuItem(),
            new MenuItem("Add to To Do List"),
            new MenuItem("Add to In Progress List"),
            new MenuItem("Add to Done List")
        );

        todoList.setOnMouseClicked(onClick());
        inProgressList.setOnMouseClicked(onClick());
        doneList.setOnMouseClicked(onClick());

        todo.add(new Task("elo", Priority.NORMAL, LocalDate.now(), "asdfjaksldf"));
        todo.add(new Task("elo", Priority.LOW, LocalDate.now(), "asdfjaksldf"));
        todo.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
        todo.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
        todo.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
        inProgress.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
        done.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));


    }

    public void handleAboutAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setResizable(true);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Author: meytan55");
        alert.showAndWait();
    }

    public void handleCloseAction() {
        System.exit(0);
    }

    public void addTaskToToDoList(Task task) {
        todo.add(task);
    }

    EventHandler<MouseEvent> onClick() {

        return event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                ListView<Task> a = (ListView<Task>) event.getSource();
                String s = a.getId();
                contextMenu.getItems().forEach(item ->item.setVisible(true));
                switch (s){
                    case "todoList":
                        contextMenu.getItems().get(0).setOnAction(editEventHandler(todoList));
                        contextMenu.getItems().get(2).setVisible(false);
                        contextMenu.getItems().get(3).setOnAction(menuItemEventHandler(todoList, inProgressList));
                        contextMenu.getItems().get(4).setOnAction(menuItemEventHandler(todoList, doneList));
                        break;
                    case "inProgressList":
                        contextMenu.getItems().get(0).setOnAction(editEventHandler(inProgressList));
                        contextMenu.getItems().get(3).setVisible(false);
                        contextMenu.getItems().get(2).setOnAction(menuItemEventHandler(inProgressList, todoList));
                        contextMenu.getItems().get(4).setOnAction(menuItemEventHandler(inProgressList, doneList));
                        break;
                    case "doneList":
                        contextMenu.getItems().get(0).setOnAction(editEventHandler(doneList));
                        contextMenu.getItems().get(4).setVisible(false);
                        contextMenu.getItems().get(2).setOnAction(menuItemEventHandler(doneList, todoList));
                        contextMenu.getItems().get(3).setOnAction(menuItemEventHandler(doneList, inProgressList));
                        break;
                }

                contextMenu.show(primaryStage, event.getScreenX(), event.getScreenY());
            }
            else if(event.getButton().equals(MouseButton.PRIMARY)){
                if(event.getClickCount() == 2){
                    ListView<Task> source = (ListView<Task>) event.getSource();
                    Task task  = source.getItems().get(source.getSelectionModel().getSelectedIndices().get(0));
                    AddController addController = new AddController(this, task,true);
                    addController.showStage();
                }
            }



        };
    }

    EventHandler<ActionEvent> menuItemEventHandler(ListView<Task> source, ListView<Task> target){
        return event ->{
            target.getItems().add(source.getItems().get(source.getSelectionModel().getSelectedIndices().get(0)));
            source.getItems().remove(source.getItems().get(source.getSelectionModel().getSelectedIndices().get(0)));
        };
    }

    EventHandler<ActionEvent> editEventHandler(ListView<Task> source){
        return event ->{
            Task task = source.getItems().get(source.getSelectionModel().getSelectedIndices().get(0));
            AddController addController = new AddController(this, task);
            addController.showStage();
        };
    }


}
