package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.input.KeyCode.P;
import static javafx.scene.input.KeyCode.T;

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

    private static final String CSV_SEPARATOR = ",";

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
            new MenuItem("Add to Done List"),
            new SeparatorMenuItem(),
            new MenuItem("Delete")
        );

        todoList.setOnMouseClicked(onClick());
        inProgressList.setOnMouseClicked(onClick());
        doneList.setOnMouseClicked(onClick());

//        todo.add(new Task("elo", Priority.NORMAL, LocalDate.now(), "asdfjaksldf"));
//        todo.add(new Task("elo", Priority.LOW, LocalDate.now(), "asdfjaksldf"));
//        todo.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
//        todo.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
//        todo.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
//        inProgress.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));
//        done.add(new Task("elo", Priority.HIGH, LocalDate.now(), "asdfjaksldf"));


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
                        contextMenu.getItems().get(6).setOnAction(deleteEventHandler(todoList));
                        break;
                    case "inProgressList":
                        contextMenu.getItems().get(0).setOnAction(editEventHandler(inProgressList));
                        contextMenu.getItems().get(3).setVisible(false);
                        contextMenu.getItems().get(2).setOnAction(menuItemEventHandler(inProgressList, todoList));
                        contextMenu.getItems().get(4).setOnAction(menuItemEventHandler(inProgressList, doneList));
                        contextMenu.getItems().get(6).setOnAction(deleteEventHandler(inProgressList));
                        break;
                    case "doneList":
                        contextMenu.getItems().get(0).setOnAction(editEventHandler(doneList));
                        contextMenu.getItems().get(4).setVisible(false);
                        contextMenu.getItems().get(2).setOnAction(menuItemEventHandler(doneList, todoList));
                        contextMenu.getItems().get(3).setOnAction(menuItemEventHandler(doneList, inProgressList));
                        contextMenu.getItems().get(6).setOnAction(deleteEventHandler(doneList));
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
    EventHandler<ActionEvent> deleteEventHandler(ListView<Task> source){
        return event ->{
            source.getItems().remove(source.getItems().get(source.getSelectionModel().getSelectedIndices().get(0)));
        };
    }

    public void handleOpenAction(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(primaryStage);

            if(file == null) throw new IOException("File not choose");

            List<Task>[] list = new List[3];

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (List<Task>[]) in.readObject();

            in.close();
            fileIn.close();

            todo.clear();
            inProgress.clear();
            done.clear();

            todo.addAll(list[0]);
            inProgress.addAll(list[1]);
            done.addAll(list[2]);
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("List<Task> class not found");
            c.printStackTrace();
            return;
        }



    }

    public void handleSaveAction()  {
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            File file = fileChooser.showSaveDialog(primaryStage);

            FileOutputStream fileOut = new FileOutputStream(file);

            List<Task>[] list = new List[3];
            list[0] = getListFromObservableList(todo);
            list[1] = getListFromObservableList(inProgress);
            list[2] = getListFromObservableList(done);

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);

            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public void handleImportAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import CSV File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showOpenDialog(primaryStage);

            if(file == null) throw new IOException("File not choose");

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            ObservableList<Task> list = null;
            String str;

            todo.clear();
            inProgress.clear();
            done.clear();

            while ((str = br.readLine()) != null)
            {
                try {
                    if (str.equals("ToDo:")) list = todo;
                    else if (str.equals("In Progress:")) list = inProgress;
                    else if (str.equals("Done:")) list = done;
                    else {
                        String[] attr = str.split(CSV_SEPARATOR);
                        String[] date = attr[2].split("-");
                        LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                        list.addAll(new Task(attr[0], setPriority(attr[1]), localDate, attr[3]));
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid row!");
                }

            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleExportAction(){
        try
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export CSV");
            File file = fileChooser.showSaveDialog(primaryStage);
            if (!file.getName().endsWith(".csv")){
                file = new File(file.getPath() + ".csv");
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            bw.write("ToDo:");
            bw.newLine();
            for (Task task : todo){
                bw.write(formatToCSV(task));
                bw.newLine();
            }
            bw.write("In Progress:");
            bw.newLine();
            for (Task task : inProgress){
                bw.write(formatToCSV(task));
                bw.newLine();
            }
            bw.write("Done:");
            bw.newLine();
            for (Task task : done){
                bw.write(formatToCSV(task));
                bw.newLine();
            }

            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private List<Task> getListFromObservableList(ObservableList<Task> observableList){
        List<Task> list = new ArrayList<>();
        for(int i = 0; i < observableList.size(); i++){
            list.add(observableList.get(i));
        }
        return list;
    }

    private String formatToCSV(Task task)
    {
        StringBuffer oneLine = new StringBuffer();
        oneLine.append(task.getTitle());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(task.getPriority());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(task.getExpDate());
        oneLine.append(CSV_SEPARATOR);
        oneLine.append(task.getDescription());
        return oneLine.toString();
    }

    private Priority setPriority(String s){
        if( s.equals("LOW")) return Priority.LOW;
        else if( s.equals("NORMAL")) return Priority.NORMAL;
        else return Priority.HIGH;
    }


}
