package markovsimulation.ui;


import java.util.HashSet;
import java.util.Set;
import markovsimulation.domain.markovManager;
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

public class markovUI extends Application {
    
    @Override
    public void start(Stage window){
        window.setHeight(300);
        window.setWidth(500);
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
        
        //Scene Root
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
        editpane.setAlignment(Pos.CENTER);
        HBox textinput2 = new HBox();
        textinput2.setSpacing(5);
        Button nodeaddbutton = new Button("add");
        TextField nodename = new TextField();
        Label nodeaddlabel = new Label("Node descriptor: ");
        textinput2.getChildren().setAll(nodeaddlabel, nodename, nodeaddbutton);
        editpane.add(textinput2, 0, 0);
        
        nodeaddbutton.setOnAction(e -> {
            String newname = nodename.getText();
            if (!logic.nodeExists(newname)){
                logic.addNode(newname);
                System.out.println(newname + " added!");
            } else {
                System.out.println(newname + " already exists!");
            }
        });
        
        BorderPane frame2 = new BorderPane();
        frame2.setBottom(buttonfield2);
        frame2.setCenter(editpane);
        
        Scene editscene = new Scene(frame2);
         
        //___result scene___
        
        //___scene-switch button functionality
        
        next1.setOnAction(e -> {
            window.setScene(editscene);
        });
        
        back2.setOnAction(e -> {
            window.setScene(loadscene);
        });
        
        window.setScene(loadscene);
        window.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
