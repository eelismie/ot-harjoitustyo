package markovsimulation.ui;

import java.util.ArrayList;
import java.io.File;
import java.sql.SQLException;
import markovsimulation.domain.MarkovManager;
import markovsimulation.dao.SimFromDb;
import markovsimulation.dao.SimFromFile;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.Font;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;

public class markovUI extends Application {
    
    private MarkovManager logic;
    private SimFromDb database;
    
    public void listelements(ListView<String> view, ArrayList<String> nodes){
        ObservableList<String> items = FXCollections.observableArrayList(nodes);
        view.setItems(items);
    }
    
    public void resize(Stage window, double w, double h){
        window.setHeight(h);
        window.setWidth(w);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        window.setX((bounds.getWidth() - w) / 2.0);
        window.setY((bounds.getHeight() - h) / 2.0);
    }
    
    @Override
    public void init() throws SQLException {
        logic = new MarkovManager();
        database = new SimFromDb();
        database.initDb("network.db");
    }
    
    @Override
    public void start(Stage window){
        
        window.setHeight(500);
        window.setWidth(400);
        window.setTitle("Markov Process Simulation");
        
        Text title = new Text("Markov Process Simulation");
        title.setFont(Font.font ("Verdana", 20));       
        //___load Scene___
                
        //buttonfield - Bottom
        HBox buttonField1 = new HBox();
        buttonField1.setPadding(new Insets(10));
        buttonField1.setAlignment(Pos.CENTER);
        Button next1 = new Button("next");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonField1.getChildren().setAll(spacer, next1);
        
        //inputfield - Center
        VBox inputField1 = new VBox();
        inputField1.setAlignment(Pos.CENTER);
        inputField1.setSpacing(10);
        inputField1.setPadding(new Insets(10));
        
        HBox textInput1 = new HBox();
        textInput1.setSpacing(5);
        textInput1.setAlignment(Pos.CENTER);
        Button loadButton = new Button("load from csv");
        Button loadButton2 = new Button("load from save");
        textInput1.getChildren().setAll(loadButton, loadButton2);
        inputField1.getChildren().setAll(title, textInput1);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        
        loadButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(window);
            if (file != null){
                if (logic.loadSim(new SimFromFile(file))) {
                    loadButton.setStyle("-fx-background-color: #70ff74; ");
                } else {
                    loadButton.setStyle("-fx-background-color: #ff7070; ");
                };
            }
        });
        
        loadButton2.setOnAction(e -> {
            if (logic.loadSim(database)) {
                loadButton2.setStyle("-fx-background-color: #70ff74; ");
            } else {
                loadButton2.setStyle("-fx-background-color: #ff7070; ");
            };
        });
        
        //Load scene Root
        BorderPane frame1 = new BorderPane();
        frame1.setBottom(buttonField1);
        frame1.setCenter(inputField1);
        frame1.setPadding(new Insets(10, 10, 10, 10));
        
        Scene loadScene = new Scene(frame1);
        
        //___edit scene___
        
        // Buttonfield - Bottom
        HBox buttonField2 = new HBox();
        buttonField2.setPadding(new Insets(10));
        buttonField2.setSpacing(5);
        Button next2 = new Button("run");
        Button save = new Button("export");
        Button save2 = new Button("save");
        Button back2 = new Button("back");
        Button clearButton = new Button("clear");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        buttonField2.getChildren().setAll(back2, save, save2, spacer2, clearButton, next2);
        
        save.setOnAction(e -> {
            FileChooser fileChooser2 = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser2.getExtensionFilters().add(extFilter);
            File file = fileChooser2.showSaveDialog(window);
            if (file != null) {
                logic.saveSim(new SimFromFile(file));
            }
        });
        
        save2.setOnAction(e -> {
            logic.saveSim(database);
        });
        
        // Editfield - Center
        GridPane editPane = new GridPane();
        editPane.setPadding(new Insets(10));
        editPane.setHgap(5);
        editPane.setVgap(5);
        editPane.setAlignment(Pos.CENTER);
        
        HBox textInput2 = new HBox();
        textInput2.setSpacing(5);
        Button nodeAddButton = new Button("add");
        TextField nodeName = new TextField();
        Label nodeAddLabel = new Label("Node descriptor: ");
        textInput2.getChildren().setAll(nodeAddLabel, nodeName, nodeAddButton);
        
        HBox textInput3 = new HBox();
        textInput3.setSpacing(5);
        Button connectAddButton = new Button("add");
        TextField connectFrom = new TextField();
        TextField connectTo = new TextField();
        connectFrom.setMaxWidth(50);
        connectTo.setMaxWidth(50);
        Label connectaddlabel = new Label("Connection (start idx/end idx): ");
        textInput3.getChildren().setAll(connectaddlabel, connectFrom, connectTo, connectAddButton);
        
        ListView<String> nodelist = new ListView();
        ListView<String> connectionlist = new ListView();
        
        Text nodeadded = new Text();
        Text connectadded = new Text();
        
        editPane.add(nodeadded, 0, 0);
        editPane.add(connectadded, 1, 0);
        editPane.add(nodelist, 0, 1);
        editPane.add(connectionlist, 1, 1);
        editPane.add(textInput2, 0, 2);
        editPane.add(textInput3, 1, 2);
        
        nodeAddButton.setOnAction(e -> {
            String newname = nodeName.getText().trim();
            if (!logic.nodeExists(newname) && (!newname.equals(""))){
                logic.addNode(newname);
                nodeadded.setText("node: '" + newname + "' added!");
                listelements(nodelist, logic.getNodes());
            }
        });
        
        connectAddButton.setOnAction(e -> {
            String begin = connectFrom.getText();
            String end = connectTo.getText();
            if (logic.addConnect(begin, end)){
                connectadded.setText("connection from " + begin + " to " + end + " added!");
                listelements(connectionlist, logic.getConnects());
            } else {
                connectadded.setText("incorrect input!");
            }
        });
        
        clearButton.setOnAction(e -> {
            logic.restart();
            listelements(connectionlist, logic.getConnects());
            listelements(nodelist, logic.getNodes());     
        });
        
        BorderPane frame2 = new BorderPane();
        frame2.setBottom(buttonField2);
        frame2.setCenter(editPane);
        
        Scene editscene = new Scene(frame2);
         
        //___result scene___
        
        HBox buttonfield3 = new HBox();
        buttonfield3.setPadding(new Insets(10));
        Button back3 = new Button("back");
        Button next3 = new Button("next state");
        Button restart = new Button("restart");
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        buttonfield3.getChildren().setAll(back3, spacer3, restart, next3);
        
        GridPane resultpane = new GridPane();
        resultpane.setPadding(new Insets(10));
        resultpane.setHgap(5);
        resultpane.setVgap(5);
        
        ListView<String> probabilities = new ListView();
        
        VBox options = new VBox();
        options.setSpacing(5);
        options.setPadding(new Insets(10));
        
        HBox startingnodebox = new HBox();
        startingnodebox.setAlignment(Pos.CENTER_RIGHT);
        Label snlabel = new Label("Select starting node: ");
        Slider startingnodeslider = new Slider(0, 0, 0);
        startingnodeslider.setBlockIncrement(1);
        startingnodeslider.setMajorTickUnit(1);
        startingnodeslider.setMinorTickCount(0);
        startingnodeslider.setShowTickLabels(true);
        startingnodeslider.setSnapToTicks(true);
        
        HBox allowjumpsbox = new HBox();
        allowjumpsbox.setAlignment(Pos.CENTER_RIGHT);
        Label ajlabel = new Label("Allow jumps: ");
        Slider jumpprobslider = new Slider(0, 1.0, 0);
        jumpprobslider.setMajorTickUnit(0.2);
        jumpprobslider.setMinorTickCount(3);
        jumpprobslider.setShowTickLabels(true);
        
        HBox stepsizeBox = new HBox();
        stepsizeBox.setAlignment(Pos.CENTER_RIGHT);
        Label szlabel = new Label("Step size: ");
        Slider stepSlider = new Slider(1.0, 5.0, 0);
        stepSlider.setBlockIncrement(1);
        stepSlider.setMajorTickUnit(1);
        stepSlider.setMinorTickCount(0);
        stepSlider.setShowTickLabels(true);
        stepSlider.setSnapToTicks(true);
        
        startingnodeslider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    logic.setResultDisplay(new_val.intValue());
                    listelements(probabilities, logic.getProbabilities());
            }
        });
        
        jumpprobslider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    logic.addJumps(new_val.doubleValue());
            }
        });
        
        stepSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    logic.setStepSize(new_val.intValue());
            }
        });
        
        startingnodebox.getChildren().setAll(snlabel, startingnodeslider);
        allowjumpsbox.getChildren().setAll(ajlabel, jumpprobslider);
        stepsizeBox.getChildren().setAll(szlabel, stepSlider);
        options.getChildren().setAll(startingnodebox, allowjumpsbox, stepsizeBox);
        
        Label stepTitle = new Label("Probabilities");
        resultpane.setAlignment(Pos.CENTER);
        resultpane.add(stepTitle, 0, 0);
        resultpane.add(probabilities, 0, 1);
        resultpane.add(options, 0, 2);
        
        BorderPane frame3 = new BorderPane();
        frame3.setCenter(resultpane);
        frame3.setBottom(buttonfield3);
        Scene resultscene = new Scene(frame3);
        
        next3.setOnAction(e->{
            logic.evolveCurrentSim();
            listelements(probabilities, logic.getProbabilities());
        });
        
        restart.setOnAction(e->{
            logic.recoverSim();
            listelements(probabilities, logic.getProbabilities());
        });
        
        //___scene-switch button functionality
        
        next1.setOnAction(e -> {
            listelements(nodelist, logic.getNodes());
            listelements(connectionlist, logic.getConnects());
            window.setScene(editscene);
            resize(window, 800, 500);
        });
        
        next2.setOnAction(e -> {
            if (!logic.nothingLoaded()){
                logic.initSim();
                startingnodeslider.setMax(logic.getSize() - 1);
                window.setScene(resultscene);
                resize(window, 400, 500);
            }
        });
        
        back2.setOnAction(e -> {
            window.setScene(loadScene);
            resize(window, 400, 500);
        });
        
        back3.setOnAction(e -> {
            window.setScene(editscene);
            resize(window, 800, 500);
        });
        
        window.setScene(loadScene);
        window.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
