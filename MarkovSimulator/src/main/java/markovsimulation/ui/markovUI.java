package markovsimulation.ui;

import java.util.ArrayList;
import markovsimulation.domain.markovManager;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class markovUI extends Application {
    
    public void listelements(ListView<String> view, ArrayList<String> nodes){
        if (nodes.isEmpty()) return;
        ObservableList<String> items = FXCollections.observableArrayList(nodes);
        view.setItems(items);
    }
    
    @Override
    public void start(Stage window){
        
        window.setHeight(500);
        window.setWidth(800);
        window.setTitle("Markov Process Simulation");
        markovManager logic = new markovManager();
        
        //___load Scene___
                
        //buttonfield - Bottom
        HBox buttonfield1 = new HBox();
        buttonfield1.setPadding(new Insets(10));
        Button next1 = new Button("next");
        Button back1 = new Button("quit");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonfield1.getChildren().setAll(back1, spacer, next1);
        
        //inputfield - Center
        VBox inputfield1 = new VBox();
        inputfield1.setAlignment(Pos.CENTER);
        inputfield1.setPadding(new Insets(10));
        
        HBox textinput1 = new HBox();
        textinput1.setSpacing(5);
        Button loadbutton = new Button("load");
        TextField filelocation = new TextField();
        Label loadlabel = new Label("Simulation file location: ");
        textinput1.getChildren().setAll(loadlabel, filelocation, loadbutton);
        inputfield1.getChildren().setAll(textinput1);
        
        //Load scene Root
        BorderPane frame1 = new BorderPane();
        frame1.setBottom(buttonfield1);
        frame1.setCenter(inputfield1);
        
        Scene loadscene = new Scene(frame1);
        
        //___edit scene___
        
        // Buttonfield - Bottom
        HBox buttonfield2 = new HBox();
        buttonfield2.setPadding(new Insets(10));
        Button next2 = new Button("run");
        Button back2 = new Button("back");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        buttonfield2.getChildren().setAll(back2, spacer2, next2);
        
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
        
        ListView<String> probabilities = new ListView(); 
        
        next3.setOnAction(e->{
            logic.evolveCurrentSim(1);
            listelements(probabilities, logic.getProbabilities(0));
        });
        
        BorderPane frame3 = new BorderPane();
        frame3.setCenter(probabilities);
        frame3.setBottom(buttonfield3);
        Scene resultscene = new Scene(frame3);
        
        //___scene-switch button functionality
        
        next1.setOnAction(e -> {
            window.setScene(editscene);
        });
        
        next2.setOnAction(e -> {
            if (!logic.nothingloaded()){
                logic.initsim();
                window.setScene(resultscene);
            }
        });
        
        back2.setOnAction(e -> {
            window.setScene(loadscene);
        });
        
        back3.setOnAction(e -> {
            window.setScene(editscene);
        });
        
        window.setScene(loadscene);
        window.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
