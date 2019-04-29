package markovsimulation.ui;

import java.util.ArrayList;
import java.io.File;
import java.sql.SQLException;
import markovsimulation.domain.markovManager;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
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
import javafx.stage.FileChooser.ExtensionFilter;

public class markovUI extends Application {
    
    private markovManager logic;
    
    public void listelements(ListView<String> view, ArrayList<String> nodes){
        ObservableList<String> items = FXCollections.observableArrayList(nodes);
        view.setItems(items);
    }
    
    public void resize(Stage window, double w, double h){
        window.setHeight(h);
        window.setWidth(w);
    }
    
    @Override
    public void init() throws SQLException {
        logic = new markovManager();
    }
    
    @Override
    public void start(Stage window){
        
        window.setHeight(500);
        window.setWidth(400);
        window.setTitle("Markov Process Simulation");
        markovManager logic = new markovManager();
        
        //___load Scene___
                
        //buttonfield - Bottom
        HBox buttonfield1 = new HBox();
        buttonfield1.setPadding(new Insets(10));
        buttonfield1.setAlignment(Pos.CENTER);
        Button next1 = new Button("next");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonfield1.getChildren().setAll(spacer, next1);
        
        //inputfield - Center
        VBox inputfield1 = new VBox();
        inputfield1.setAlignment(Pos.CENTER);
        inputfield1.setPadding(new Insets(10));
        
        HBox textinput1 = new HBox();
        textinput1.setSpacing(5);
        Button loadbutton = new Button("load from csv");
        textinput1.getChildren().setAll(loadbutton);
        inputfield1.getChildren().setAll(textinput1);

        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().add(new ExtensionFilter("CSV", "*.csv"));
        
        loadbutton.setOnAction(e -> {
            File file = filechooser.showOpenDialog(window);
            if (file != null){
                if (logic.loadSim(file)) {
                    loadbutton.setStyle("-fx-background-color: #70ff74; ");
                } else {
                    loadbutton.setStyle("-fx-background-color: #ff7070; ");
                };
            }
        });
        
        //Load scene Root
        BorderPane frame1 = new BorderPane();
        frame1.setBottom(buttonfield1);
        frame1.setCenter(inputfield1);
        frame1.setAlignment(inputfield1, Pos.CENTER);
        
        Scene loadscene = new Scene(frame1);
        
        //___edit scene___
        
        // Buttonfield - Bottom
        HBox buttonfield2 = new HBox();
        buttonfield2.setPadding(new Insets(10));
        buttonfield2.setSpacing(5);
        Button next2 = new Button("run");
        Button save = new Button("save");
        Button back2 = new Button("back");
        Button clearbutton = new Button("clear");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        buttonfield2.getChildren().setAll(back2, save, spacer2, clearbutton, next2);
        
        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(window);
            logic.saveSim(file);
        });
        
        // Editfield - Center
        GridPane editpane = new GridPane();
        editpane.setPadding(new Insets(10));
        editpane.setHgap(5);
        editpane.setVgap(5);
        editpane.setAlignment(Pos.CENTER);
        
        HBox textinput2 = new HBox();
        textinput2.setSpacing(5);
        Button nodeaddbutton = new Button("add");
        TextField nodename = new TextField();
        Label nodeaddlabel = new Label("Node descriptor: ");
        textinput2.getChildren().setAll(nodeaddlabel, nodename, nodeaddbutton);
        
        HBox textinput3 = new HBox();
        textinput3.setSpacing(5);
        Button connectaddbutton = new Button("add");
        TextField connectfrom = new TextField();
        TextField connectto = new TextField();
        connectfrom.setMaxWidth(50);
        connectto.setMaxWidth(50);
        Label connectaddlabel = new Label("Connection (start idx/end idx): ");
        textinput3.getChildren().setAll(connectaddlabel, connectfrom, connectto, connectaddbutton);
        
        ListView<String> nodelist = new ListView();
        ListView<String> connectionlist = new ListView();
        
        Text nodeadded = new Text();
        Text connectadded = new Text();
        
        editpane.add(nodeadded, 0, 0);
        editpane.add(connectadded, 1, 0);
        editpane.add(nodelist, 0, 1);
        editpane.add(connectionlist, 1, 1);
        editpane.add(textinput2, 0, 2);
        editpane.add(textinput3, 1, 2);
        
        nodeaddbutton.setOnAction(e -> {
            String newname = nodename.getText().trim();
            if (!logic.nodeExists(newname) && (!newname.equals(""))){
                logic.addNode(newname);
                nodeadded.setText("node: '" + newname + "' added!");
                listelements(nodelist, logic.getNodes());
            }
        });
        
        connectaddbutton.setOnAction(e -> {
            String begin = connectfrom.getText();
            String end = connectto.getText();
            if (logic.addConnect(begin, end)){
                connectadded.setText("connection from " + begin + " to " + end + " added!");
                listelements(connectionlist, logic.getConnects());
            } else {
                connectadded.setText("incorrect input!");
            }
        });
        
        clearbutton.setOnAction(e -> {
            logic.restart();
            listelements(connectionlist, logic.getConnects());
            listelements(nodelist, logic.getNodes());     
        });
        
        BorderPane frame2 = new BorderPane();
        frame2.setBottom(buttonfield2);
        frame2.setCenter(editpane);
        
        Scene editscene = new Scene(frame2);
         
        //___result scene___
        
        HBox buttonfield3 = new HBox();
        buttonfield3.setPadding(new Insets(10));
        Button back3 = new Button("back");
        Button next3 = new Button("next state");
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        buttonfield3.getChildren().setAll(back3, spacer3, next3);
        
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
        
        startingnodebox.getChildren().setAll(snlabel, startingnodeslider);
        allowjumpsbox.getChildren().setAll(ajlabel, jumpprobslider);
        options.getChildren().setAll(startingnodebox, allowjumpsbox);
        
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
            logic.evolveCurrentSim(1);
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
            window.setScene(loadscene);
            resize(window, 400, 500);
        });
        
        back3.setOnAction(e -> {
            window.setScene(editscene);
            resize(window, 800, 500);
        });
        
        window.setScene(loadscene);
        window.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
