import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class myGrid extends GridPane {

    private GridPane grid = new GridPane();
    private ComboBox comboBox;
    private Label perimter, area, perimterValue,areaValue, input1Label,input2Label,input3Label;
    private Text errorMsg;
    private TextField input1TextField,input2TextField,input3TextField;
    private Button calculateButton;
    private HBox input1HBox, input2HBox, input3HBox,perimterHBox,areaHBox;
    private VBox inputVBox, answersVBox;
    private StackPane calculatePane;

    public myGrid() {


        setGridCells();

        // Setting grid options
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(10,10,10,40));

        // Binding content with specific cell
        GridPane.setConstraints(comboBox,0,0);
        GridPane.setConstraints(answersVBox,1,0);
        GridPane.setConstraints(inputVBox,0,1);
        GridPane.setConstraints(calculatePane,1,1);

        grid.getChildren().addAll(comboBox, answersVBox,inputVBox,calculatePane);

    }

    // Handle comboBox where you can choose figure
    private EventHandler<ActionEvent> onFigureChange() {

        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(comboBox.getSelectionModel().getSelectedItem().toString().equals("Square")){
                    input1HBox.setVisible(true);
                    input1Label.setText("Side:  ");
                    input2HBox.setVisible(false);
                    input3HBox.setVisible(false);


                }
                else if(comboBox.getSelectionModel().getSelectedItem().toString().equals("Triangle")) {
                    input1HBox.setVisible(true);
                    input1Label.setText("Side 1:  ");
                    input2HBox.setVisible(true);
                    input2Label.setText("Side 2:  ");
                    input3HBox.setVisible(true);
                    input3Label.setText("Side 3:  ");
                }
                else{
                    input1HBox.setVisible(true);
                    input1Label.setText("Radius:  ");
                    input2HBox.setVisible(false);
                    input3HBox.setVisible(false);

                }
            }
        };


    }

    // Handle button "Calculate"
    private EventHandler<ActionEvent> calculation(){
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Figure figure;
                    errorMsg.setVisible(false);

                    if(comboBox.getSelectionModel().getSelectedItem().toString().equals("Square")){
                        figure = new Square(Double.parseDouble(input1TextField.getText()));

                    }
                    else if (comboBox.getSelectionModel().getSelectedItem().toString().equals("Triangle")){
                        figure = new Triangle(Double.parseDouble(input1TextField.getText()),Double.parseDouble(input2TextField.getText()),Double.parseDouble(input3TextField.getText()));
                    }
                    else{
                        figure = new Circle(Double.parseDouble(input1TextField.getText()));
                    }
                    perimterValue.setText(String.valueOf(figure.calculatePerimeter()));
                    areaValue.setText(String.valueOf(figure.calculateArea()));
                }
                catch (IllegalArgumentException e){
                    errorMsg.setText(e.getMessage());
                    errorMsg.setFill(Color.RED);
                    errorMsg.setVisible(true);
                }
            }
        };
    }

    // Setting cells content  used in that specific grid
    private void setGridCells(){

        comboBox = new ComboBox();
        comboBox.getItems().addAll(
                "Square",
                "Triangle",
                "Circle"
        );
        comboBox.getSelectionModel().selectFirst();


        perimter = new Label("Perimeter: ");
        perimterValue = new Label();
        area = new Label("Area: ");
        areaValue = new Label();

        answersVBox = new VBox();
        perimterHBox = new HBox();
        areaHBox = new HBox();

        perimterHBox.getChildren().setAll(perimter,perimterValue);
        areaHBox.getChildren().setAll(area,areaValue);

        answersVBox.setPadding(new Insets(0,10,10,30));
        answersVBox.setSpacing(8);
        answersVBox.getChildren().addAll(perimterHBox,areaHBox);

        input1Label = new Label("Side 1: ");
        input2Label = new Label();
        input3Label = new Label();
        errorMsg = new Text();
        errorMsg.setVisible(false);


        input1TextField = new TextField();
        input2TextField = new TextField();
        input3TextField = new TextField();

        input1TextField.setMaxWidth(50);
        input2TextField.setMaxWidth(50);
        input3TextField.setMaxWidth(50);

        inputVBox= new VBox();
        input1HBox = new HBox();
        input2HBox = new HBox();
        input3HBox = new HBox();

        input1HBox.setAlignment(Pos.CENTER_RIGHT);
        input2HBox.setAlignment(Pos.CENTER_RIGHT);
        input3HBox.setAlignment(Pos.CENTER_RIGHT);

        input2HBox.setVisible(false);
        input3HBox.setVisible(false);

        input1HBox.getChildren().setAll(input1Label,input1TextField);
        input2HBox.getChildren().setAll(input2Label,input2TextField);
        input3HBox.getChildren().setAll(input3Label,input3TextField);

        inputVBox.prefWidth(100);
        inputVBox.setMaxWidth(100);
        errorMsg.wrappingWidthProperty().bind(inputVBox.widthProperty());


        inputVBox.getChildren().setAll(input1HBox, input2HBox, input3HBox,errorMsg);

        calculateButton = new Button("Calculate");
        calculatePane = new StackPane(calculateButton);
        calculatePane.setAlignment(Pos.TOP_LEFT);
        calculatePane.setPadding(new Insets(0,0,0,30));


        calculateButton.setOnAction(calculation());
        comboBox.setOnAction(onFigureChange());

    }


    public GridPane getGrid(){
        return grid;
    }

}
